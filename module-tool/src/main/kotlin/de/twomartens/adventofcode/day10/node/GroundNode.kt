package de.twomartens.adventofcode.day10.node

data class GroundNode(val index: Pair<Int, Int>) : Node {
    override fun index(): Pair<Int, Int> {
        return index
    }

    override fun isConnectedTo(otherNode: Node): Boolean {
        return false
    }
}
