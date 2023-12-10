package de.twomartens.adventofcode.day10.node

interface Node {
    val index: Pair<Int, Int>
    val colour: NodeColour

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
        fun of(nodeType: NodeType, index: Pair<Int, Int>): Node {
            val foundNode = when (nodeType) {
                NodeType.START_NODE -> StartNode(index)
                NodeType.NORTH_WEST -> NorthWestNode(index)
                NodeType.NORTH_EAST -> NorthEastNode(index)
                NodeType.SOUTH_WEST -> SouthWestNode(index)
                NodeType.SOUTH_EAST -> SouthEastNode(index)
                NodeType.HORIZONTAL -> HorizontalNode(index)
                NodeType.VERTICAL -> VerticalNode(index)
                NodeType.GROUND -> GroundNode(index)
            }

            return foundNode
        }
    }
}