package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.Player
import java.util.Locale

class SimpleLevelNormalizer {
    fun normalize(player: Player){
    player.nivel =

        when (player.nivel) {
        "iniciación" -> "INICIACIÓN"
        "intermedio" -> "INTERMEDIO"
        "avanzado" -> "AVANZADO"
        else -> player.nivel.uppercase(Locale.getDefault())
    }
}
}