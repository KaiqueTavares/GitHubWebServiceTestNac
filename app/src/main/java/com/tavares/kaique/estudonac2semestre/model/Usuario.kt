package com.tavares.kaique.estudonac2semestre.model

import com.google.gson.annotations.SerializedName


data class Usuario (
        @SerializedName("nome") val nome:String,
        @SerializedName("avatar_url") val avatarUrl: String
)