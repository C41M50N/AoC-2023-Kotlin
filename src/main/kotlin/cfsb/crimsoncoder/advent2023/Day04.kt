package cfsb.crimsoncoder.advent2023

import kotlin.math.pow

class Day04(input: String) {
    private val cards = parseInput(input)

    fun solveP1(): Int {
        return cards.sumOf { it.getPoints() }
    }

    fun solveP2(): Int {
        var totalCards = 0
        for (card in cards) {
            println("Processing Original Card ${card.id} ...")
            val count = card.getMatchingCount()
            println("Original Card ${card.id} has $count matches")

            if (count == 0) {
                totalCards++
                continue
            }

            totalCards += processCard(card.id, true)
            println("Total Cards after processing original card ${card.id}: $totalCards")
        }
        return totalCards
    }

    private fun processCard(id: Int, original: Boolean): Int {
        val card = cards.find { it.id == id }!!
        if (!original) println("Processing Copy Card ${card.id} ...")

        val count = card.getMatchingCount()
        if (count == 0) return 1

        if (!original) println("Copy Card ${card.id} has $count matches")

        var sum = 1
        for (i in 1..count) {
            sum += processCard(id = card.id + i, false)
        }
        return sum
    }

    private fun parseInput(input: String): List<Card> {
        return input
            .trim()
            .lines()
            .map { line ->
                val id = line.substringAfter("Card ").substringBefore(":").trim().toInt()
                val winningNumbers = line.substringAfter(": ").substringBefore(" | ")
                    .split(' ')
                    .filter { !(it.isBlank() || it.isEmpty()) }
                    .map { it.trim().toInt() }
                val numbers = line.substringAfter(" | ")
                    .split(' ')
                    .filter { !(it.isBlank() || it.isEmpty()) }
                    .map { it.trim().toInt() }

                Card(id, winningNumbers, numbers)
            }
    }
}

private data class Card(val id: Int, val winningNumbers: List<Int>, val numbers: List<Int>) {
    override fun toString(): String = "Card(id=$id, winningNumbers=$winningNumbers, numbers=$numbers)"

    fun getPoints(): Int {
        val count = numbers.count { winningNumbers.contains(it) }
        return 2.0.pow(count.toDouble() - 1).toInt()
    }

    fun getMatchingCount(): Int {
        return numbers.count { winningNumbers.contains(it) }
    }
}
