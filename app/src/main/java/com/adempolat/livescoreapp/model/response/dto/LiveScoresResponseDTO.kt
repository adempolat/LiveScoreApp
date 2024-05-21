package com.adempolat.livescoreapp.model.response.dto

// API yanıtı DTO sınıfları
data class LiveScoresResponseDto(
    val matches: List<MatchDto>
)

data class MatchDto(
    val to: String, // Lig ismi veya ID'si
    val ht: TeamDto, // Ev sahibi takım
    val at: TeamDto, // Deplasman takımı
    val sc: ScoreDto, // Skor
    val d: String // Maç tarihi (örnek olarak)
)

data class TeamDto(
    val n: String, // Takım ismi
    val r: Int, // Normal süre skoru
    val ht: HalfTimeScoreDto // Devre arası skoru
)

data class ScoreDto(
    val st: Int, // Durum (5: bitmiş maçlar)
    val ht: TeamScoreDto, // Ev sahibi takım skoru
    val at: TeamScoreDto, // Deplasman takımı skoru
    val hr: Int, // Ev sahibi takım kırmızı kartlar
    val ar: Int // Deplasman takımı kırmızı kartlar
)

data class TeamScoreDto(
    val r: Int // Normal süre skoru
)

data class HalfTimeScoreDto(
    val ht: Int, // Ev sahibi takım devre arası skoru
    val at: Int // Deplasman takımı devre arası skoru
)

