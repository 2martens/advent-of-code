package de.twomartens.adventofcode.day15

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CustomHashMapTest {
    @ParameterizedTest
    @MethodSource(value = ["getInputsAndLenses"])
    fun shouldHandleInputsCorrectly(inputs: String, finalLenses: List<Lens>) {
        val hashAlgorithm = HashAlgorithm()
        val hashMap = CustomHashMap(hashAlgorithm)

        hashMap.handleInputs(inputs.split(','))
        val lenses = hashMap.boxes.values.flatten()

        Assertions.assertThat(lenses).isEqualTo(finalLenses)
    }


    @ParameterizedTest
    @MethodSource(value = ["getLensesAndPowers"])
    fun shouldCalculateFocusingPower(lens: Lens, focusingPower: Int) {
        val hashAlgorithm = HashAlgorithm()
        val hashMap = CustomHashMap(hashAlgorithm)

        val computedValue = hashMap.calculateFocusingPower(lens)

        Assertions.assertThat(computedValue).isEqualTo(focusingPower)
    }

    companion object {
        @JvmStatic
        fun getInputsAndLenses(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(
                            "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7",
                            listOf(
                                    Lens("rn", 1, 0, 1),
                                    Lens("cm", 2, 0, 2),
                                    Lens("ot", 7, 3, 1),
                                    Lens("ab", 5, 3, 2),
                                    Lens("pc", 6, 3, 3)
                            )
                    )
            )
        }


        @JvmStatic
        fun getLensesAndPowers(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(Lens("rn", 1, 0, 1), 1),
                    Arguments.of(Lens("cm", 2, 0, 2), 4),
                    Arguments.of(Lens("ot", 7, 3, 1), 28)
            )
        }
    }
}