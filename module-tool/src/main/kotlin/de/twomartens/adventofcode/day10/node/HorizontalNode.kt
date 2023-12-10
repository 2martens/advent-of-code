package de.twomartens.adventofcode.day10.node

import kotlin.math.abs

data class HorizontalNode(val index: Pair<Int, Int>) : Node {
    override fun index(): Pair<Int, Int> {
        return index
    }

    override fun isConnectedTo(otherNode: Node): Boolean {
        return isHorizontal(otherNode)
    }

    private fun isHorizontal(otherNode: Node) =
            (abs(otherNode.index().second - index.second) == 1
                    && otherNode.index().first == index.first)

}
