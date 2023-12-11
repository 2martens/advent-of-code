package de.twomartens.adventofcode.day11.node

data class GalaxyNode(override val index: Pair<Int, Int>,
                      override val nodeType: NodeType = NodeType.GALAXY) : Node
