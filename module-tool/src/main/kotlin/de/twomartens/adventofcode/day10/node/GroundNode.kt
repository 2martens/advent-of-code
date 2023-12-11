package de.twomartens.adventofcode.day10.node

data class GroundNode(override val index: Pair<Int, Int>,
                      override var colour: NodeColour = NodeColour.UNKNOWN,
                      override val nodeType: NodeType = NodeType.GROUND) : AdjustableColourNode {
    override fun isConnectedTo(otherNode: Node): Boolean {
        return false
    }
}
