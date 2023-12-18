package de.twomartens.adventofcode.day17.graph

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class GraphWalkerTest {

    @ParameterizedTest
    @MethodSource(value = ["getGraphAndLoss"])
    fun shouldFindMinimalHeatLoss(graph: Graph, expectedLoss: Int) {
        val walker = GraphWalker()

        val heatLoss = walker.findMinimalHeatLoss(graph)

        Assertions.assertThat(heatLoss).isEqualTo(expectedLoss)
    }

    companion object {
        @JvmStatic
        fun getGraphAndLoss(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(
                            Graph.of(listOf(
                                    "2413432311323",
                                    "3215453535623",
                                    "3255245654254",
                                    "3446585845452",
                                    "4546657867536",
                                    "1438598798454",
                                    "4457876987766",
                                    "3637877979653",
                                    "4654967986887",
                                    "4564679986453",
                                    "1224686865563",
                                    "2546548887735",
                                    "4322674655533"
                            )),
                            102
                    )
            )
        }
    }
}