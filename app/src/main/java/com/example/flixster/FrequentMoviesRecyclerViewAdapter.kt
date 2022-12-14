package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FrequentMoviesRecyclerViewAdapter(
    private val movies: List<FrequentMovie>,
    private val mListener: FrequentMovieFragment
)
    : RecyclerView.Adapter<FrequentMoviesRecyclerViewAdapter.MovieViewHolder>()

{
    /**
     * [RecyclerView.Adapter] that can display a [FrequentMovie] and makes a call to the
     * specified [OnListFragmentInteractionListener].
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_frequent_movie, parent, false)
        return MovieViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: FrequentMovie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.movie_title) as TextView
        val mMovieOverview: TextView = mView.findViewById<View>(R.id.movie_overview) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(R.id.movie_image) as ImageView
        val mBookRanking: TextView = mView.findViewById<View>(R.id.ranking) as TextView

        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieOverview.text + "'"
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieOverview.text = movie.overview
        holder.mBookRanking.text = movie.popularity.toString()

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500"+movie.moveImageUrl)
            .centerInside()
            .into(holder.mMovieImage)
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }
}

