package app.krunal3kapadiya.facts.ui.factsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.krunal3kapadiya.facts.R
import app.krunal3kapadiya.facts.network.models.FactsRows
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_lists.view.*

/**
 * @author krunal kapadiya
 * @link https://krunal3kapadiya.app
 * @date 14,April,2019
 */

class FactsListAdapter(private val factsList: List<FactsRows>) :
    RecyclerView.Adapter<FactsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_lists,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(factsList[position])
    }

    override fun getItemCount(): Int {
        return factsList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(facts: FactsRows) {
            itemView.txtTitle.text = facts.title
            itemView.txtDescription.text = facts.description
            Glide
                .with(itemView.context)
                .load(facts.imageHref)
                .centerCrop()
                .placeholder(R.drawable.ic_image_gray_24dp)
                .into(itemView.ivImage)
        }
    }
}