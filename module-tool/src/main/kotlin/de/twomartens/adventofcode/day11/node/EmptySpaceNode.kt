package de.twomartens.adventofcode.day11.node

data class EmptySpaceNode(override val index: Pair<Int, Int>,
                          override val nodeType: NodeType = NodeType.EMPTY) : Node
