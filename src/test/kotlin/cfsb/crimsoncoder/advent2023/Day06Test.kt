package cfsb.crimsoncoder.advent2023

import cfsb.crimsoncoder.advent2023.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 6")
class Day06Test {

    @Nested
    @DisplayName("Day 6: Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day06(resourceAsText("day06_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(288)
        }

        @Test
        fun answer() {
            val answer = Day06(resourceAsText("day06.txt")).solveP1()
            assertThat(answer).isEqualTo(4568778)
        }
    }

    @Nested
    @DisplayName("Day 6: Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day06(resourceAsText("day06_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(71503)
        }

        @Test
        fun answer() {
            val answer = Day06(resourceAsText("day06.txt")).solveP2()
            assertThat(answer).isEqualTo(28973936L)
        }
    }
}