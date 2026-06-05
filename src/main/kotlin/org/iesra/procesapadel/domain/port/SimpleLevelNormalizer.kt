package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.Player
import java.util.Locale.getDefault

class SimpleLevelNormalizer {
    fun normalize(player:Player) {
        if ((player.nivel != "iniciación") || (player.nivel != "intermedio") || (player.nivel != "experto")) {

        } else {

            if (player.nivel == "iniciación"){
                player.nivel = player.nivel.uppercase(getDefault())
            }

            else if (player.nivel == "intermedio"){
                player.nivel = player.nivel.uppercase(getDefault())
            }

            else if (player.nivel == "experto"){
                player.nivel = player.nivel.uppercase(getDefault())
            }
        }
    }
}