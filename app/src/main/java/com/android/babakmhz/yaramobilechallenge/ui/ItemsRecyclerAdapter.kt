package com.android.babakmhz.yaramobilechallenge.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.babakmhz.yaramobilechallenge.data.model.Search
import com.android.babakmhz.yaramobilechallenge.databinding.ItemMoviesBinding
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


class ItemsRecyclerAdapter(
    private val context: Context,
    private var movies: ArrayList<Search>,
    private val callback: callBack
) : RecyclerView.Adapter<ItemsRecyclerAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val templateBinding =
            ItemMoviesBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(
            context,
            templateBinding,
            templateBinding.root,
            callback
        )
    }

    override fun getItemCount(): Int {
        return this.movies.size
    }

    fun reloadItems(items: List<Search>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class viewHolder(
        val context: Context,
        private val itemsTemplateBinding: ItemMoviesBinding,
        itemView: View,
        private val callback: callBack
    ) : RecyclerView.ViewHolder(itemView) {

        object dBindingAdapter {
            @BindingAdapter("imageUrl")
            @JvmStatic
            fun loadImage(view: CircleImageView, url: String?) {
                Glide.with(view).load(url)
                    .into(view)
            }
        }

        fun bind(movie: Search) {
            itemsTemplateBinding.repo = movie
            itemsTemplateBinding.executePendingBindings()
            itemsTemplateBinding.itemContainer.setOnClickListener {
                callback.onItemClicked(movie)
            }

        }

    }

}

public interface callBack {
    fun onItemClicked(movie: Search)
}
