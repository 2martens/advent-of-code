package de.twomartens.adventofcode.day11.graph

import de.twomartens.adventofcode.day11.node.GalaxyNode
import kotlin.math.abs

class GraphWalker {
    fun sumAllDistancesBetweenGalaxies(graph: Graph): Int {
        val galaxyPairs: MutableCollection<GalaxyPair> = mutableSetOf()

        for (galaxy1 in graph.galaxies) {
            for (galaxy2 in graph.galaxies) {
                if (galaxy1 != galaxy2) {
                    galaxyPairs.add(GalaxyPair(galaxy1, galaxy2))
                }
            }
        }

        return galaxyPairs
                .sumOf {
                    findDistanceBetweenGalaxies(it.galaxy1, it.galaxy2)
                }
    }

    fun findDistanceBetweenGalaxies(galaxy1: Pair<Int, Int>, galaxy2: Pair<Int, Int>): Int {
        return abs(galaxy1.first - galaxy2.first) +
                abs(galaxy1.second - galaxy2.second)
    }
}