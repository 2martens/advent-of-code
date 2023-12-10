package de.twomartens.adventofcode.day10.node

data class NorthWestNode(val index: Pair<Int, Int>) : Node {
    override fun index(): Pair<Int, Int> {
        return index
    }

    override fun isConnectedTo(otherNode: Node): Boolean {
        return isNorth(otherNode) || isWest(otherNode)
    }

    private fun isNorth(otherNode: Node) =
            (otherNode.index().first == index.first - 1
                    && otherNode.index().second == index.second)

    private fun isWest(otherNode: Node) =
            (otherNode.index().first == index.first
                    && otherNode.index().second == index.second - 1)

}
