import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.adempolat.livescoreapp.databinding.ItemMatchBinding
import com.adempolat.livescoreapp.model.response.Match

class MatchAdapter(
    private var matches: List<Match>,
    private val navController: NavController
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matches[position])
    }

    override fun getItemCount(): Int = matches.size

    fun setData(newMatches: List<Match>) {
        matches = newMatches
        notifyDataSetChanged()
    }

    inner class MatchViewHolder(private val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match) {
            binding.apply {
                textViewHomeTeam.text = match.homeTeam.name
                textViewAwayTeam.text = match.awayTeam.name
                textViewScore.text = "${match.score.homeTeamScore.regular} - ${match.score.awayTeamScore.regular}"
                textViewRedCards.text = "Red Cards: ${match.score.homeRedCards} - ${match.score.awayRedCards}"

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
