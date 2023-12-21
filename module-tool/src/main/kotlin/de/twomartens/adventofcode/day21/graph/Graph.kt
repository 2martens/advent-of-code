package de.twomartens.adventofcode.day21.graph

class Graph(val rows: List<List<Boolean>>, val startPosition: Index) {

    companion object {
        fun of(lines: List<String>): Graph {
            val rows: MutableList<List<Boolean>> = mutableListOf()
            var startPosition: Index = Index(0, 0)
            for ((rowIndex, line) in lines.withIndex()) {
                val fields = line.split("").filterNot { it.isBlank() }
                val tiles = fields.mapIndexed { columnIndex, it ->
                    if (it == "S") {
                        startPosition = Index(rowIndex, columnIndex)
                    }
                    it == "." || it == "S"
                }
                rows.add(tiles)
            }

            return Graph(rows, startPosition)
        }
    }
}