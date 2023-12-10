package de.twomartens.adventofcode.day10.node

data class GroundNode(override val index: Pair<Int, Int>,
                      override var colour: NodeColour = NodeColour.UNKNOWN) : AdjustableColourNode {
    override fun isConnectedTo(otherNode: Node): Boolean {
        return false
    }
}
