package de.twomartens.adventofcode.day10.node

data class NorthWestNode(override val index: Pair<Int, Int>,
                         override var colour: NodeColour = NodeColour.UNKNOWN,
                         override val nodeType: NodeType = NodeType.NORTH_WEST) : AdjustableColourNode {
    override fun isConnectedTo(otherNode: Node): Boolean {
        return isNorth(otherNode) || isWest(otherNode)
    }

    private fun isNorth(otherNode: Node) =
            (otherNode.index.first == index.first - 1
                    && otherNode.index.second == index.second)

    private fun isWest(otherNode: Node) =
            (otherNode.index.first == index.first
                    && otherNode.index.second == index.second - 1)

}
