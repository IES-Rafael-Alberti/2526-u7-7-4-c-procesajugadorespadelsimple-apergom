package org.iesra.procesapadel.domain.port

import java.io.File
import java.nio.file.Path

class SimplePlayerRepository {

    fun findInputFiles(path: Path): List<File> {
        val carpeta = path.toFile().listFiles() ?: return emptyList()
        val archivos = mutableListOf<File>()
        for (file in carpeta) {
            if (file.isFile) {
                archivos.add(file)
            }
        }
        return archivos
    }
}
