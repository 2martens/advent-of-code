package de.twomartens.adventofcode.day11.node

interface Node {
    val index: Pair<Int, Int>
    val nodeType: NodeType

    companion object {
        fun of(nodeType: NodeType, index: Pair<Int, Int>): Node {
            val foundNode = when (nodeType) {
                NodeType.EMPTY -> EmptySpaceNode(index)
                NodeType.GALAXY -> GalaxyNode(index)
            }

            return foundNode
        }
    }
}