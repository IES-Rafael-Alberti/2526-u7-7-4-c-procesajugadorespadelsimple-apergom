package org.iesra.procesapadel.application

import org.iesra.procesapadel.cli.CliOptions
import org.iesra.procesapadel.domain.model.FileIssue
import org.iesra.procesapadel.domain.model.Player
import org.iesra.procesapadel.domain.model.ProcessingSummary
import org.iesra.procesapadel.domain.port.SimpleLevelNormalizer
import org.iesra.procesapadel.domain.port.SimpleMatchScheduler
import org.iesra.procesapadel.domain.port.SimpleOutputWriter
import org.iesra.procesapadel.domain.port.SimplePairMaker
import org.iesra.procesapadel.domain.port.SimplePlayerParser
import org.iesra.procesapadel.domain.port.SimplePlayerRepository
import org.iesra.procesapadel.domain.port.SimpleSummaryPrinter

/**
 * Coordina el caso de uso principal del programa.
 *
 * En esta base didáctica se comporta como orquestador: recibe los datos de entrada
 * ya parseados y explica cómo se podría repartir el trabajo entre otros objetos.
 *
 * Importante: en la rama `main` todavía no depende de implementaciones reales ni de
 * interfaces con métodos, porque la intención aquí es enseñar la estructura antes
 * de construir la solución completa.
 */
class PadelProcessingApplication {

    /**
     * Ejecuta el flujo principal del programa. Debes desglosarlo en llamadas a distintos métodos.
     *
     * @param options opciones recibidas desde la línea de comandos.
     */
    fun run(options: CliOptions) {
        println("Torneo recibido: ${options.tournament}")
        println("Directorio de trabajo: ${options.path}")

        // A partir de aquí, una solución OO razonable podría seguir este flujo.
        // En esta rama base no se implementa todavía: solo se deja la guía.

        // ####################### Entrada: Lectura de datos, conversión a estructuras

        // 1. Pedir a una clase repositorio que localice los `.txt` de entrada.

        val repository = SimplePlayerRepository()
        val parser = SimplePlayerParser()
        val normalizer = SimpleLevelNormalizer()
        val pairMaker = SimplePairMaker()
        val outputWriter = SimpleOutputWriter()
        val matchScheduler = SimpleMatchScheduler()
        val summaryPrinter = SimpleSummaryPrinter()
        val inputFiles = repository.findInputFiles(options.path)


        // 2. Crear colecciones donde guardar jugadores válidos e incidencias.

        val players = mutableListOf<Player>()
        val issues = mutableListOf<FileIssue>()

        // 3. Recorrer cada fichero y delegar el parseo en un objeto parser.
        // Esto es un metodo: procesaFichero(inputFile, players, issues)
        println("##################### Ficheros a leer: " + inputFiles.size)

        for (file in inputFiles) {
            val player = parser.parse(file)
            println(player)
            if (player == null) {
                issues.add(FileIssue(file.path.fileName.toString(), "Error al analizar al jugador"))
            } else {
                players.add(player)
                normalizer.normalize(player)
            }
            repository.moveToProcessed(file)
        }

        // ####################### Procesamiento: de datos de entrada, y generación de datos de salida


        println("Jugadors leidos: "+players.size)
        // 8. Delegar la creación de parejas equilibradas a una clase especializada.
        val pairs = pairMaker.createPairs(players)

        println("parejas creadas: " +pairs.pairs.size)
        // 9. Delegar la generación de partidos evitando repetir horarios.
        val matches = matchScheduler.createMatches(pairs.pairs, options.tournament)

        println("partidos creados: " + matches.matches.size)

        // ####################### Salida: ficheros de salida y resumen

        // 10. Delegar la escritura de ficheros de salida a un escritor.
        println("llamada a writePariss*************************** ")
        outputWriter.writePairs(options.tournament, options.path, pairs.pairs)
        println("llamada a writeMatches*************************")
        outputWriter.writeMatches(options.tournament, options.path, matches.matches)


        // 11. Finalmente, construir un resumen y mostrarlo por consola.
        val summary = ProcessingSummary(
            detectedFiles = inputFiles.size,
            validPlayers = players.size,
            issues = issues + pairs.issues + matches.issues
        )
        summaryPrinter.print(summary)

        // printSuggestedDesign()
    }

}


/**
 * Muestra por consola una posible descomposición del problema en objetos.
 *
 * Esta salida sirve como orientación y no forma parte de la solución final.
 */
/** private fun printSuggestedDesign() {
println()
println("Sugerencia de diseño orientado a objetos:")
println("- PlayerFileRepository: localiza, lee y mueve ficheros.")
println("- PlayerParser: convierte un fichero en un objeto Player.")
println("- LevelNormalizer: convierte el nivel textual en un valor comparable.")
println("- PairMaker: crea parejas equilibradas según nivel y preferencia.")
println("- MatchScheduler: genera partidos entre parejas y horarios disponibles.")
println("- OutputWriter: escribe los ficheros CSV y TXT.")
println("- SummaryPrinter: muestra el resumen final.")
}
}
 */