package com.tavares.kaique.estudonac2semestre

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_busca.*
import com.tavares.kaique.estudonac2semestre.api.GitHubService
import com.tavares.kaique.estudonac2semestre.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class BuscaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca)

        btPesquisar.setOnClickListener {
            //Pegar no site do Retrofit
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            //Chamo minha interface API
            val service = retrofit.create(GitHubService::class.java)
            //Estou executando um call back em um log por tras
            //Primeiro crio um object que recebe um callBack do retroFit e pego meu arquivo Endereco
            //Depois disto clico com alt+Enter em object para criar os overrider fun

            //Aqui estou pegando o metodo de buscarUsuario e passando o que eu coloquei no meu EditText
            service.buscarUsuario(etUsername.text.toString()).enqueue(object : Callback<Usuario>{
                override fun onFailure(call: Call<Usuario>?, t: Throwable?) {
                    //Dando mensagem de Erro por Toast
                    Toast.makeText(this@BuscaActivity,
                            "DeuRuimPapai",
                            Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Usuario>?, response: Response<Usuario>?) {
                    //Estou criando uma variavel para receber meu objeto que vai ser preenchido
                    val usuario = response?.body()
                    //Chamo o Piccaso e carrego uma foto contida no meu objeto dentro de um imageView
                    Picasso.get().load(usuario?.avatarUrl).into(ivFoto);
                    //Pego meu Text e coloco o que estiver dentro do meu objeto trazido pela api.
                    tvNome.text = usuario?.nome
                }

            })
        }
    }
}
