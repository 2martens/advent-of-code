package de.twomartens.adventofcode.day10.node

data class BorderNode(override val index: Pair<Int, Int>,
                      override val colour: NodeColour = NodeColour.BORDER) : Node {
    override fun isConnectedTo(otherNode: Node): Boolean {
        return false
    }
}