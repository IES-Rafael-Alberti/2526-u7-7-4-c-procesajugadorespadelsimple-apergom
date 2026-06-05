package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.FileIssue
import org.iesra.procesapadel.domain.model.Player
import org.iesra.procesapadel.domain.model.Pair
import org.iesra.procesapadel.domain.model.Result

class SimplePairMaker {

    fun createPairs(players: List<Player>): Result {
        val issues = mutableListOf<FileIssue>()
        val pairs = mutableListOf<Pair>()

        val counters = mutableMapOf<String, MutableMap<String, Int>>()

        fun inc(nivel: String, horario: String) {
            val porNivel = counters.getOrPut(nivel) { mutableMapOf("mañana" to 0, "tarde" to 0) }
            porNivel[horario] = (porNivel[horario] ?: 0) + 1
        }

        fun escogerPorMenor(nivel: String): String {
            val porNivel = counters.getOrPut(nivel) { mutableMapOf("mañana" to 0, "tarde" to 0) }
            val m = porNivel["mañana"] ?: 0
            val t = porNivel["tarde"] ?: 0
            return if (m <= t) "mañana" else "tarde"
        }

        fun computarHorario(level: String, a: Player, b: Player): String {
            val ha = a.horario
            val hb = b.horario

            if (ha == hb) return ha
            if (ha == "indiferente" && hb != "indiferente") return hb
            if (hb == "indiferente" && ha != "indiferente") return ha

            return escogerPorMenor(level)
        }

        val byNivel = players.groupBy { it.nivel }
        val ordenNivel = listOf("INICIACIÓN", "INTERMEDIO", "AVANZADO")

        for (nivel in ordenNivel) {
            val list = byNivel[nivel].orEmpty()

            var i = 0
            while (i + 1 < list.size) {
                val p1 = list[i]
                val p2 = list[i + 1]
                val horario = computarHorario(nivel, p1, p2)

                val id = "P${pairs.size + 1}"
                val pair = Pair(
                    id = id,
                    player1 = p1,
                    player2 = p2,
                    nivel = nivel,
                    horario = horario
                )
                pairs.add(pair)

                if (horario == "mañana" || horario == "tarde") inc(nivel, horario)
                i += 2
            }

            if (list.size % 2 != 0) {
                val leftover = list.last()
                issues.add(
                    FileIssue(
                        fileName = "jugador ${leftover.nombre} ${leftover.apellidos}",
                        message = "queda sin pareja en el nivel ${leftover.nivel}.",
                    )
                )
            }
        };  return Result(pairs = pairs, issues = issues)
    }
}