package org.iesra.procesapadel.domain.port

import java.nio.file.Files
import java.nio.file.Path
import org.iesra.procesapadel.domain.model.Pair

class SimpleOutputWriter {
    fun writePairs(torneo: String, path: Path, pairs: List<Pair>): Path {
        Files.createDirectories(path)
        val file = path.resolve("$torneo-parejas.csv")

        println("****************************************  parejas: "+pairs.size)

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
}