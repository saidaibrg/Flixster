package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FrequentMoviesRecyclerViewAdapter (
    private val books: List<FrequentMovie>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<FrequentMoviesRecyclerViewAdapter.BookViewHolder>()

{
    /**
     * [RecyclerView.Adapter] that can display a [BestSellerBook] and makes a call to the
     * specified [OnListFragmentInteractionListener].
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_frequent_movie, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: FrequentMovie? = null
        val mBookTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mBookAuthor: TextView = mView.findViewById<View>(id.movie_author) as TextView

        override fun toString(): String {
            return mBookTitle.toString() + " '" + mBookAuthor.text + "'"
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.mItem = book
        holder.mBookTitle.text = book.title
        holder.mBookAuthor.text = book.author

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return books.size
    }
}

