package cfsb.crimsoncoder.advent2023

import cfsb.crimsoncoder.advent2023.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 3")
class Day03Test {

    @Nested
    @DisplayName("Day 3: Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day03(resourceAsText("day03_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(4361)
        }

        @Test
        fun answer() {
            val answer = Day03(resourceAsText("day03.txt")).solveP1()
            assertThat(answer).isEqualTo(549908)
        }
    }

    @Nested
    @DisplayName("Day 3: Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day03(resourceAsText("day03_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(467835)
        }

        @Test
        fun answer() {
            val answer = Day03(resourceAsText("day03.txt")).solveP2()
            assertThat(answer).isEqualTo(81166799)
        }
    }
}