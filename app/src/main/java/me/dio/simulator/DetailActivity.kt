// Criando activity usando o biding nas linhas 11, 18, 19 e 21 e comenta a linha 16
package me.dio.simulator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.annotation.Nullable
import me.dio.simulator.databinding.ActivityDetailBinding
import me.dio.simulator.databinding.ActivityDetailBinding.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding //tem que colocar lateinit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate(layoutInflater)//Criando o elemnto binding
        setContentView(binding.root) // chamando set com elemento root

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}