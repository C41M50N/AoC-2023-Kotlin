package cfsb.crimsoncoder.advent2023

import cfsb.crimsoncoder.advent2023.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day05Test {

    @Nested
    @DisplayName("Day 5: Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day05(resourceAsText("day05_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(35)
        }

        @Test
        fun answer() {
            val answer = Day05(resourceAsText("day05.txt")).solveP1()
            assertThat(answer).isEqualTo(662197086L)
        }
    }

    @Nested
    @DisplayName("Day 5: Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day05(resourceAsText("day05_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(46)
        }

        @Test
        fun answer() {
            val answer = Day05(resourceAsText("day05.txt")).solveP2()
            assertThat(answer).isEqualTo(0) // < 52510810L
        }
    }
}