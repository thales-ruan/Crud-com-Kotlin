package projeto.android.primeiroappemkotlin.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import projeto.android.primeiroappemkotlin.databinding.FormularioImagemBinding
import projeto.android.primeiroappemkotlin.util.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(urlPadrao:String? = null ,quandoImagemCarregada: (imagem:String)-> Unit) {

        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))
        binding.button.setOnClickListener {


            urlPadrao?.let {
                binding.imagemVieww.tentaCarregarImagem(it)
                binding.editform.setText(it)
            }

                val url = binding.editform.text.toString()
                binding.imagemVieww.tentaCarregarImagem(url)


        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.editform.text.toString()
                quandoImagemCarregada(url)

            }
            .setNegativeButton("Cancelar") { _, _ ->


            }
            .show()
    }

}