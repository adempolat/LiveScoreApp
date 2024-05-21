import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.adempolat.livescoreapp.databinding.ItemMatchBinding
import com.adempolat.livescoreapp.model.response.Match

class MatchAdapter(
    private var matches: List<Match>,
    private val navController: NavController,
    private val onFavoriteClick: (Match) -> Unit
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    // ViewHolder oluşturma
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    // ViewHolder'a veri bağlama
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matches[position])
    }

    // Item sayısını döndürme
    override fun getItemCount(): Int = matches.size

    // Verileri güncelleme
    fun setData(newMatches: List<Match>) {
        matches = newMatches
        notifyDataSetChanged()
    }

    // ViewHolder sınıfı
    inner class MatchViewHolder(private val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match) {
            binding.apply {
                textViewHomeTeam.text = match.homeTeam.name
                textViewAwayTeam.text = match.awayTeam.name
                textViewScore.text = "${match.score.homeTeamScore.regular} - ${match.score.awayTeamScore.regular}"
                textViewRedCards.text = "Red Cards: ${match.score.homeRedCards} - ${match.score.awayRedCards}"

                // Favori ikonu click
                imageViewFavorite.setOnClickListener {
                    onFavoriteClick(match)
                }

                // Maç item click
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
}
