package com.fmaldonado.akiyama.ui.fragments.animeDisplay

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fmaldonado.akiyama.R

class AnimeDisplayFragment : Fragment() {

    companion object {
        fun newInstance() = AnimeDisplayFragment()
    }

    private lateinit var viewModel: AnimeDisplayViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.anime_display_fragment2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AnimeDisplayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}