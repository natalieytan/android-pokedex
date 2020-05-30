import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natalieytan.pokedex.R

class PokemonListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val pokemonId: TextView = itemView.findViewById(R.id.pokemonId)
    val pokemonName: TextView = itemView.findViewById(R.id.pokemonName)
    val pokemonSprite: ImageView = itemView.findViewById(R.id.pokemonSprite)
    val pokemonTypePrimary: TextView = itemView.findViewById(R.id.pokemonTypePrimary)
    val pokemonTypeSecondary: TextView = itemView.findViewById(R.id.pokemonTypeSecondary)
}