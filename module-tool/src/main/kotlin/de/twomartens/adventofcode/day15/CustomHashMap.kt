package de.twomartens.adventofcode.day15

import java.util.LinkedList

data class Lens(val label: String, val focalLength: Int, val boxNumber: Int, val slotNumber: Int)

enum class Operation {
    INSERT,
    REMOVAL
}

class CustomHashMap(private val hashAlgorithm: HashAlgorithm) {
    private val boxLensLabels: MutableMap<Int, MutableMap<String, Int>> = mutableMapOf()
    val boxes: MutableMap<Int, MutableList<Lens>> = mutableMapOf()

    fun sumTotalFocusingPower(): Int {
        return boxes.values.flatten().sumOf { calculateFocusingPower(it) }
    }

    fun calculateFocusingPower(lens: Lens): Int {
        return (1 + lens.boxNumber) * lens.slotNumber * lens.focalLength
    }

    fun handleInputs(inputs: List<String>) {
        inputs.forEach { handleInput(it) }
    }

    private fun handleInput(input: String) {
        val operation = parseOperation(input)
        when (operation) {
            Operation.INSERT -> insert(input)
            Operation.REMOVAL -> remove(input)
        }
    }

    private fun parseOperation(input: String): Operation {
        return when {
            input.contains('=') -> Operation.INSERT
            input.endsWith('-') -> Operation.REMOVAL
            else -> throw IllegalArgumentException("Input must contain = or -")
        }
    }

    private fun insert(input: String) {
        val (label, focalLengthString) = input.split('=')
        val boxNumber = hashAlgorithm.calculateHash(label)

        val lenses = boxes.getOrDefault(boxNumber, LinkedList())
        val lensLabels = boxLensLabels.getOrDefault(boxNumber, mutableMapOf())
                .toMutableMap()
        if (lensLabels.containsKey(label)) {
            val index = lensLabels[label]!!
            val lens = Lens(label, focalLengthString.toInt(), boxNumber, index + 1)
            lenses[index] = lens
        } else {
            val lens = Lens(label, focalLengthString.toInt(), boxNumber,
                    lenses.lastIndex + 2)
            lenses.addLast(lens)
            lensLabels[label] = lenses.lastIndex
        }

        boxes[boxNumber] = lenses
        boxLensLabels[boxNumber] = lensLabels
    }

    private fun remove(input: String) {
        val label = input.substringBefore('-')
        val boxNumber = hashAlgorithm.calculateHash(label)

        var lenses = boxes.getOrDefault(boxNumber, mutableListOf())
        val lensLabels = boxLensLabels.getOrDefault(boxNumber, mutableMapOf())
                .toMutableMap()
        if (lensLabels.containsKey(label)) {
            val index = lensLabels[label]!!

            lenses = lenses.mapIndexed { existingIndex, lens ->
                val newLens =
                        if (existingIndex > index) {
                            Lens(lens.label, lens.focalLength, lens.boxNumber, lens.slotNumber - 1)
                        } else {
                            lens
                        }
                newLens
            }.toMutableList()

            lenses.removeAt(index)
            lensLabels.remove(label)

            lensLabels.forEach { (existingLabel, existingIndex) ->
                if (existingIndex > index) {
                    lensLabels[existingLabel] = existingIndex - 1
                }
            }
        }

        boxes[boxNumber] = lenses
        boxLensLabels[boxNumber] = lensLabels
    }

}
