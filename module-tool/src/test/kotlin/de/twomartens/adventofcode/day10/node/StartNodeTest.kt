package de.twomartens.adventofcode.day10.node

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class StartNodeTest : NodeTest() {
    @ParameterizedTest
    @MethodSource(value = ["getDiagonalNeighbours"])
    fun shouldNotBeConnectedDiagonally(nodeType: NodeType, index: Pair<Int, Int>) {
        val node = Node.of(NodeType.START_NODE, Pair(1, 1))
        val otherNode = Node.of(nodeType, index)

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isFalse()
    }

    @ParameterizedTest
    @MethodSource(value = ["getHorizontalNeighbours"])
    fun shouldBeConnectedHorizontally(nodeType: NodeType, index: Pair<Int, Int>) {
        val node = Node.of(NodeType.START_NODE, Pair(1, 1))
        val otherNode = Node.of(nodeType, index)

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isTrue()
    }

    @ParameterizedTest
    @MethodSource(value = ["getVerticalNeighbours"])
    fun shouldBeConnectedVertically(nodeType: NodeType, index: Pair<Int, Int>) {
        val node = Node.of(NodeType.START_NODE, Pair(1, 1))
        val otherNode = Node.of(nodeType, index)

        val isConnected = node.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected).isTrue()
    }
}