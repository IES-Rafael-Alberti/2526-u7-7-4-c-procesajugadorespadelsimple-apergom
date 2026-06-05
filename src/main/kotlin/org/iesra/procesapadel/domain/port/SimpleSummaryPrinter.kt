package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.ProcessingSummary

class SimpleSummaryPrinter {
    fun print(summary: ProcessingSummary) {
        println()
        println("Ficheros procesados: ${summary.detectedFiles}")
        println("Ficheros con errores: ${summary.issues.size}")
        println("Jugadores válidos: ${summary.validPlayers}")
        println()

        if (summary.issues.isNotEmpty()) {
            println("Incidencias:")
            for (issue in summary.issues) {
                println("- archivo ${issue.fileName}: ${issue.message}")
            }
            println()
        }
    }
}
