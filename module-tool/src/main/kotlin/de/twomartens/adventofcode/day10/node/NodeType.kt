package de.twomartens.adventofcode.day10.node

enum class NodeType(val identifier: String) {
    START_NODE("S"),
    NORTH_WEST("J"),
    NORTH_EAST("L"),
    SOUTH_WEST("7"),
    SOUTH_EAST("F"),
    HORIZONTAL("-"),
    VERTICAL("|"),
    GROUND("."),
    BORDER("B");

    companion object {
        fun of(identifier: String): NodeType {
            val foundNode = entries.find {
                it.identifier == identifier
            }

            if (foundNode == null) {
                throw IllegalArgumentException("Invalid identifier provided: $identifier")
            }

            return foundNode
        }
    }
}