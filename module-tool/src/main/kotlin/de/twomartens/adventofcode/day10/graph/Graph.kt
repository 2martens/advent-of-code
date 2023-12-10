package de.twomartens.adventofcode.day10.graph

import de.twomartens.adventofcode.day10.node.Node
import de.twomartens.adventofcode.day10.node.NodeColour
import de.twomartens.adventofcode.day10.node.NodeType

data class Graph(val name: String, val rows: List<List<Node>>, val startPosition: Pair<Int, Int>) {

    val nodes = rows.flatten()

    override fun toString(): String {
        return name
    }

    fun randomNonLoopNode(): Node? {
        return try {
            nodes.filter { it.colour != NodeColour.LOOP }.random()
        } catch (e: NoSuchElementException) {
            null
        }
    }

    companion object {
        fun of(name: String, rows: List<String>): Graph {
            var startPosition: Pair<Int, Int> = Pair(0, 0)
            val graph = rows.map {
                it.split("")
            }.mapIndexed { indexRow, row ->
                row.filterNot { it.isBlank() }.mapIndexed { indexCol, col ->
                    val nodeType = NodeType.of(col)
                    val index = Pair(indexRow, indexCol)
                    if (nodeType == NodeType.START_NODE) {
                        startPosition = index
                    }
                    Node.of(nodeType, index)
                }
            }

            return Graph(name, graph, startPosition)
        }
    }
}