package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.Match
import java.nio.file.Files
import java.nio.file.Path
import org.iesra.procesapadel.domain.model.Pair

class SimpleOutputWriter {
    fun writePairs(torneo: String, path: Path, pairs: List<Pair>): Path {
        Files.createDirectories(path)
        val file = path.resolve("$torneo-parejas.csv")

        println("****************************************  parejas: " + pairs.size)

        val sb = StringBuilder()
        sb.appendLine("pareja|jugador1|jugador2|nivel|franja")
        for (pair in pairs) {
            val j1 = "${pair.player1.nombre} ${pair.player1.apellidos}"
            val j2 = "${pair.player2.nombre} ${pair.player2.apellidos}"
            sb.appendLine("${pair.id}|$j1|$j2|${pair.nivel}|${pair.horario}")
        }

        Files.writeString(file, sb.toString())
        return file
    }

    fun writeMatches(torneo: String, path: Path, matches: List<Match>): Path {
        Files.createDirectories(path)
        val file = path.resolve("$torneo-partidos.txt")

        val sb = StringBuilder()

        for (match in matches) {
            sb.appendLine("[${match.id}]")
            sb.appendLine("Nivel: ${match.nivel}")
            sb.appendLine("Franja: ${match.horario}")

            val p1_j1 = "${match.pair1.player1.nombre} ${match.pair1.player1.apellidos}"
            val p1_j2 = "${match.pair1.player2.nombre} ${match.pair1.player2.apellidos}"
            sb.appendLine("${match.pair1.id}: $p1_j1 / $p1_j2")

            val p2_j1 = "${match.pair2.player1.nombre} ${match.pair2.player1.apellidos}"
            val p2_j2 = "${match.pair2.player2.nombre} ${match.pair2.player2.apellidos}"
            sb.appendLine("${match.pair2.id}: $p2_j1 / $p2_j2")

            sb.appendLine()
        }
        Files.writeString(file, sb.toString())
        return file
    }
}