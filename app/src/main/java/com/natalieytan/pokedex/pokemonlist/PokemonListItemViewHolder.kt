import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.natalieytan.pokedex.R
import com.natalieytan.pokedex.models.Pokemon

class PokemonListItemViewHolder private constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val pokemonId: TextView = itemView.findViewById(R.id.pokemonId)
    private val pokemonName: TextView = itemView.findViewById(R.id.pokemonName)
    private val pokemonSprite: ImageView = itemView.findViewById(R.id.pokemonSprite)
    private val pokemonTypePrimary: TextView = itemView.findViewById(R.id.pokemonTypePrimary)
    private val pokemonTypeSecondary: TextView = itemView.findViewById(R.id.pokemonTypeSecondary)

    fun bind(
        pokemon: Pokemon, pokemonListItemClickListener: PokemonListItemClickListener
    ) {
        itemView.setOnClickListener { pokemonListItemClickListener.onClick(pokemon) }

        val pokemonSpriteUri = pokemon.sprites.normal.toUri().buildUpon().scheme("https").build()
        val typePrimary = pokemon.type[0]
        val typeSecondary = pokemon.type.getOrNull(1)

        Glide.with(pokemonSprite.context)
            .load(pokemonSpriteUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_baseline_cloud_download_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
            )
            .into(pokemonSprite)

        pokemonId.text = "#${pokemon.id.toString().padStart(3, '0')}"
        pokemonName.text = pokemon.name
        pokemonTypePrimary.text = typePrimary
        when (typeSecondary) {
            null -> pokemonTypeSecondary.visibility = View.GONE
            else -> {
                pokemonTypeSecondary.text = typeSecondary
                pokemonTypeSecondary.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): PokemonListItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.pokemon_list_item, parent, false)
            return PokemonListItemViewHolder(view)
        }
    }

    class PokemonListItemClickListener(val clickListener: (pokemonId:Int) -> Unit) {
        fun onClick(pokemon: Pokemon) = clickListener(pokemon.id)
    }
}
