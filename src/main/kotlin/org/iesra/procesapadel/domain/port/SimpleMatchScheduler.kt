package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.FileIssue
import org.iesra.procesapadel.domain.model.Match
import org.iesra.procesapadel.domain.model.MatchResult
import org.iesra.procesapadel.domain.model.Pair

class SimpleMatchScheduler {

    fun createMatches(pairs: List<Pair>): MatchResult {
        val matches = mutableListOf<Match>()
        val issues = mutableListOf<FileIssue>()

        val grouped = pairs.groupBy { it.nivel + "-" + it.horario }

        for ((_, groupPairs) in grouped) {
            var i = 0
            while (i + 1 < groupPairs.size) {
                val pair1 = groupPairs[i]
                val pair2 = groupPairs[i + 1]

                val match = Match(
                    id = "M${matches.size + 1}",
                    pair1 = pair1,
                    pair2 = pair2,
                    nivel = pair1.nivel,
                    horario = pair1.horario
                )

                matches.add(match)
                i += 2
            }

            if (groupPairs.size % 2 != 0) {
                val leftover = groupPairs.last()
                issues.add(
                    FileIssue(
                        fileName = "pareja ${leftover.id}",
                        message = "queda pendiente porque no hay otra pareja de nivel ${leftover.nivel} en la franja ${leftover.horario}."
                    )
                )
            }
        }

        return MatchResult(matches = matches, issues = issues)
    }
}