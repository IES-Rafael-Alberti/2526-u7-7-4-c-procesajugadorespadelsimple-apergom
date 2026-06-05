package org.iesra.procesapadel.domain.model


data class Result(

    val pairs: List<Pair>,
    val issues: List<FileIssue>,
)