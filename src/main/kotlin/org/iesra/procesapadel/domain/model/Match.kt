package org.iesra.procesapadel.domain.model

data class Match(
    val id: String,
    val pair1: Pair,
    val pair2: Pair,
    val nivel: String,
    val horario: String
)