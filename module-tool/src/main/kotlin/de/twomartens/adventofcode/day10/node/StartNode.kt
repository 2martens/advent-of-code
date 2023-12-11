package de.twomartens.adventofcode.day10.node

import kotlin.math.abs

data class StartNode(override val index: Pair<Int, Int>,
                     override var colour: NodeColour = NodeColour.LOOP,
                     override val nodeType: NodeType = NodeType.START_NODE) : Node {
    override fun isConnectedTo(otherNode: Node): Boolean {
        return isHorizontal(otherNode) || isVertical(otherNode)
    }

    private fun isHorizontal(otherNode: Node) =
            (abs(otherNode.index.second - index.second) == 1
                    && otherNode.index.first == index.first)

    private fun isVertical(otherNode: Node) =
            (abs(otherNode.index.first - index.first) == 1
                    && otherNode.index.second == index.second)
}
