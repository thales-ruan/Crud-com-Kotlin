package projeto.android.primeiroappemkotlin.util

import android.widget.ImageView
import coil.load
import projeto.android.primeiroappemkotlin.R

fun ImageView.tentaCarregarImagem(url: String? = null) {

    load(url) {
        fallback(R.drawable.ic_launcher_background)
        error(R.drawable.ic_launcher_foreground)
    }

}