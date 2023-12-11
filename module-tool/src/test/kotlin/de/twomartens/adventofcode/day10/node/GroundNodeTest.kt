package de.twomartens.adventofcode.day10.node

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class GroundNodeTest : NodeTest() {
    @ParameterizedTest
    @MethodSource(value = ["getNeighbours"])
    fun shouldNotBeConnectedToAnything(otherNodeType: NodeType, index: Pair<Int, Int>) {
        val groundNode = Node.of(NodeType.GROUND, Pair(1, 1))
        val otherNode = Node.of(otherNodeType, index)

        val isConnected = groundNode.isConnectedTo(otherNode)

        Assertions.assertThat(isConnected)
                .withFailMessage("Ground node should not be connected with anything")
                .isFalse()
    }
}