package cfsb.crimsoncoder.advent2023

import kotlin.time.Duration

class Day06(input: String) {
    private val races = parseRaces(input)

    fun solveP1(): Long {
        // for each race, simulate on hold times from 1 to record distance,
        // counting how many simulations are successful
        return races.map { race ->
            (1..race.duration).count { holdTime -> race.simulate(holdTime) }
        }.reduce(Int::times).toLong()
    }

    private val race = parseRace(input)

    fun solveP2(): Long {
        return (1..race.duration).count { holdTime -> race.simulate(holdTime) }.toLong()
    }

    private fun parseRaces(input: String): List<Race> {
        val times = input.trim().lines().first()
            .substringAfter("Time:").trim()
            .split(" ").filter { it.isNotBlank() }
            .map { it.toLong() }

        val distances = input.trim().lines()[1]
            .substringAfter("Distance:").trim()
            .split(" ").filter { it.isNotBlank() }
            .map { it.toLong() }

        return times.zip(distances).map { (time, distance) -> Race(time, distance) }
    }

    private fun parseRace(input: String): Race {
        val time = input.trim().lines().first()
            .substringAfter("Time:").trim()
            .split(" ").filter { it.isNotBlank() }
            .joinToString("").toLong()

        val distance = input.trim().lines()[1]
            .substringAfter("Distance:").trim()
            .split(" ").filter { it.isNotBlank() }
            .joinToString("").toLong()

        return Race(time, distance)
    }
}

private data class Race(
    val duration: Long,
    val recordDistance: Long
) {
    fun simulate(holdTime: Long): Boolean {
        return ((duration - holdTime) * holdTime) > recordDistance
    }
}

// race duration = 7
// hold time -> distance
// 2 -> (7-2)*2 = 10
// 3 -> (7-3)*3 = 12
// 4 -> (7-4)*4 = 12
// 5 -> (7-5)*5 = 10
// 6 -> (7-6)*6 = 6