package org.iesra.procesapadel.domain.model

data class Pair(
    val id: String,
    val player1: Player,
    val player2: Player,
    val nivel: String,
    val horario: String,
)
