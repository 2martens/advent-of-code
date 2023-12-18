package de.twomartens.adventofcode.day17.graph

class Graph(val rows: List<List<Tile>>) {

    companion object {
        fun of(lines: List<String>): Graph {
            val rows: MutableList<List<Tile>> = mutableListOf()
            for (line in lines) {
                val fields = line.split("").filterNot { it.isBlank() }
                val tiles = fields.map {
                    Tile(heatLossOnEnter = it.toInt())
                }
                rows.add(tiles)
            }

            return Graph(rows)
        }
    }
}