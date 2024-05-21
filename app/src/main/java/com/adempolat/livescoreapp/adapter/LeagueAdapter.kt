package com.adempolat.livescoreapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adempolat.livescoreapp.databinding.ItemLeagueBinding

class LeagueAdapter(private val leagues: List<String>) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    inner class LeagueViewHolder(private val binding: ItemLeagueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(league: String) {
            binding.textViewLeagueName.text = league
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val binding = ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeagueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(leagues[position])
    }

    override fun getItemCount(): Int = leagues.size
}