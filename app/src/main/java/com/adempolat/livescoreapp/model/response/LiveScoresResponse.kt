package com.adempolat.livescoreapp.model.response

data class LiveScoresResponse(
    val matches: List<Match>
)

data class Match(
    val league: String, // League name or ID
    val homeTeam: Team, // Home team
    val awayTeam: Team, // Away team
    val score: Score // Score
)

data class Team(
    val name: String, // Team name
    val regularScore: Int, // Regular score
    val halfTimeScore: HalfTimeScore // Half-time score
)

data class Score(
    val status: Int, // Status (5 for finished matches)
    val homeTeamScore: TeamScore, // Home team score
    val awayTeamScore: TeamScore, // Away team score
    val homeRedCards: Int, // Home team red cards
    val awayRedCards: Int // Away team red cards
)

data class TeamScore(
    val regular: Int // Regular score
)

data class HalfTimeScore(
    val home: Int, // Home team half-time score
    val away: Int // Away team half-time score
)