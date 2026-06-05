package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.Player
import org.iesra.procesapadel.domain.model.PlayerFile
import java.nio.file.Files

class SimplePlayerParser {
    fun parse(file: PlayerFile): Player? {
        val lineas = Files.readAllLines(file.path).map { line -> line.trim() }
        var player = Player()
        lineas.forEach { lineaLambda ->
            val linea = lineaLambda.trim()
            println("lineaLambda: $linea")

            if (!linea.contains("Nombre:") && !linea.contains("Apellidos:") && !linea.contains("Nivel:") && !linea.contains(
                    "Horario=")
            ) {
                return null
            } else {
                if (linea.contains("Nombre:")) {
                    val partes = linea.split(":")
                    player.nombre = partes[1].trim()
                } else if (linea.contains("Apellidos:")) {
                    val partes = linea.split(":")
                    player.apellidos = partes[1].trim()
                } else if (linea.contains("Nivel:")) {
                    val partes = linea.split(":")
                    player.nivel = partes[1].trim()
                } else if (linea.contains("Horario")) {
                    val partes = linea.split("=")
                    player.horario = partes[1].trim()
                }
            }
        }
        return player
    }
}
