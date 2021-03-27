package ru.alexoheah.fundamentalassignments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alexoheah.fundamentalassignments.R
import ru.alexoheah.fundamentalassignments.model.Actor

class ActorAdapter(
    context: Context,
    var actors: List<Actor>
    ): RecyclerView.Adapter<ActorAdapter.ViewHolderActor>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActor {
        return ViewHolderActor(inflater.inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderActor, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): Actor = actors[position]

    override fun getItemCount(): Int = actors.size

    class ViewHolderActor(view: View): RecyclerView.ViewHolder(view) {

        private val poster: ImageView = view.findViewById(R.id.ivActor)
        private val name: TextView = view.findViewById(R.id.tvActor)

        fun bind(actor: Actor) {

            poster.setImageResource(actor.poster)
            name.text = actor.name
        }
    }
}