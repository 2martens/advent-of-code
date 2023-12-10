package de.twomartens.adventofcode.day10

import de.twomartens.adventofcode.day10.graph.Graph
import de.twomartens.adventofcode.day10.graph.GraphWalker
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
        val farthestDistance = walker.findFarthestDistance(graph)

        return farthestDistance.toString()
    }

    private fun readLines(): List<String> {
        val url = Day10::class.java.classLoader.getResource("input/day-10.txt")
        if (url === null) {
            return listOf()
        }

        return Files.readAllLines(url.toURI().toPath())
    }
}