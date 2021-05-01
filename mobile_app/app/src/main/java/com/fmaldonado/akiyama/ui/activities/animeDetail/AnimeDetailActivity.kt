package com.fmaldonado.akiyama.ui.activities.animeDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.databinding.ActivityAnimeDetailBinding
import com.fmaldonado.akiyama.ui.common.ParcelableNames

class AnimeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeDetailBinding
    private var anime: Anime? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        anime = intent?.extras?.getParcelable(ParcelableNames.Anime.value)
        anime?.let {
            val byteArray = Base64.decode(it.image, Base64.DEFAULT)

            binding.animeTitle.text = it.title
            binding.synopsis.text = it.synopsis

            Glide.with(this)
                .load(byteArray)
                .centerCrop()
                .into(binding.animeBackground)

            Glide.with(this)
                .load(byteArray)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(binding.animeCover)

        }
    }
}