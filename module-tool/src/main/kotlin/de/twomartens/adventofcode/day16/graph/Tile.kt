package de.twomartens.adventofcode.day16.graph

data class Tile(val operation: Operation,
                private var energized: Boolean,
                private val reached: MutableMap<Direction, Boolean>
) {
    val isEnergized: Boolean get() = energized
    val reachedDirections: Map<Direction, Boolean> get() = reached

    fun isReachedFrom(direction: Direction): Boolean {
        return reached[direction] == true
    }

    fun energizeFrom(direction: Direction) {
        reached[direction] = true
        energized = true
    }
}
