package cfsb.crimsoncoder.advent2023

class Day05(input: String) {
    private val seeds = parseSeeds(input)
    private val mappings: List<List<Mapping>> = parseMappings(input)

    fun solveP1(): Long {
        return seeds.minOf { seed ->
            mappings.fold(seed) { acc, mappings ->
                mappings.firstOrNull { acc in it }?.translate(acc) ?: acc
            }
        }
    }

    private val seedRanges = parseSeeds2(input)

    fun solveP2(): Long {
        val reverseMappings = mappings.map { mapping ->
            mapping.map { it.reverse() }
        }.reversed()

        println(seedRanges)
        println(reverseMappings.size)

        return generateSequence(0L, Long::inc).first { location ->
            val seed = reverseMappings.fold(location) { acc, mappings ->
                mappings.firstOrNull { acc in it }?.translate(acc) ?: acc
            }
            seedRanges.any { seedRange -> seed in seedRange }
        }
    }

    private fun parseSeeds(input: String): List<Long> {
        return input.trim().lines().first().substringAfter("seeds:").trim().split(" ").map { it.toLong() }
    }

    private fun parseMappings(input: String): List<List<Mapping>> {
        return input.trim().lines().drop(2).joinToString("\n").split("\n\n").map { it.trim().lines().drop(1).map { line -> Mapping.of(line) } }
    }

    private fun parseSeeds2(input: String): List<LongRange> {
        val ranges = mutableListOf<LongRange>()
        val nums = input.trim().lines().first().substringAfter("seeds:").trim().split(" ").map { it.toLong() }
        var idx = 0
        while (idx < nums.size) {
            ranges.add(nums[idx]..< nums[idx] + nums[idx + 1])
            idx += 2
        }
        return ranges
    }
}

private data class Mapping(
    val inputRange: LongRange,
    val outputRange: LongRange,
) {

    fun translate(input: Long): Long {
        return when {
            input in inputRange -> outputRange.first + (input - inputRange.first)
            else -> input
        }
    }

    operator fun contains(input: Long): Boolean {
        return input in inputRange
    }

    fun reverse(): Mapping = Mapping(outputRange, inputRange)

    companion object {
        fun of(row: String): Mapping {
            val (a,b,c) = row.split(" ", limit = 3).map { it.toLong() }
            return Mapping(
                inputRange = b..< b+c,
                outputRange = a..< a+c
            )
        }
    }
}
