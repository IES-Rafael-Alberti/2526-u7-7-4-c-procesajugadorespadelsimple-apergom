package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.FileIssue
import org.iesra.procesapadel.domain.model.Player
import org.iesra.procesapadel.domain.model.PlayerFile

import java.nio.file.Path
import kotlin.io.path.readLines

class SimplePlayerParser() {
    fun parse(path: Path): Player? {
        val lineas = path.readLines()

        lineas.forEach { lineaLambda ->
            val linea = lineaLambda.trim()
            val player = Player()
            if (!linea.contains("Nombre:") || !linea.contains("Apellidos:") || !linea.contains("Nivel:") || !linea.contains("Horario="))
                {return null}

            else {
                when{ linea.contains("Nombre:")}
                    val partes = linea.split(" ")
                    player.nombre = partes[1]}
                    val nombre = player.nombre
                    linea.contains("Apellidos:")) {
                    val partes = linea.split(" ")
                    player.apellidos = partes[1] + " " + partes[2]
                    val apellidos = player.apellidos
                } else if (linea.contains("Nivel:")) {
                    val partes = linea.split(" ")
                    player.nivel = partes[1]
                    val nivel = player.nivel
                } else if (linea.contains("Horario")) {
                    val partes = linea.split(" ")
                    player.horario = partes[2]
                    val horario = player.horario
                } return Player(nombre, apellidos, nivel, horario)
            }
            )

    }
}
}
