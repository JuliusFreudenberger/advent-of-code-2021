import kotlin.streams.toList

fun main() {


    fun part1(input: List<String>): Int {
        fun getBytesForSequence(sequence: String): ByteArray {
            return sequence.toByteArray(Charsets.UTF_8).mapTo()
        }

        fun getNumberOfOneBits(inputInt: List<ByteArray>, sequenceSize: Int): IntArray {
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

        fun getMoreCommonBits(sequenceSize: Int, numberOfOneBits: IntArray, numberOfSequences: Int): IntArray {
            val moreCommonBits = IntArray(sequenceSize)
            for (index in numberOfOneBits.indices) {
                if (numberOfOneBits[index] > numberOfSequences / 2) {
                    moreCommonBits[index] = 1
                } else {
                    moreCommonBits[index] = 0
                }
            }
            return moreCommonBits
        }

        val numberOfSequences = input.size
        val sequenceSize = input[0].length
        val inputInt = input.stream().map { getBytesForSequence(it) }.toList()
        val numberOfOneBits = getNumberOfOneBits(inputInt, sequenceSize)
        val moreCommonBits = getMoreCommonBits(sequenceSize, numberOfOneBits, numberOfSequences)

        val gammaRate: Int = moreCommonBits.sum()
        val epsilonRate: Int = moreCommonBits.sumOf { if (it == 1) 0 as Int; else 1 as Int }

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
