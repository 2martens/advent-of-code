package de.twomartens.adventofcode.day15

import mu.KotlinLogging
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import java.nio.file.Files
import kotlin.io.path.toPath

@ShellComponent
class Day15 {
    @ShellMethod(key = ["day-15-part-1"])
    fun day15Part1(): String {
        val segments = readInput()
        val hashAlgorithm = HashAlgorithm()
        val sum = hashAlgorithm.calculateSumOfSegmentHashes(segments)

        return sum.toString()
    }

    @ShellMethod(key = ["day-15-part-2"])
    fun day15Part2(): String {
        val segments = readInput()
        val hashAlgorithm = HashAlgorithm()
        val hashMap = CustomHashMap(hashAlgorithm)
        hashMap.handleInputs(segments)

        return hashMap.sumTotalFocusingPower().toString()
    }

    private fun readInput(filename: String = "day-15.txt"): List<String> {
        val url = Day15::class.java.classLoader.getResource("input/$filename")
        if (url === null) {
            return listOf()
        }

        return Files.readAllLines(url.toURI().toPath()).get(0).split(',')
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}