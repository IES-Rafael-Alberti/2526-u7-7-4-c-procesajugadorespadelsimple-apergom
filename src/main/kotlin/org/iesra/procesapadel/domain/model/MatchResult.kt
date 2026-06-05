package org.iesra.procesapadel.domain.model

data class MatchResult(
    val matches: List<Match>,
    val issues: List<FileIssue>
)
