package de.twomartens.adventofcode.day11

import de.twomartens.adventofcode.day11.graph.Graph
import de.twomartens.adventofcode.day11.graph.GraphWalker
import mu.KotlinLogging
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import java.nio.file.Files
import kotlin.io.path.toPath

@ShellComponent
class Day11 {
    @ShellMethod(key = ["day-11-part-1"])
    fun day11Part1(): String {
        val lines = readLines()
        val graph = Graph.of("day11", lines, 2)
        val walker = GraphWalker()
        return walker.sumAllDistancesBetweenGalaxies(graph).toString()
    }

    @ShellMethod(key = ["day-11-part-2"])
    fun day11Part2(@ShellOption(defaultValue = "1000000") factor: Int = 1_000_000): String {
        val lines = readLines()
        val graph = Graph.of("day11", lines, factor)
        val walker = GraphWalker()
        return walker.sumAllDistancesBetweenGalaxies(graph).toString()
    }

    private fun readLines(): List<String> {
        val url = Day11::class.java.classLoader.getResource("input/day-11.txt")
        if (url === null) {
            return listOf()
        }

        return Files.readAllLines(url.toURI().toPath())
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}