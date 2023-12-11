package de.twomartens.adventofcode.day11.node

data class BorderNode(override val index: Pair<Int, Int>,
                      override val nodeType: NodeType = NodeType.BORDER) : Node