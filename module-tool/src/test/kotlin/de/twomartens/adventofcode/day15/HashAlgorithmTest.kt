package de.twomartens.adventofcode.day15

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class HashAlgorithmTest {

    @ParameterizedTest
    @MethodSource(value = ["getInputsAndHashes"])
    fun shouldCreateHash(input: String, hash: Int) {
        val hashAlgorithm = HashAlgorithm()

        val calculatedHash = hashAlgorithm.calculateHash(input)

        Assertions.assertThat(calculatedHash).isEqualTo(hash)
    }

    companion object {
        @JvmStatic
        private fun getInputsAndHashes(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of("rn=1", 30),
                    Arguments.of("cm-", 253),
                    Arguments.of("qp=3", 97),
                    Arguments.of("cm=2", 47),
                    Arguments.of("qp-", 14),
                    Arguments.of("pc=4", 180),
                    Arguments.of("ot=9", 9),
                    Arguments.of("ab=5", 197),
                    Arguments.of("pc-", 48)
            )
        }
    }
}