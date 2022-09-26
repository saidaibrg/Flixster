package com.example.flixster

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single book from the MovieDB API
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */

class FrequentMovie {
    @SerializedName("popularity")
    var popularity = 0

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("overview")
    var overview: String? = null

    //TODO bookImageUrl
    //TODO description

}