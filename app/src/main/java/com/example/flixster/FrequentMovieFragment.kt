package com.example.flixster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the MovieDB API.
 */

class FrequentMovieFragment : Fragment(), OnListFragmentInteractionListener {
    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_frequent_movies_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        // Using the client, perform the HTTP request
        var client =AsyncHttpClient()
        val params= RequestParams()
        params["api-key"]= API_KEY
        client["https://api.themoviedb.org/3/movie/76341?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", params,
                object :JsonHttpResponseHandler(){

            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {

                // The wait for a response is over

                val resultsJSON : JSONObject = json.jsonObject.get("results") as JSONObject
                val moviesJSON : String = resultsJSON.get("movies").toString()
                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<FrequentMovie>>() {}.type
                val models : List<FrequentMovie> = gson.fromJson(moviesJSON, arrayMovieType)
                recyclerView.adapter = FrequentMoviesRecyclerViewAdapter(models, this@FrequentMovieFragment)
                progressBar.hide()

                // Look for this in Logcat:
                Log.d("MoviesFragment", "response successful")
            }
            /*
             * The onFailure function gets called when
             * HTTP response status is "4XX" (eg. 401, 403, 404)
             */
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                t: Throwable?
            ) {
                // The wait for a response is over
                progressBar.hide()
                // If the error is not null, log it!
                t?.message?.let {
                    Log.e("FrequentMoviesFragment", errorResponse)
                }
            }
        }]

        }

    override fun onItemClick(item: FrequentMovie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }

    }




