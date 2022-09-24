package com.example.flixster

import android.os.Bundle
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
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import okhttp3.Headers

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//

private const val API_KEY = "<YOUR-API-KEY-HERE>"

var client =AsyncHttpClient()
val params= RequestParams()
params["api-key"]=API_KEY
client["", params, object :
     TextHttpResponseHandler(){
    override fun onSuccess(statusCode: Int, headers: Headers?, response: String?) {
        TODO("Not yet implemented")
    }

    override fun onFailure(
        statusCode: Int,
        headers: Headers?,
        errorResponse: String?,
        throwable: Throwable?
    ) {
        TODO("Not yet implemented")
    }
}]
/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the ????  API.
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
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here

        // Using the client, perform the HTTP request

        /* Uncomment me once you complete the above sections!
        {
            /*
             * The onSuccess function gets called when
             * HTTP response status is "200 OK"
             */
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                // The wait for a response is over
                progressBar.hide()
                //TODO - Parse JSON into Models
                val models : List<CurrentMovie> = null // Fix me!
                recyclerView.adapter = BestSellerBooksRecyclerViewAdapter(models, this@BestSellerBooksFragment)
                // Look for this in Logcat:
                Log.d("BestSellerBooksFragment", "response successful")
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
                    Log.e("BestSellerBooksFragment", errorResponse)
                }
            }
        }]
        */

    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: FrequentMovie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }

}
