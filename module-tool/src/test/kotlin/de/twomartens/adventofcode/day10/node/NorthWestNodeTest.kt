package de.twomartens.adventofcode.day10.node

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class NorthWestNodeTest : NodeTest() {
    @ParameterizedTest
    @MethodSource(value = ["getDiagonalNeighbours"])
    fun shouldNotBeConnectedDiagonally(nodeType: NodeType, index: Pair<Int, Int>) {
        val node = Node.of(NodeType.NORTH_WEST, Pair(1, 1))
        val otherNode = Node.of(nodeType, index)

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isFalse()
    }

    @ParameterizedTest
    @MethodSource(value = ["getNodeTypes"])
    fun shouldBeConnectedToNorth(nodeType: NodeType) {
        val node = Node.of(NodeType.NORTH_WEST, Pair(1, 1))
        val otherNode = Node.of(nodeType, Pair(0, 1))

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isTrue()
    }

    @ParameterizedTest
    @MethodSource(value = ["getNodeTypes"])
    fun shouldNotBeConnectedToEast(nodeType: NodeType) {
        val node = Node.of(NodeType.NORTH_WEST, Pair(1, 1))
        val otherNode = Node.of(nodeType, Pair(1, 2))

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isFalse()
    }

    @ParameterizedTest
    @MethodSource(value = ["getNodeTypes"])
    fun shouldBeConnectedToWest(nodeType: NodeType) {
        val node = Node.of(NodeType.NORTH_WEST, Pair(1, 1))
        val otherNode = Node.of(nodeType, Pair(1, 0))

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isTrue()
    }

    @ParameterizedTest
    @MethodSource(value = ["getNodeTypes"])
    fun shouldNotBeConnectedToSouth(nodeType: NodeType) {
        val node = Node.of(NodeType.NORTH_WEST, Pair(1, 1))
        val otherNode = Node.of(nodeType, Pair(2, 1))

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isFalse()
    }
}