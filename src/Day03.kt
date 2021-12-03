import kotlin.math.pow
import kotlin.streams.toList

fun main() {


    fun part1(input: List<String>): Int {
        fun getBytesForSequence(sequence: String): List<Byte> {
            return sequence.toByteArray(Charsets.UTF_8).map { (it - 48).toByte() }
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
                if (number > numberOfSequences / 2) {
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

        val numberOfSequences = input.size
        val sequenceSize = input[0].length
        val inputInt = input.stream().map { getBytesForSequence(it) }.toList()
        val numberOfOneBits = getNumberOfOneBits(inputInt, sequenceSize)
        val moreCommonBits = getMoreCommonBits(numberOfOneBits, numberOfSequences)

        val gammaRate: Int = byteListToInt(moreCommonBits)
        val epsilonRate: Int = byteListToInt(moreCommonBits.map { if (it == 1.toByte()) 0.toByte(); else 1.toByte() }.toList())

        return gammaRate * epsilonRate

    }
//
//    fun part2(input: List<String>): Int {
//    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
//    check(part2(testInput) == 900)

    val input = readInput("Day03")
    println(part1(input))
//    println(part2(input))
}
