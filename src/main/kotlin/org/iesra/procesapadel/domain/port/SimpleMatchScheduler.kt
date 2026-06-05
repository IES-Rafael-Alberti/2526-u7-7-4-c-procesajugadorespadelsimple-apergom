package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.FileIssue
import org.iesra.procesapadel.domain.model.Match
import org.iesra.procesapadel.domain.model.MatchResult
import org.iesra.procesapadel.domain.model.Pair

class SimpleMatchScheduler {
    fun createMatches(pairs: List<Pair>, torneo: String): MatchResult {
        val matches = mutableListOf<Match>()
        val issues = mutableListOf<FileIssue>()

        val grupos = mutableMapOf<String, MutableList<Pair>>()

        for (pair in pairs) {
            val clave = "${pair.nivel}-${pair.horario}"
            if (!grupos.containsKey(clave)) {
                grupos[clave] = mutableListOf()
            }
            grupos[clave]?.add(pair)
        }

        for ((clave, parejas) in grupos) {
            var i = 0
            while (i + 1 < parejas.size) {
                val p1 = parejas[i]
                val p2 = parejas[i + 1]

                val id = "M${matches.size + 1}"
                val match = Match(
                    id = id,
                    pair1 = p1,
                    pair2 = p2,
                    nivel = p1.nivel,
                    horario = p1.horario
                )
                matches.add(match)
                i += 2
            }

            if (parejas.size % 2 != 0) {
                val leftover = parejas.last()
                issues.add(
                    FileIssue(
                        fileName = "pareja ${leftover.id}",
                        message = "queda pendiente porque no hay otra pareja de nivel ${leftover.nivel} en la franja ${leftover.horario}."
                    )
                )
            }
        }
         println("partidos dentro de createmathces" + matches.size)
         return MatchResult(matches = matches, issues = issues)
    }
}