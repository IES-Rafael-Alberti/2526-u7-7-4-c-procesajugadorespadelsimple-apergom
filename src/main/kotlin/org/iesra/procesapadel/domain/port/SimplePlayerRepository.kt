package org.iesra.procesapadel.domain.port

import java.io.File
import java.nio.file.Path

class SimplePlayerRepository {

    fun findInputFiles(path: Path): List<File> {
        val carpeta = (path.toString())
        if (!carpeta.exists()) {
            return emptyList()
        } else {
            return carpeta.toList()
        }
    }
    fun moveToProcessed(file: File) {

    }
}
