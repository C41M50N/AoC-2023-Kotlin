package cfsb.crimsoncoder.advent2023

class Day05(private val input: String) {
    private val seeds = parseSeeds(input)
    private val mappings = getMappings(input)
    private val seedToSoilMap: (Long) -> Long = { src -> bigEval(src, mappings[0]) }
    private val soilToFertilizerMap: (Long) -> Long = { src -> bigEval(src, mappings[1]) }
    private val fertilizerToWaterMap: (Long) -> Long = { src -> bigEval(src, mappings[2]) }
    private val waterToLightMap: (Long) -> Long = { src -> bigEval(src, mappings[3]) }
    private val lightToTemperatureMap: (Long) -> Long = { src -> bigEval(src, mappings[4]) }
    private val temperatureToHumidityMap: (Long) -> Long = { src -> bigEval(src, mappings[5]) }
    private val humidityToLocationMap: (Long) -> Long = { src -> bigEval(src, mappings[6]) }

    fun solveP1(): Long {
        return seeds.map { seed ->
            var res = seedToSoilMap(seed)
            res = soilToFertilizerMap(res)
            res = fertilizerToWaterMap(res)
            res = waterToLightMap(res)
            res = lightToTemperatureMap(res)
            res = temperatureToHumidityMap(res)
            res = humidityToLocationMap(res)
            res
        }.min()
    }

    fun solveP2(): Long {
        var min = Long.MAX_VALUE

        val seedRanges = parseSeeds2(input)
        println(seedRanges)
        for (seedRange in seedRanges) {
            for (seed in seedRange) {
                var res = seedToSoilMap(seed)
                res = soilToFertilizerMap(res)
                res = fertilizerToWaterMap(res)
                res = waterToLightMap(res)
                res = lightToTemperatureMap(res)
                res = temperatureToHumidityMap(res)
                res = humidityToLocationMap(res)

                if (res < min) {
                    min = res
                }
            }
        }

        return min
    }

    private fun parseSeeds(input: String): List<Long> {
        return input
            .trim()
            .lines()[0]
            .substringAfter("seeds: ")
            .split(" ")
            .map { it.toLong() }
    }

    private fun parseSeeds2(input: String): List<LongRange> {
        val seedRanges = mutableListOf<LongRange>()
        val nums = input
            .trim()
            .lines()[0]
            .substringAfter("seeds: ")
            .split(" ").map { it.toLong() }

        for (i in nums.indices step 2) {
            seedRanges.add(nums[i]..<nums[i]+nums[i+1])
        }

        return seedRanges
    }

    private enum class State { IDLE, S2S, S2F, F2W, W2L, L2T, T2H, H2L }
    private fun getMappings(input: String): List<MutableList<Mapping>> {
        val seedToSoilMappings = mutableListOf<Mapping>()
        val soilToFertilizerMappings = mutableListOf<Mapping>()
        val fertilizerToWaterMappings = mutableListOf<Mapping>()
        val waterToLightMappings = mutableListOf<Mapping>()
        val lightToTemperatureMappings = mutableListOf<Mapping>()
        val temperatureToHumidityMappings = mutableListOf<Mapping>()
        val humidityToLocationMappings = mutableListOf<Mapping>()
        var state = State.IDLE
        for (line in input.trim().lines()) {
            if (line.startsWith("seeds: ")) continue

            when {
                line.startsWith("seed-to-soil") -> {
                    state = State.S2S
                    continue
                }
                line.startsWith("soil-to-fertilizer") -> {
                    state = State.S2F
                    continue
                }
                line.startsWith("fertilizer-to-water") -> {
                    state = State.F2W
                    continue
                }
                line.startsWith("water-to-light") -> {
                    state = State.W2L
                    continue
                }
                line.startsWith("light-to-temperature") -> {
                    state = State.L2T
                    continue
                }
                line.startsWith("temperature-to-humidity") -> {
                    state = State.T2H
                    continue
                }
                line.startsWith("humidity-to-location") -> {
                    state = State.H2L
                    continue
                }
            }

            state = when {
                line.isBlank() && state == State.S2S -> State.S2F
                line.isBlank() && state == State.S2F -> State.F2W
                line.isBlank() && state == State.F2W -> State.W2L
                line.isBlank() && state == State.W2L -> State.L2T
                line.isBlank() && state == State.L2T -> State.T2H
                line.isBlank() && state == State.T2H -> State.H2L

                else -> state
            }

            if (line.isNotBlank()) {
                val nums = line.split(" ").map { it.toLong() }
                val length = nums[2]
                val srcRange = nums[1]..nums[1]+length
                val dstRange = nums[0]..nums[0]+length
                when {
                    state == State.S2S -> seedToSoilMappings.add(Mapping(srcRange, dstRange))
                    state == State.S2F -> soilToFertilizerMappings.add(Mapping(srcRange, dstRange))
                    state == State.F2W -> fertilizerToWaterMappings.add(Mapping(srcRange, dstRange))
                    state == State.W2L -> waterToLightMappings.add(Mapping(srcRange, dstRange))
                    state == State.L2T -> lightToTemperatureMappings.add(Mapping(srcRange, dstRange))
                    state == State.T2H -> temperatureToHumidityMappings.add(Mapping(srcRange, dstRange))
                    state == State.H2L -> humidityToLocationMappings.add(Mapping(srcRange, dstRange))
                }
            }
        }

        return listOf(
            seedToSoilMappings,
            soilToFertilizerMappings,
            fertilizerToWaterMappings,
            waterToLightMappings,
            lightToTemperatureMappings,
            temperatureToHumidityMappings,
            humidityToLocationMappings
        )
    }
}

private data class Mapping(val inputRange: LongRange, val outputRange: LongRange) {
    fun evaluate(src: Long): Long {
        return when {
            src in inputRange -> {
                val offset = src - inputRange.first
                outputRange.first + offset
            }

            else -> src
        }
    }
}

private fun bigEval(src: Long, mappings: List<Mapping>): Long {
    for (mapping in mappings) {
        if (src in mapping.inputRange) {
            return mapping.evaluate(src)
        }
    }
    return src
}
