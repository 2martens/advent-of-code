package de.twomartens.adventofcode.day10.node

data class SouthWestNode(override val index: Pair<Int, Int>,
                         override var colour: NodeColour = NodeColour.UNKNOWN) : AdjustableColourNode {
    override fun isConnectedTo(otherNode: Node): Boolean {
        return isSouth(otherNode) || isWest(otherNode)
    }

    private fun isSouth(otherNode: Node) =
            (otherNode.index.first == index.first + 1
                    && otherNode.index.second == index.second)

    private fun isWest(otherNode: Node) =
            (otherNode.index.first == index.first
                    && otherNode.index.second == index.second - 1)

}
