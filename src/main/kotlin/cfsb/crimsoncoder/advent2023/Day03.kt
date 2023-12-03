package cfsb.crimsoncoder.advent2023

class Day03(input: String) {
    private val symbols = parseSymbols(input)
    private val partNumbers = parsePartNumbers(input)

    fun solveP1(): Int {
        return partNumbers.filter { partNumber -> symbols.any { partNumber.isAdjacent(it) } }.sumOf { it.value }
    }

    fun solveP2(): Int {
        // set of all pairs of shared adjacents
        val pairsOfSharedAdjacents = mutableSetOf<Pair<PartNumber, PartNumber>>()
        for (symbol in symbols) {
            var count = 0
            val sharedAdjacentPartNumbers = mutableListOf<PartNumber>()
            for (partNumber in partNumbers) {
                if (symbol.value == '*' && partNumber.isAdjacent(symbol)) {
                    sharedAdjacentPartNumbers.add(partNumber)
                    count++
                }
            }

            if (count == 2) {
                sharedAdjacentPartNumbers.indices.forEach() {
                    i -> sharedAdjacentPartNumbers.indices.minus(0..i).forEach() {
                    j -> pairsOfSharedAdjacents.add(sharedAdjacentPartNumbers[i] to sharedAdjacentPartNumbers[j]) }
                }
            }
        }
        return pairsOfSharedAdjacents.sumOf { it.first.value * it.second.value }
    }

    private fun parseSymbols(input: String): List<Symbol> {
        val symbols = mutableListOf<Symbol>()
        for ((y, line) in input.trim().lines().withIndex()) {
            for ((x, char) in line.withIndex()) {
                when {
                    char == '.' -> {}
                    char.isDigit() -> {}
                    else -> {
                        symbols.add(Symbol(Position(x,y), char))
                    }
                }
            }
        }
        return symbols
    }

    enum class State { IDLE, PROCESSING_DIGIT }
    private fun parsePartNumbers(input: String): List<PartNumber> {
        val partNumbers = mutableListOf<PartNumber>()
        for ((y, line) in input.trim().lines().withIndex()) {
            var x = 0
            var position: Position = Position(0,0)
            var value: String = ""
            var state: State = State.IDLE
            while (x < line.length) {
                if (line[x].isDigit() && state == State.IDLE) {
                    position = Position(x,y)
                    value = "${line[x]}"
                    state = State.PROCESSING_DIGIT
                } else if (line[x].isDigit() && state == State.PROCESSING_DIGIT) {
                    value += line[x]
                } else if (!line[x].isDigit() && state == State.PROCESSING_DIGIT) {
                    partNumbers.add(PartNumber(position, value.toInt()))
                    state = State.IDLE
                }
                x++
            }

            if (state == State.PROCESSING_DIGIT) {
                partNumbers.add(PartNumber(position, value.toInt()))
                state = State.IDLE
            }
        }
        return partNumbers
    }

}

private typealias Position = Pair<Int, Int>
private data class Symbol(val position: Position, val value: Char) {
    override fun toString(): String = "Symbol(position=(${position.first}, ${position.second}), value=`$value`)"
}
private data class PartNumber(
    val position: Position, // left-most digit's position
    val value: Int
) {
    override fun toString(): String = "PartNumber(position=(${position.first}, ${position.second}), value=$value)"
}

private fun PartNumber.isAdjacent(symbol: Symbol): Boolean {
    val partNumberLength = this.value.toString().length
    val adjacentPositions = mutableListOf<Position>()

    // left, right, upper left diagonal, lower left diagonal, upper right diagonal, lower right diagonal
    val left = Position(this.position.first - 1, this.position.second)
    val right = Position(this.position.first + partNumberLength, this.position.second)
    val upperLeftDiag = Position(this.position.first - 1, this.position.second - 1)
    val lowerLeftDiag = Position(this.position.first - 1, this.position.second + 1)
    val upperRightDiag = Position(this.position.first + partNumberLength, this.position.second - 1)
    val lowerRightDiag = Position(this.position.first + partNumberLength, this.position.second + 1)
    adjacentPositions.addAll(listOf(left, right, upperLeftDiag, lowerLeftDiag, upperRightDiag, lowerRightDiag))

    for (offset in 1..partNumberLength) {
        val above = Position(this.position.first + offset - 1, this.position.second - 1)
        val below = Position(this.position.first + offset - 1, this.position.second + 1)
        adjacentPositions.addAll(listOf(above, below))
    }

    return adjacentPositions.any { it == symbol.position }
}
