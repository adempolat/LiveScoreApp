package com.adempolat.livescoreapp.model.response.dto

data class LiveScoresResponseDto(
    val matches: List<MatchDto>
)

data class MatchDto(
    val to: String, // League name or ID
    val ht: TeamDto, // Home team
    val at: TeamDto, // Away team
    val sc: ScoreDto // Score
)

data class TeamDto(
    val n: String, // Team name
    val r: Int, // Regular score
    val ht: HalfTimeScoreDto // Half-time score
)

data class ScoreDto(
    val st: Int, // Status (5 for finished matches)
    val ht: TeamScoreDto, // Home team score
    val at: TeamScoreDto, // Away team score
    val hr: Int, // Home team red cards
    val ar: Int // Away team red cards
)

data class TeamScoreDto(
    val r: Int // Regular score
)

data class HalfTimeScoreDto(
    val ht: Int, // Half-time score
    val at: Int // Away team half-time score
)

