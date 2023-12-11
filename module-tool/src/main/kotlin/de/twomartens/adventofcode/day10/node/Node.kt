package de.twomartens.adventofcode.day10.node

interface Node {
    val index: Pair<Int, Int>
    val colour: NodeColour
    val nodeType: NodeType

    fun isConnectedTo(otherNode: Node): Boolean

    fun northIndex(): Pair<Int, Int> {
        return Pair(index.first - 1, index.second)
    }

    fun eastIndex(): Pair<Int, Int> {
        return Pair(index.first, index.second + 1)
    }

    fun southIndex(): Pair<Int, Int> {
        return Pair(index.first + 1, index.second)
    }

    fun westIndex(): Pair<Int, Int> {
        return Pair(index.first, index.second - 1)
    }

    companion object {
        fun of(nodeType: NodeType, index: Pair<Int, Int>, colour: NodeColour = NodeColour.UNKNOWN): Node {
            val foundNode = when (nodeType) {
                NodeType.START_NODE -> StartNode(index)
                NodeType.NORTH_WEST -> NorthWestNode(index, colour)
                NodeType.NORTH_EAST -> NorthEastNode(index, colour)
                NodeType.SOUTH_WEST -> SouthWestNode(index, colour)
                NodeType.SOUTH_EAST -> SouthEastNode(index, colour)
                NodeType.HORIZONTAL -> HorizontalNode(index, colour)
                NodeType.VERTICAL -> VerticalNode(index, colour)
                NodeType.GROUND -> GroundNode(index, colour)
                NodeType.BORDER -> BorderNode(index)
            }

            return foundNode
        }
    }
}