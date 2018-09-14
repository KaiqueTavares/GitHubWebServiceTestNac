package com.tavares.kaique.estudonac2semestre.api

import com.tavares.kaique.estudonac2semestre.model.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by kaiqu on 14/09/2018.
 */
interface GitHubService {

    @GET("/users/{username}")
    fun buscarUsuario(@Path("username")username:String):Call<Usuario>
}