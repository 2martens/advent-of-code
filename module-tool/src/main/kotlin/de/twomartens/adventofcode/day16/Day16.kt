package de.twomartens.adventofcode.day16

import de.twomartens.adventofcode.day15.Day15
import de.twomartens.adventofcode.day15.HashAlgorithm
import de.twomartens.adventofcode.day16.graph.ActiveNode
import de.twomartens.adventofcode.day16.graph.Direction
import de.twomartens.adventofcode.day16.graph.Graph
import de.twomartens.adventofcode.day16.graph.GraphWalker
import mu.KotlinLogging
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import java.nio.file.Files
import kotlin.io.path.toPath

@ShellComponent
class Day16 {
    @ShellMethod(key = ["day-16-part-1"])
    fun day16Part1(): String {
        val lines = readInput()
        val graph = Graph.of(lines)
        val walker = GraphWalker()
        val startPosition = ActiveNode(0, 0, Direction.RIGHT)
        val modifiedGraph = walker.energizeGraph(graph, startPosition)

        return modifiedGraph.countEnergizedTiles().toString()
    }

    @ShellMethod(key = ["day-16-part-2"])
    fun day16Part2(): String {
        val lines = readInput()
        val walker = GraphWalker()
        val graph = Graph.of(lines)
        val graphs = walker.energizeFromAllStartPositions(graph)
        val maxEnergizedTiles = graphs.maxOf { it.countEnergizedTiles() }

        return maxEnergizedTiles.toString()
    }

    private fun readInput(filename: String = "day-16.txt"): List<String> {
        val url = Day15::class.java.classLoader.getResource("input/$filename")
        if (url === null) {
            return listOf()
        }

        return Files.readAllLines(url.toURI().toPath())
    }


}