package com.kyrylo.kotlinmessenger.data.preferences.responces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews

data class GoogleNewsResponse(@Expose
                              @SerializedName("status")
                              private var statusCode: String? = null,

                              @Expose
                              @SerializedName("articles")
                              var data: List<GoogleNews>? = null)