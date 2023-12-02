package cfsb.crimsoncoder.advent2023

import cfsb.crimsoncoder.advent2023.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day02Test {

    @Nested
    @DisplayName("Day 2: Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day02(resourceAsText("day02_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(8)
        }

        @Test
        fun answer() {
            val answer = Day02(resourceAsText("day02.txt")).solveP1()
            assertThat(answer).isEqualTo(2283)
        }
    }

    @Nested
    @DisplayName("Day 2: Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day02(resourceAsText("day02_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(2286)
        }

        @Test
        fun answer() {
            val answer = Day02(resourceAsText("day02.txt")).solveP2()
            assertThat(answer).isEqualTo(78669)
        }
    }
}