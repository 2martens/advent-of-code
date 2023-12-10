package de.twomartens.adventofcode.day10

import de.twomartens.adventofcode.day10.graph.Graph
import de.twomartens.adventofcode.day10.graph.GraphWalker
import mu.KotlinLogging
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import java.nio.file.Files
import kotlin.io.path.toPath

@ShellComponent
class Day10 {
    @ShellMethod(key = ["day-10-part-1"])
    fun day10Part1(): String {
        val lines = readLines()
        val graph = Graph.of("day10", lines)
        val walker = GraphWalker()
        val furthestDistance = walker.findFurthestDistance(graph)

        return furthestDistance.toString()
    }

    @ShellMethod(key = ["day-10-part-2"])
    fun day10Part2(): String {
        val lines = readLines()
        val graph = Graph.of("day10", lines)
        val walker = GraphWalker()
        val numberOfContainedNodes = walker.findNumberOfContainedNodes(graph)
        log.debug(graph.printGraphString())

        return numberOfContainedNodes.toString()
    }

    private fun readLines(): List<String> {
        val url = Day10::class.java.classLoader.getResource("input/day-10.txt")
        if (url === null) {
            return listOf()
        }

        return Files.readAllLines(url.toURI().toPath())
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}