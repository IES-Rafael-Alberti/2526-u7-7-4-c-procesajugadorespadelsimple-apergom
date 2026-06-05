package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.FileIssue
import org.iesra.procesapadel.domain.model.Pair
import org.iesra.procesapadel.domain.model.Player
import org.iesra.procesapadel.domain.model.Result

class SimplePairMaker {

    private fun contarPorFranja(counters: MutableMap<String, MutableMap<String, Int>>, nivel: String, horario: String) {
        val porNivel = counters.getOrPut(nivel) {
            mutableMapOf("mañana" to 0, "tarde" to 0)
        }
        porNivel[horario] = (porNivel[horario] ?: 0) + 1
    }

    private fun menorFranja(counters: MutableMap<String, MutableMap<String, Int>>, nivel: String): String {
        val porNivel = counters.getOrPut(nivel) {
            mutableMapOf("mañana" to 0, "tarde" to 0)
        }

        val manana = porNivel["mañana"] ?: 0
        val tarde = porNivel["tarde"] ?: 0

        return if (manana <= tarde) "mañana" else "tarde"
    }

    private fun calcularHorario(
        counters: MutableMap<String, MutableMap<String, Int>>,
        nivel: String,
        p1: Player,
        p2: Player
    ): String {
        val h1 = p1.horario
        val h2 = p2.horario

        if (h1 == h2) return h1

        if (h1 == "indiferente" && (h2 == "mañana" || h2 == "tarde")) return h2
        if (h2 == "indiferente" && (h1 == "mañana" || h1 == "tarde")) return h1

        if (h1 == "indiferente" && h2 == "indiferente") {
            return menorFranja(counters, nivel)
        }

        if ((h1 == "mañana" && h2 == "tarde") || (h1 == "tarde" && h2 == "mañana")) {
            return menorFranja(counters, nivel)
        }

        return menorFranja(counters, nivel)
    }

    fun createPairs(players: List<Player>): Result {
        val issues = mutableListOf<FileIssue>()
        val pairs = mutableListOf<Pair>()
        val counters = mutableMapOf<String, MutableMap<String, Int>>()

        val byNivel = players.groupBy { it.nivel }
        val ordenNivel = listOf("INICIACIÓN", "INTERMEDIO", "AVANZADO")

        for (nivel in ordenNivel) {
            val list = byNivel[nivel].orEmpty()

            var i = 0
            while (i + 1 < list.size) {
                val p1 = list[i]
                val p2 = list[i + 1]

                val horario = calcularHorario(counters, nivel, p1, p2)

                val pair = Pair(
                    id = "P${pairs.size + 1}",
                    player1 = p1,
                    player2 = p2,
                    nivel = nivel,
                    horario = horario
                )

                pairs.add(pair)

                if (horario == "mañana" || horario == "tarde") {
                    contarPorFranja(counters, nivel, horario)
                }

                i += 2
            }

            if (list.size % 2 != 0) {
                val leftover = list.last()
                issues.add(
                    FileIssue(
                        fileName = "jugador ${leftover.nombre} ${leftover.apellidos}",
                        message = "queda sin pareja en el nivel ${leftover.nivel}."
                    )
                )
            }
        }

        return Result(pairs = pairs, issues = issues)
    }
}