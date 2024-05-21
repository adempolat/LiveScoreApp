package com.adempolat.livescoreapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.adempolat.livescoreapp.databinding.ItemLeagueBinding
import com.adempolat.livescoreapp.databinding.ItemMatchBinding
import com.adempolat.livescoreapp.model.response.Match

class CompositeAdapter(private var data: List<Any>,private val navController: NavController) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_LEAGUE = 0
        private const val TYPE_MATCH = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is String -> TYPE_LEAGUE
            is Data -> TYPE_MATCH
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LEAGUE -> {
                val binding = ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LeagueViewHolder(binding)
            }
            TYPE_MATCH -> {
                val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MatchViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LeagueViewHolder -> holder.bind(data[position] as String)
            is MatchViewHolder -> holder.bind(data[position] as Match)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class LeagueViewHolder(private val binding: ItemLeagueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(league: String) {
            binding.textViewLeagueName.text = league
        }
    }

    inner class MatchViewHolder(private val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match) {
            binding.apply {
                textViewHomeTeam.text = match.homeTeam.name
                textViewAwayTeam.text = match.awayTeam.name
                textViewScore.text = "${match.score.homeTeamScore.regular} - ${match.score.awayTeamScore.regular}"
                textViewHalfTimeScore.text = "HT: ${match.homeTeam.halfTimeScore.home} - ${match.awayTeam.halfTimeScore.away}"

                root.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        league = match.league,
                        homeTeam = match.homeTeam.name,
                        awayTeam = match.awayTeam.name,
                        homeScore = match.score.homeTeamScore.regular,
                        awayScore = match.score.awayTeamScore.regular,
                        homeRegularScore = match.homeTeam.regularScore,
                        awayRegularScore = match.awayTeam.regularScore,
                        homeHalfTimeScore = match.homeTeam.halfTimeScore.home,
                        awayHalfTimeScore = match.awayTeam.halfTimeScore.away,
                        homeRedCards = match.score.homeRedCards,
                        awayRedCards = match.score.awayRedCards
                    )
                    navController.navigate(action)
                }
            }
        }
    }
    fun setData(data: List<Any>) {
        this.data = data
        notifyDataSetChanged()
    }
}
