package org.iesra.procesapadel.domain.port

import java.io.File
import java.nio.file.Path

class SimplePlayerRepository {

    fun findInputFiles(path: Path): List<File> {
        val carpeta = (path.toFile().listFiles())
        return carpeta as List<File>
    }
    fun moveToProcessed(file: File) {

    }
}
