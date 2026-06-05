package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.Player
import org.iesra.procesapadel.domain.model.PlayerFile
import java.nio.file.Files

class SimplePlayerParser {
    fun parse(file: PlayerFile): Player? {
        val lineas = Files.readAllLines(file.path).map { it.trim() }

        val player = Player("", "", "", "")

        for (linea in lineas) {
            println("lineaLambda: $linea")

            when {
                linea.startsWith("Nombre:") -> {
                    val partes = linea.split(":", limit = 2)
                    if (partes.size < 2) return null
                    player.nombre = partes[1].trim()
                }

                linea.startsWith("Apellidos:") -> {
                    val partes = linea.split(":", limit = 2)
                    if (partes.size < 2) return null
                    player.apellidos = partes[1].trim()
                }

                linea.startsWith("Nivel:") -> {
                    val partes = linea.split(":", limit = 2)
                    if (partes.size < 2) return null
                    player.nivel = partes[1].trim()
                }

                linea.startsWith("Horario") -> {
                    val partes = linea.split("=", limit = 2)
                    if (partes.size < 2) return null
                    player.horario = partes[1].trim()
                }

            }
        }
        return if (
            player.nombre.isNotBlank() &&
            player.apellidos.isNotBlank() &&
            player.nivel.isNotBlank() &&
            player.horario.isNotBlank()
        ) player else null
    }
}

