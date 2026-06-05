package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.Player
import org.iesra.procesapadel.domain.model.PlayerFile

import java.nio.file.Path

class SimplePlayerParser {
    fun parse (path: Path, file: PlayerFile): Player {
        val lineas = PlayerFile.readLines()
        val jugador = Player
        lineas.forEach { linea ->
            linea = linea.trim()
            if (linea.contains("Nombre")) {
                val partes = linea.split(" ")
                partes
            }
        }
    }