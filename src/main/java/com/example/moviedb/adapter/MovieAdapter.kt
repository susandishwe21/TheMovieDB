package com.example.moviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_top_movie_item.view.*

class MovieAdapter(var topList: List<Result> = ArrayList()) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var clickListener : ClickListener?= null

    fun setClickListener( clickListener : ClickListener){
        this.clickListener = clickListener
    }
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) ,
    View.OnClickListener {

        lateinit var topList : Result

        fun bindTopRate(topRate: Result) {
            this.topList = topRate
            var Base_Url = "https://image.tmdb.org/t/p/w500/"
            Picasso.get()
                .load(Base_Url + topRate.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.movieImage)
            itemView.titleMovie.text = topRate.title
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener?.onClick(topList.id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_top_movie_item,parent,false)
        return MovieViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = topList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTopRate(topList[position])
    }
    fun updateTopMovie(topList: List<Result>) {
        this.topList = topList
        notifyDataSetChanged()
    }
    interface ClickListener{
        fun onClick(id : Int )
    }
}
