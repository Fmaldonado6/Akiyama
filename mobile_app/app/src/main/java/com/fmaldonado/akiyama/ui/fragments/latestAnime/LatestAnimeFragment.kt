package com.fmaldonado.akiyama.ui.fragments.latestAnime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fmaldonado.akiyama.databinding.FragmentLatestAnimeBinding

class LatestAnimeFragment : Fragment() {

    private lateinit var binding: FragmentLatestAnimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLatestAnimeBinding.inflate(layoutInflater)
        return binding.root
    }
}