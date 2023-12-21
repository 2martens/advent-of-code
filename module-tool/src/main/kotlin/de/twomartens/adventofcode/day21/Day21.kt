package de.twomartens.adventofcode.day21

import de.twomartens.adventofcode.day21.graph.Graph
import de.twomartens.adventofcode.day21.graph.GraphWalker
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.util.StopWatch
import java.nio.file.Files
import kotlin.io.path.toPath

@ShellComponent
class Day21 {
    @ShellMethod(key = ["day-21-part-1"])
    fun day21Part1(): String {
        val stopWatch = StopWatch()
        stopWatch.start()
        val lines = readInput()
        val graph = Graph.of(lines)
        val walker = GraphWalker()
        val reachableNodes = walker.findNumberOfReachableNodes(graph, 64)
        stopWatch.stop()

        return reachableNodes.toString() + ", took ${stopWatch.totalTimeSeconds} seconds\n"
    }

    @ShellMethod(key = ["day-21-part-2"])
    fun day21Part2(): String {
        val stopWatch = StopWatch()
        stopWatch.start()
        val lines = readInput()
        val graph = Graph.of(lines)
        val walker = GraphWalker()
        val reachableNOdes = walker.findNumberOfReachableNodes(graph, 26501365)
        return ", took ${stopWatch.totalTimeSeconds} seconds"
    }

    private fun readInput(filename: String = "day-21.txt"): List<String> {
        val url = Day21::class.java.classLoader.getResource("input/$filename")
        if (url === null) {
            return listOf()
        }

        return Files.readAllLines(url.toURI().toPath())
    }


}