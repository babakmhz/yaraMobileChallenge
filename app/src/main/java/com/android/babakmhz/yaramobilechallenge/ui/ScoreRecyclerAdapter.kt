package com.android.babakmhz.yaramobilechallenge.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.babakmhz.yaramobilechallenge.data.model.Ratings
import com.android.babakmhz.yaramobilechallenge.databinding.ItemRatingBinding


class ScoreRecyclerAdapter(
    private val context: Context,
    private var ratings: List<Ratings>
) : RecyclerView.Adapter<ScoreRecyclerAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val templateBinding =
            ItemRatingBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(
            context,
            templateBinding,
            templateBinding.root
        )
    }

    override fun getItemCount(): Int {
        return this.ratings.size
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(ratings[position])
    }

    class viewHolder(
        val context: Context,
        private val itemsTemplateBinding: ItemRatingBinding,
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Ratings) {
            itemsTemplateBinding.rating = item
            itemsTemplateBinding.executePendingBindings()
        }

    }

}

