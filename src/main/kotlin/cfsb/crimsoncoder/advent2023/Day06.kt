package cfsb.crimsoncoder.advent2023

import kotlin.math.ceil
import kotlin.math.floor

class Day06(private val input: String) {

    fun solveP1(): Long {
        val races = parseInput(input)
        return races.fold(1) { total, race -> total * race.getNumberOfWinningConfigs() }
    }

    fun solveP2(): Long {
        val race = parseInput2(input)
        return race.getNumberOfWinningConfigs()
    }

    private fun parseInput(input: String): List<Race> {
        val times = input
            .trim()
            .lines()[0]
            .substringAfter("Time: ")
            .trim()
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.trim().toLong() }

        val distances = input
            .trim()
            .lines()[1]
            .substringAfter("Distance: ")
            .trim()
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.trim().toLong() }

        return times.mapIndexed { index, time -> Race(time, distances[index]) }
    }

    private fun parseInput2(input: String): Race {
        val time = input
            .trim()
            .lines()[0]
            .substringAfter("Time: ")
            .trim()
            .replace(" ", "")
            .toLong()

        val distance = input
            .trim()
            .lines()[1]
            .substringAfter("Distance: ")
            .trim()
            .replace(" ", "")
            .toLong()

        return Race(time, distance)
    }
}

private data class Race(
    val timeLimit: Long, // a race lasts `timeLimit` milliseconds
    val distanceRecord: Long
    ) {

    private fun getDistance(buttonPressTime: Long): Long {
        var distance = 0L
        var timeRemaining = timeLimit - buttonPressTime
        while (timeRemaining > 0) {
            distance += buttonPressTime
            timeRemaining--
        }
        return distance
    }

    fun getNumberOfWinningConfigs(): Long {
        var count = 0L
        val tolerance = ceil(timeLimit * 0.001).toLong()
        val midpoint = floor(timeLimit / 2.0).toLong()
        val left = midpoint - tolerance
        val right = midpoint + tolerance
        for (buttonPressTime in left..right) {
            if (this.getDistance(buttonPressTime) > distanceRecord) {
                count++
            }
        }
        return count
    }
}

