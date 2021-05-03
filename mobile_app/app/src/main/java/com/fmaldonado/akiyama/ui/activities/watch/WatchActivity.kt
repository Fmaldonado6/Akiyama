package com.fmaldonado.akiyama.ui.activities.watch

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.databinding.ActivityWatchBinding
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class WatchActivity : AppCompatActivity() {

    private var server: Server? = null
    private lateinit var binding: ActivityWatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        server = intent?.extras?.getParcelable(ParcelableKeys.SERVER_PARCELABLE)
        server?.let {
            Log.d("Code",it.code)
            val exoPlayer = SimpleExoPlayer.Builder(this).build()
            binding.videoView.player = exoPlayer
            val mediaItem = MediaItem.fromUri(Uri.parse("https://00f74ba44b8d3f31a779c298af9ad6c2f09ce6f548-apidata.googleusercontent.com/download/storage/v1/b/olakease/o/ab9d7655e51e73e683b8a7271d971596?jk=AFshE3V1aCYav48lDuRsw3xJJE-XbFbB4fodHmltnViDREMVMGEDGp6jMZm1S2GHCc_TY9h3bEUtfjaOJDppHpuKuXglPaIwr1TljZi2cnOvvXIB6L9zA1NLu2pnT07jIkHZ6c8jPlQuCtrmFJVNhhpAmSpwVh1UMuMAIntUUwnMtsh2OJxh3qQv7Lr9vk9c8SRjjIA5wOA6tIC_xy5LKU5AwFmY28Clritg_PCtIZtZQXXAlxO339uDOMpkkz2_R8ZtnjG9kKko0A2_XI-tqgu6OE4mLqqlYM86SvswbZ78zzSQP7gJHyLrC4ZzTJrvf2sCiF_9zN_BrZYx_IZOiV7QIfRP_9A_MiunuVgkpD6q2dqVEMEu9HLGLa7UHi1eI6pUYGR249IHXT2KkcSDXELdSo-EYDvdocgSIc9efOJmemi8XiZgQFW0ydA-s9EFTdMC5qtFmddonHKMt6ngcYhxTv5sOVzXo6hBc29tCaZvJbnzHHRrbj2U-l2hTye4AyLejrCQPy5H0gYGENMEYha5jEUyDCdAEUQr7BeRG41CxGpH27iN8S-X626NBfgth1r9tOhBfiREBvnRvwDUO2YciBwdSzBzJw3XR-7ZKYptC0EY3NMm9wg6abASj2cvMZFANuRTJFC5W7t3CPYbtPAkVpRKRJ_k_28ZaJAHS5T9tKlrUEAiA8fFR6yT0zKjqdxIcz2tLKEQCze8ak-tc807yC2Y-Auptun0GLC67w4HBe05M0JzSCypUbXCFQKulRigkP4dDS9_IsJJoByNiY1sxy1BQT5XK49Ey88yNp5xfdopzmiEB6CvkCBmlKvq1jAnQowdZvsCe4mSWZfJHV10oqdPonVZPOHjmVs0izQpAH7Vebn3cP938zszlz_Jj_S_gGEWPtlmYL_voHjiRDiRvubGMVY6tMVtofSuuzdRF2eb1gEQ-2sWpehf0MX3e3oJ9--b7N_PI-eYDv0A9N3VwAgeQ-qLsg1o7n4tuTomOWo2Xq27lVniglM2DKL-&amp;isca=1"))
            exoPlayer.addMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()
        }
    }
}