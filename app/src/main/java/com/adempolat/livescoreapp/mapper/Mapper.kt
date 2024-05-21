package com.adempolat.livescoreapp.mapper

import com.adempolat.livescoreapp.model.response.HalfTimeScore
import com.adempolat.livescoreapp.model.response.HalfTimeScoreDto
import com.adempolat.livescoreapp.model.response.LiveScoresResponse
import com.adempolat.livescoreapp.model.response.LiveScoresResponseDto
import com.adempolat.livescoreapp.model.response.Match
import com.adempolat.livescoreapp.model.response.MatchDto
import com.adempolat.livescoreapp.model.response.Score
import com.adempolat.livescoreapp.model.response.ScoreDto
import com.adempolat.livescoreapp.model.response.Team
import com.adempolat.livescoreapp.model.response.TeamDto
import com.adempolat.livescoreapp.model.response.TeamScore
import com.adempolat.livescoreapp.model.response.TeamScoreDto

object Mapper {

    fun mapToDomain(dto: LiveScoresResponseDto): LiveScoresResponse {
        return LiveScoresResponse(
            matches = dto.matches.map { mapToDomain(it) }
        )
    }

    fun mapToDomain(dto: MatchDto): Match {
        return Match(
            league = dto.to,
            homeTeam = mapToDomain(dto.ht),
            awayTeam = mapToDomain(dto.at),
            score = mapToDomain(dto.sc)
        )
    }

    fun mapToDomain(dto: TeamDto): Team {
        return Team(
            name = dto.n,
            regularScore = dto.r,
            halfTimeScore = mapToDomain(dto.ht)
        )
    }

    fun mapToDomain(dto: ScoreDto): Score {
        return Score(
            status = dto.st,
            homeTeamScore = mapToDomain(dto.ht),
            awayTeamScore = mapToDomain(dto.at),
            homeRedCards = dto.hr,
            awayRedCards = dto.ar
        )
    }

    fun mapToDomain(dto: TeamScoreDto): TeamScore {
        return TeamScore(
            regular = dto.r
        )
    }

    fun mapToDomain(dto: HalfTimeScoreDto): HalfTimeScore {
        return HalfTimeScore(
            home = dto.ht,
            away = dto.at
        )
    }
}

