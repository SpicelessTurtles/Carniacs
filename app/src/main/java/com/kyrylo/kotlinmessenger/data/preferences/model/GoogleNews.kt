package com.kyrylo.kotlinmessenger.data.preferences.model

import android.arch.persistence.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class GoogleNews(
        @Expose
        @SerializedName("title")
        var title: String? = null,
        @Expose
        @SerializedName("url")
        var url: String? = null,
        @Expose
        @SerializedName("description")
        var description: String? = null,
        @Expose
        @SerializedName("urlToImage")
        var backgroudnImageUrl: String? = null,
        @SerializedName("content")
        @Expose
        var content : String? = null) : ViewHolderItem