package com.fmaldonado.akiyama.ui.activities.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}