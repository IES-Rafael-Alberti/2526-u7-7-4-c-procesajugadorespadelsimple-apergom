package org.iesra.procesapadel.domain.port

import org.iesra.procesapadel.domain.model.PlayerFile

import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries

class SimplePlayerRepository {

    fun findInputFiles(path: Path): List<PlayerFile> {
        if (!Files.exists(path) || !Files.isDirectory(path)) return emptyList()

        return path.listDirectoryEntries("*.txt").map { PlayerFile(it) }
    }
}
