package com.fmaldonado.akiyama


import android.app.Application
import com.fmaldonado.akiyama.data.network.AruppiApiService
import com.fmaldonado.akiyama.data.network.interceptor.NetworkInterceptor
import com.fmaldonado.akiyama.data.network.interceptor.NetworkInterceptorImpl
import com.fmaldonado.akiyama.data.repositories.AruppiRepository
import com.fmaldonado.akiyama.ui.activities.animeActivity.AnimeActivityViewModelFactory
import com.fmaldonado.akiyama.ui.fragments.animeDisplay.AnimeDisplayViewModelFactory
import com.fmaldonado.akiyama.ui.fragments.dialogs.bottomSheet.ServerBottomSheetViewModel
import com.fmaldonado.akiyama.ui.fragments.dialogs.bottomSheet.ServerBottomSheetViewModelFactory

import com.fmaldonado.akiyama.ui.fragments.home.HomeFragmentViewModelFactory
import com.fmaldonado.akiyama.ui.fragments.search.SearchViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AkiyamaApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@AkiyamaApplication))

        bind<NetworkInterceptor>() with singleton { NetworkInterceptorImpl() }
        bind() from singleton { AruppiApiService(instance()) }
        bind() from singleton { AruppiRepository(instance()) }
        bind() from provider { HomeFragmentViewModelFactory(instance()) }
        bind() from provider { AnimeDisplayViewModelFactory(instance()) }
        bind() from provider { ServerBottomSheetViewModelFactory(instance()) }
        bind() from provider { SearchViewModelFactory(instance()) }
        bind() from provider { AnimeActivityViewModelFactory(instance()) }


    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}
