package de.twomartens.adventofcode.day11.graph

data class GalaxyPair(val galaxy1: Pair<Int, Int>, val galaxy2: Pair<Int, Int>) {
    override fun equals(other: Any?): Boolean {
        if (other !is GalaxyPair) {
            return false
        }
        return indicesMatchSameOrder(other) || indicesMatchReverseOrder(other)
    }

    private fun indicesMatchReverseOrder(other: GalaxyPair) =
            galaxy1 == other.galaxy2 && galaxy2 == other.galaxy1

    private fun indicesMatchSameOrder(other: GalaxyPair) =
            galaxy1 == other.galaxy1 && galaxy2 == other.galaxy2

    override fun hashCode(): Int {
        return galaxy1.hashCode() + galaxy2.hashCode()
    }
}