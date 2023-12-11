package de.twomartens.adventofcode.day10.node

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SouthEastNodeTest : NodeTest() {
    @ParameterizedTest
    @MethodSource(value = ["getDiagonalNeighbours"])
    fun shouldNotBeConnectedDiagonally(nodeType: NodeType, index: Pair<Int, Int>) {
        val node = Node.of(NodeType.SOUTH_EAST, Pair(1, 1))
        val otherNode = Node.of(nodeType, index)

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isFalse()
    }

    @ParameterizedTest
    @MethodSource(value = ["getNodeTypes"])
    fun shouldNotBeConnectedToNorth(nodeType: NodeType) {
        val node = Node.of(NodeType.SOUTH_EAST, Pair(1, 1))
        val otherNode = Node.of(nodeType, Pair(0, 1))

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isFalse()
    }

    @ParameterizedTest
    @MethodSource(value = ["getNodeTypes"])
    fun shouldBeConnectedToEast(nodeType: NodeType) {
        val node = Node.of(NodeType.SOUTH_EAST, Pair(1, 1))
        val otherNode = Node.of(nodeType, Pair(1, 2))

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isTrue()
    }

    @ParameterizedTest
    @MethodSource(value = ["getNodeTypes"])
    fun shouldNotBeConnectedToWest(nodeType: NodeType) {
        val node = Node.of(NodeType.SOUTH_EAST, Pair(1, 1))
        val otherNode = Node.of(nodeType, Pair(1, 0))

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isFalse()
    }

    @ParameterizedTest
    @MethodSource(value = ["getNodeTypes"])
    fun shouldBeConnectedToSouth(nodeType: NodeType) {
        val node = Node.of(NodeType.SOUTH_EAST, Pair(1, 1))
        val otherNode = Node.of(nodeType, Pair(2, 1))

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isTrue()
    }
}