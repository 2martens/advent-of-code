package de.twomartens.adventofcode.day10.graph

import de.twomartens.adventofcode.day10.node.Node
import de.twomartens.adventofcode.day10.node.NodeColour
import de.twomartens.adventofcode.day10.node.NodeType

data class Graph(val name: String, val rows: List<MutableList<Node>>, val startPosition: Pair<Int, Int>) {

    val nodes = rows.flatten()

    override fun toString(): String {
        return name
    }

    fun printGraphString(): String {
        return name + "\n" + rows.joinToString("\n") { row ->
            row.joinToString("") {
                when (it.colour) {
                    NodeColour.INSIDE -> "1"
                    NodeColour.BORDER -> "x"
                    NodeColour.OUTSIDE -> "0"
                    NodeColour.LOOP -> "L"
                    NodeColour.UNKNOWN -> "?"
                }
            }
        }
    }

    fun replaceNode(index: Pair<Int, Int>, node: Node) {
        rows[index.first][index.second] = node
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
                }.toMutableList()
            }

            return Graph(name, graph, startPosition)
        }
    }
}