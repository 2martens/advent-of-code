package de.twomartens.adventofcode.day10.node

import kotlin.math.abs

data class VerticalNode(override val index: Pair<Int, Int>,
                        override var colour: NodeColour = NodeColour.UNKNOWN,
                        override val nodeType: NodeType = NodeType.VERTICAL) : AdjustableColourNode {
    override fun isConnectedTo(otherNode: Node): Boolean {
        return isVertical(otherNode)
    }

    private fun isVertical(otherNode: Node) =
            (abs(otherNode.index.first - index.first) == 1
                    && otherNode.index.second == index.second)

}
