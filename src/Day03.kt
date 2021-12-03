import kotlin.math.pow
import kotlin.streams.toList

fun main() {

    fun getBytesForSequence(sequence: String): List<Byte> {
        return sequence.toByteArray(Charsets.UTF_8).map { (it - 48).toByte() }
    }

    fun flipAllBits(bits: List<Byte>): List<Byte> {
        return bits.map { if (it == 1.toByte()) 0.toByte(); else 1.toByte() }.toList()
    }

    fun getNumberOfOneBits(inputInt: List<List<Byte>>, sequenceSize: Int): IntArray {
        val numberOfOneBits = IntArray(sequenceSize)
        for (sequence in inputInt) {
            for (index in sequence.indices) {
                if (sequence[index] == 1.toByte()) {
                    numberOfOneBits[index]++
                }
            }
        }
        return numberOfOneBits
    }

    fun getMoreCommonBits(numberOfOneBits: IntArray, numberOfSequences: Int): List<Byte> {
        val moreCommonBits = ArrayList<Byte>()
        for (number in numberOfOneBits) {
            if (number >= numberOfSequences / 2f) {
                moreCommonBits.add(1)
            } else {
                moreCommonBits.add(0)
            }
        }
        return moreCommonBits
    }

    fun byteListToInt(bytes: List<Byte>): Int {
        val listSize = bytes.size
        var result = 0f
        for (index in bytes.indices) {
            result += bytes[index] * (2f.pow(listSize - index - 1))
        }
        return result.toInt()
    }

    fun filterSequenceHavingValueInPosition(sequence: List<List<Byte>>, value: Byte, position: Int): List<List<Byte>> {
        return sequence.filter { it[position] == value }.toList()
    }

    fun part1(input: List<String>): Int {
        val numberOfSequences = input.size
        val sequenceSize = input[0].length
        val inputInt = input.stream().map { getBytesForSequence(it) }.toList()
        val numberOfOneBits = getNumberOfOneBits(inputInt, sequenceSize)
        val moreCommonBits = getMoreCommonBits(numberOfOneBits, numberOfSequences)

        val gammaRate: Int = byteListToInt(moreCommonBits)
        val epsilonRate: Int = byteListToInt(flipAllBits(moreCommonBits))

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        val sequenceSize = input[0].length
        val inputInt = input.stream().map { getBytesForSequence(it) }.toList()
        var currentList = inputInt

        var oxygenGeneratorRating = 0
        var co2ScrubberRating = 0

        for (index in 0 until sequenceSize) {
            val numberOfSequences = currentList.size
            val numberOfOneBits = getNumberOfOneBits(currentList, sequenceSize)
            val moreCommonBits = getMoreCommonBits(numberOfOneBits, numberOfSequences)
            currentList = filterSequenceHavingValueInPosition(currentList, moreCommonBits[index], index)
            if (currentList.size == 1) oxygenGeneratorRating = byteListToInt(currentList[0])
        }

        currentList = inputInt
        for (index in 0 until sequenceSize) {
            val numberOfSequences = currentList.size
            val numberOfOneBits = getNumberOfOneBits(currentList, sequenceSize)
            val lessCommonBits = flipAllBits(getMoreCommonBits(numberOfOneBits, numberOfSequences))
            currentList = filterSequenceHavingValueInPosition(currentList, lessCommonBits[index], index)
            if (currentList.size == 1) co2ScrubberRating = byteListToInt(currentList[0])
        }

        return oxygenGeneratorRating * co2ScrubberRating

    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
