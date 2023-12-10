package de.twomartens.adventofcode.day10.node

import kotlin.math.abs

data class VerticalNode(val index: Pair<Int, Int>) : Node {
    override fun index(): Pair<Int, Int> {
        return index
    }

    override fun isConnectedTo(otherNode: Node): Boolean {
        return isVertical(otherNode)
    }

    private fun isVertical(otherNode: Node) =
            (abs(otherNode.index().first - index.first) == 1
                    && otherNode.index().second == index.second)

}
