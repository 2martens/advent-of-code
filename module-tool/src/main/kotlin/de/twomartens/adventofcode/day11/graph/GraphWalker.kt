package de.twomartens.adventofcode.day11.graph

import mu.KotlinLogging
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

        val checksum = graph.galaxies.size * (graph.galaxies.size - 1) / 2
        log.debug("number of galaxies: {}", graph.galaxies.size)
        log.debug("expected number of pairs: {}", checksum)
        log.debug("actual number of pairs: {}", galaxyPairs.size)

        return galaxyPairs
                .sumOf {
                    findDistanceBetweenGalaxies(it.galaxy1, it.galaxy2)
                }
    }

    fun findDistanceBetweenGalaxies(galaxy1: Pair<Int, Int>, galaxy2: Pair<Int, Int>): Int {
        return abs(galaxy1.first - galaxy2.first) +
                abs(galaxy1.second - galaxy2.second)
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}