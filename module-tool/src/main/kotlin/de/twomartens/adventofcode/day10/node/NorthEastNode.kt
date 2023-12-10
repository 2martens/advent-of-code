package de.twomartens.adventofcode.day10.node

data class NorthEastNode(val index: Pair<Int, Int>) : Node {
    override fun index(): Pair<Int, Int> {
        return index
    }

    override fun isConnectedTo(otherNode: Node): Boolean {
        return isNorth(otherNode) || isEast(otherNode)
    }

    private fun isNorth(otherNode: Node) =
            (otherNode.index().first == index.first - 1
                    && otherNode.index().second == index.second)

    private fun isEast(otherNode: Node) =
            (otherNode.index().first == index.first
                    && otherNode.index().second == index.second + 1)

}
