package cfsb.crimsoncoder.advent2023

import cfsb.crimsoncoder.advent2023.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 4")
class Day04Test {

    @Nested
    @DisplayName("Day 4: Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day04(resourceAsText("day04_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(13)
        }

        @Test
        fun answer() {
            val answer = Day04(resourceAsText("day04.txt")).solveP1()
            assertThat(answer).isEqualTo(21959)
        }
    }

    @Nested
    @DisplayName("Day 4: Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day04(resourceAsText("day04_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(30)
        }

        @Test
        fun answer() {
            val answer = Day04(resourceAsText("day04.txt")).solveP2()
            assertThat(answer).isEqualTo(5132675)
        }
    }
}