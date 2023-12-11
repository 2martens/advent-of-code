package de.twomartens.adventofcode.day11.node

enum class NodeType(val identifier: String) {
    EMPTY("."),
    GALAXY("#"),
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