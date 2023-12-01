package cfsb.crimsoncoder.advent2023

import cfsb.crimsoncoder.advent2023.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day01Test {

    @Nested
    @DisplayName("Day 1: Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day01(resourceAsText("day01_ex1.txt")).solveP1()
            assertThat(answer).isEqualTo(142)
        }

        @Test
        fun answer() {
            val answer = Day01(resourceAsText("day01.txt")).solveP1()
            assertThat(answer).isEqualTo(53386)
        }
    }

    @Nested
    @DisplayName("Day 1: Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day01(resourceAsText("day01_ex2.txt")).solveP2()
            assertThat(answer).isEqualTo(281)
        }

        @Test
        fun answer() {
            val answer = Day01(resourceAsText("day01.txt")).solveP2()
            assertThat(answer).isEqualTo(53312)
        }
    }
}