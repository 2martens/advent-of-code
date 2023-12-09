package de.twomartens.adventofcode.day9

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.toPath

@ShellComponent
class Day9 {
    @ShellMethod(key = ["day-9-part-1"])
    fun day9Part1(): String {
        val url = Day9::class.java.classLoader.getResource("input/day-9.txt")
        if (url === null) {
            return ""
        }
        val lines = Files.readAllLines(url.toURI().toPath())
        val convertedLines = lines.map {
            convertStringToIntSequence(it)
        }
        val predictor = SequencePredictor()
        val sum = predictor.calculateSumOfPredictedValues(convertedLines)

        return sum.toString()
    }

    private fun convertStringToIntSequence(row: String): List<Int> {
        return row.split(' ').map {
            it.toInt()
        }
    }
}