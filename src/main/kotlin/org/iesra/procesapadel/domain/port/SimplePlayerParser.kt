package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.FileIssue
import org.iesra.procesapadel.domain.model.Player
import org.iesra.procesapadel.domain.model.PlayerFile

import java.nio.file.Path
import kotlin.io.path.readLines

class SimplePlayerParser {
    fun parse(path: Path): Player? {
        val lineas = path.readLines()
        val player= Player()

        lineas.forEach { lineaLambda ->
            val linea = lineaLambda.trim()
            if (!linea.contains("Nombre:") || !linea.contains("Apellidos:") || !linea.contains("Nivel:") || !linea.contains(
                    "Horario="
                )
            ) {
                return null
            } else {
                if (linea.contains("Nombre:")) {
                    val partes = linea.split(" ")
                    player.nombre = partes[1]
                } else if (linea.contains("Apellidos:")) {
                    val partes = linea.split(" ")
                    player.apellidos = partes[1] + " " + partes[2]
                } else if (linea.contains("Nivel:")) {
                    val partes = linea.split(" ")
                    player.nivel = partes[1]
                } else if (linea.contains("Horario")) {
                    val partes = linea.split(" ")
                    player.horario = partes[2]
                }
            }
            return player
        }
    }
}
