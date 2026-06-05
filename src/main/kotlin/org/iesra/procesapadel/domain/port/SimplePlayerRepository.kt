package org.iesra.procesapadel.domain.port

import java.io.File
import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries

class SimplePlayerRepository {

    fun findInputFiles(path: Path): List<File> {
       return path.listDirectoryEntries("*.txt") as List<File>
    }

    fun moveToProcessed(file: File) {

    }

}
