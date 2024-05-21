package com.adempolat.livescoreapp.model.response

// Domain model sınıfları

data class LiveScoresResponse(
    val matches: List<Match>
)

data class Match(
    val league: String, // Lig ismi veya ID'si
    val homeTeam: Team, // Ev sahibi takım
    val awayTeam: Team, // Deplasman takımı
    val score: Score, // Skor
    val date: String // Maç tarihi (örnek olarak)
)

data class Team(
    val name: String, // Takım ismi
    val regularScore: Int, // Normal süre skoru
    val halfTimeScore: HalfTimeScore // Devre arası skoru
)

data class Score(
    val status: Int, // Durum (5: bitmiş maçlar)
    val homeTeamScore: TeamScore, // Ev sahibi takım skoru
    val awayTeamScore: TeamScore, // Deplasman takımı skoru
    val homeRedCards: Int, // Ev sahibi takım kırmızı kartlar
    val awayRedCards: Int // Deplasman takımı kırmızı kartlar
)

data class TeamScore(
    val regular: Int // Normal süre skoru
)

data class HalfTimeScore(
    val home: Int, // Ev sahibi takım devre arası skoru
    val away: Int // Deplasman takımı devre arası skoru
)
