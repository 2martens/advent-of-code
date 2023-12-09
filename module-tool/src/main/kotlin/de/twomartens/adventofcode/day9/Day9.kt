package de.twomartens.adventofcode.day9

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import java.nio.file.Files
import kotlin.io.path.toPath

@ShellComponent
class Day9 {
    @ShellMethod(key = ["day-9-part-1"])
    fun day9Part1(): String {
        val convertedLines = readLines()
        val predictor = SequencePredictor()
        val sum = predictor.calculateSumOfPredictedNextValues(convertedLines)

        return sum.toString()
    }

    @ShellMethod(key = ["day-9-part-2"])
    fun day9Part2(): String {
        val convertedLines = readLines()
        val predictor = SequencePredictor()
        val sum = predictor.calculateSumOfPredictedPreviousValues(convertedLines)

        return sum.toString()
    }

    private fun readLines(): List<List<Int>> {
        val url = Day9::class.java.classLoader.getResource("input/day-9.txt")
        if (url === null) {
            return listOf()
        }
        val lines = Files.readAllLines(url.toURI().toPath())
        return lines.map {
            convertStringToIntSequence(it)
        }
    }

    private fun convertStringToIntSequence(row: String): List<Int> {
        return row.split(' ').map {
            it.toInt()
        }
    }
}