package de.twomartens.adventofcode.day10.node

import kotlin.math.abs

data class HorizontalNode(override val index: Pair<Int, Int>,
                          override var colour: NodeColour = NodeColour.UNKNOWN) : AdjustableColourNode {
    override fun isConnectedTo(otherNode: Node): Boolean {
        return isHorizontal(otherNode)
    }

    private fun isHorizontal(otherNode: Node) =
            (abs(otherNode.index.second - index.second) == 1
                    && otherNode.index.first == index.first)

}
