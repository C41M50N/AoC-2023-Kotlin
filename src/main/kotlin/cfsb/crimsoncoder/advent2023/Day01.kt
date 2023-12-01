package cfsb.crimsoncoder.advent2023

class Day01(input: String) {
    private val lines = parseInput(input)

    fun solveP1(): Int =
        lines.map { line -> "${line.first { it.isDigit() }}${line.last { it.isDigit() }}" }.sumOf { it.toInt() }

    fun solveP2(): Int {
        val out = lines.map { line ->
            var newLine = ""
            var l = 0
            var r = 1
            while (r <= line.length) {
                val window = line.substring(l,r)
                if (window.contains("""\d""".toRegex())) {
                    newLine += window.first { it.isDigit() }
                    l = r
                    r += 1
                } else if (window.contains("one|two|three|four|five|six|seven|eight|nine".toRegex())) {
                    var s = window
                    s = s.replace("one", "1")
                    s = s.replace("two", "2")
                    s = s.replace("three", "3")
                    s = s.replace("four", "4")
                    s = s.replace("five", "5")
                    s = s.replace("six", "6")
                    s = s.replace("seven", "7")
                    s = s.replace("eight", "8")
                    s = s.replace("nine", "9")
                    newLine += s.first { it.isDigit() }
                    l = r - 1
                    r += 1
                } else {
                    r++
                }
            }
            newLine
        }

        println(out)

        val nums = out.map { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}"
        }

        println(nums)

        return nums.sumOf { it.toInt() }
    }

    private fun parseInput(input: String): List<String> {
        return input
            .trim()
            .lines()
    }
}