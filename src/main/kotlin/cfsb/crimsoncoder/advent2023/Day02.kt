package cfsb.crimsoncoder.advent2023

class Day02(input: String) {
    private val games = parseInput(input)

    fun solveP1(): Int {
        val sim = CubeSimulator(12, 13, 14, games)
        val validGames = sim.getValidGames()
        println("Valid Games: $validGames")
        return validGames.sumOf { it.id }
    }

    fun solveP2(): Int {
        return games.sumOf { it.red * it.green * it.blue }
    }

    private fun parseInput(input: String): List<Game> {
        return input
            .trim()
            .lines()
            .mapIndexed { index, line ->
                var red = 0
                var green = 0
                var blue = 0

                val segments = line.substringAfter(": ").split("; ")
                for (segment in segments) {
                    val cubeData = segment.split(", ")
                    for (data in cubeData) {
                        val value = data.split(" ")[0].toInt()
                        val color = data.split(" ")[1]
                        when (color) {
                            "red" -> {
                                if (value > red) red = value
                            }
                            "green" -> {
                                if (value > green) green = value
                            }
                            "blue" -> {
                                if (value > blue) blue = value
                            }
                        }
                    }
                }

                Game(id=index+1, red, green, blue)
            }
    }
}

private class Game(val id: Int, val red: Int = 0, val green: Int = 0, val blue: Int = 0) {
    override fun toString(): String = "Game(id=$id, red=$red, green=$green, blue=$blue)"
}

private class CubeSimulator (
    private val maxRed: Int,
    private val maxGreen: Int,
    private val maxBlue: Int,
    private val games: List<Game>
) {

    fun getValidGames(): List<Game> {
        return games.filter { it.red <= maxRed && it.green <= maxGreen && it.blue <= maxBlue }
    }
}
