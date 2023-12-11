package de.twomartens.adventofcode.day10.graph

import de.twomartens.adventofcode.day10.node.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class IntersectionFinderTest {
    @ParameterizedTest
    @MethodSource(value = ["getNodes"])
    fun shouldFindZeroIntersections_WhenNotTouchingLoop(nodes: Collection<Node>, expectedIntersections: Int) {
        val finder = IntersectionFinder()

        val numberOfIntersections = finder.findNumberOfIntersections(nodes)

        Assertions.assertThat(numberOfIntersections).isEqualTo(expectedIntersections)
    }

    companion object {
        @JvmStatic
        private fun getNodes(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(listOf(
                            Node.of(NodeType.GROUND, Pair(1, 1)),
                            BorderNode(Pair(1, 0))
                    ), 0),
                    Arguments.of(listOf(
                            Node.of(NodeType.GROUND, Pair(1, 2)),
                            Node.of(NodeType.VERTICAL, Pair(1, 1), NodeColour.LOOP),
                            BorderNode(Pair(1, 0))
                    ), 1),
                    Arguments.of(listOf(
                            Node.of(NodeType.GROUND, Pair(1, 4)),
                            Node.of(NodeType.NORTH_WEST, Pair(1, 3), NodeColour.LOOP),
                            Node.of(NodeType.HORIZONTAL, Pair(1, 2), NodeColour.LOOP),
                            Node.of(NodeType.NORTH_EAST, Pair(1, 1), NodeColour.LOOP),
                            BorderNode(Pair(1, 0))
                    ), 0),
                    Arguments.of(listOf(
                            Node.of(NodeType.GROUND, Pair(1, 7)),
                            Node.of(NodeType.NORTH_WEST, Pair(1, 6), NodeColour.LOOP),
                            Node.of(NodeType.HORIZONTAL, Pair(1, 5), NodeColour.LOOP),
                            Node.of(NodeType.NORTH_EAST, Pair(1, 4), NodeColour.LOOP),
                            Node.of(NodeType.NORTH_WEST, Pair(1, 3), NodeColour.LOOP),
                            Node.of(NodeType.HORIZONTAL, Pair(1, 2), NodeColour.LOOP),
                            Node.of(NodeType.NORTH_EAST, Pair(1, 1), NodeColour.LOOP),
                            BorderNode(Pair(1, 0))
                    ), 0),
                    Arguments.of(listOf(
                            Node.of(NodeType.GROUND, Pair(1, 7)),
                            Node.of(NodeType.SOUTH_WEST, Pair(1, 6), NodeColour.LOOP),
                            Node.of(NodeType.HORIZONTAL, Pair(1, 5), NodeColour.LOOP),
                            Node.of(NodeType.SOUTH_EAST, Pair(1, 4), NodeColour.LOOP),
                            Node.of(NodeType.NORTH_WEST, Pair(1, 3), NodeColour.LOOP),
                            Node.of(NodeType.HORIZONTAL, Pair(1, 2), NodeColour.LOOP),
                            Node.of(NodeType.NORTH_EAST, Pair(1, 1), NodeColour.LOOP),
                            BorderNode(Pair(1, 0))
                    ), 0),
                    Arguments.of(listOf(
                            Node.of(NodeType.GROUND, Pair(1, 5)),
                            Node.of(NodeType.NORTH_WEST, Pair(1, 4), NodeColour.LOOP),
                            Node.of(NodeType.HORIZONTAL, Pair(1, 3), NodeColour.LOOP),
                            Node.of(NodeType.NORTH_EAST, Pair(1, 2), NodeColour.LOOP),
                            Node.of(NodeType.VERTICAL, Pair(1, 1), NodeColour.LOOP),
                            BorderNode(Pair(1, 0))
                    ), 1),
                    Arguments.of(listOf(
                            Node.of(NodeType.GROUND, Pair(1, 4)),
                            Node.of(NodeType.NORTH_WEST, Pair(1, 3), NodeColour.LOOP),
                            Node.of(NodeType.HORIZONTAL, Pair(1, 2), NodeColour.LOOP),
                            Node.of(NodeType.SOUTH_EAST, Pair(1, 1), NodeColour.LOOP),
                            BorderNode(Pair(1, 0))
                    ), 1),
                    Arguments.of(listOf(
                            Node.of(NodeType.GROUND, Pair(1, 4)),
                            Node.of(NodeType.SOUTH_WEST, Pair(1, 3), NodeColour.LOOP),
                            Node.of(NodeType.HORIZONTAL, Pair(1, 2), NodeColour.LOOP),
                            Node.of(NodeType.NORTH_EAST, Pair(1, 1), NodeColour.LOOP),
                            BorderNode(Pair(1, 0))
                    ), 1)
            )
        }
    }
}