package de.twomartens.adventofcode.day21.graph

data class Index(val row: Int, val column: Int, val overflowVertical: Int = 0,
                 val overflowHorizontal: Int = 0)
