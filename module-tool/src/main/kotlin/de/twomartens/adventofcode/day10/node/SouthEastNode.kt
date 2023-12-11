package de.twomartens.adventofcode.day10.node

data class SouthEastNode(override val index: Pair<Int, Int>,
                         override var colour: NodeColour = NodeColour.UNKNOWN,
                         override val nodeType: NodeType = NodeType.SOUTH_EAST) : AdjustableColourNode {
    override fun isConnectedTo(otherNode: Node): Boolean {
        return isSouth(otherNode) || isEast(otherNode)
    }

    private fun isSouth(otherNode: Node) =
            (otherNode.index.first == index.first + 1
                    && otherNode.index.second == index.second)

    private fun isEast(otherNode: Node) =
            (otherNode.index.first == index.first
                    && otherNode.index.second == index.second + 1)

}
