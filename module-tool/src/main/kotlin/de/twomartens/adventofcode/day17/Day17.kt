package de.twomartens.adventofcode.day17

import de.twomartens.adventofcode.day17.graph.Graph
import de.twomartens.adventofcode.day17.graph.GraphWalker
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.util.StopWatch
import java.nio.file.Files
import kotlin.io.path.toPath

@ShellComponent
class Day17 {
    @ShellMethod(key = ["day-17-part-1"])
    fun day17Part1(): String {
        val stopWatch = StopWatch()
        stopWatch.start()
        val lines = readInput()
        val graph = Graph.of(lines)
        val walker = GraphWalker()
        val heatLoss = walker.findMinimalHeatLoss(graph, 1, 3)
        stopWatch.stop()

        return heatLoss.toString() + ", took ${stopWatch.totalTimeSeconds} seconds"
    }

    @ShellMethod(key = ["day-17-part-2"])
    fun day17Part2(): String {
        val stopWatch = StopWatch()
        stopWatch.start()
        val lines = readInput()
        val graph = Graph.of(lines)
        val walker = GraphWalker()
        val heatLoss = walker.findMinimalHeatLoss(graph, 4, 10)
        stopWatch.stop()

        return heatLoss.toString() + ", took ${stopWatch.totalTimeSeconds} seconds"
    }

    private fun readInput(filename: String = "day-17.txt"): List<String> {
        val url = Day17::class.java.classLoader.getResource("input/$filename")
        if (url === null) {
            return listOf()
        }

        return Files.readAllLines(url.toURI().toPath())
    }


}