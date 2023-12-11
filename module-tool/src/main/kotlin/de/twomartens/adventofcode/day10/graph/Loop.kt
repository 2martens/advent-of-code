package de.twomartens.adventofcode.day10.graph

import de.twomartens.adventofcode.day10.node.Node

data class Loop(val nodes: Collection<Node>, val indices: Collection<Pair<Int, Int>>)