package projeto.android.primeiroappemkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import projeto.android.primeiroappemkotlin.MainActivity.Companion.CHAVE_PRODUTO
import projeto.android.primeiroappemkotlin.ROOM.AppDataBase
import projeto.android.primeiroappemkotlin.databinding.ActivityFormBinding
import projeto.android.primeiroappemkotlin.dialog.FormularioImagemDialog
import projeto.android.primeiroappemkotlin.recyclerView.Produt
import projeto.android.primeiroappemkotlin.util.tentaCarregarImagem
import java.math.BigDecimal

class Form : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private var idProduto = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val botao = binding.button

        binding.image.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    url = imagem
                    binding.image.tentaCarregarImagem(url)
                }
        }
        intent.getParcelableExtra<Produt>(CHAVE_PRODUTO).let { produtoCarregado ->
            if (produtoCarregado != null) {
                idProduto = produtoCarregado.id
                url = produtoCarregado.imagem
                binding.image.tentaCarregarImagem(produtoCarregado.imagem)
                binding.editNome.setText(produtoCarregado.nome)
                binding.descricao.setText(produtoCarregado.descricao)
                binding.editValor.setText(produtoCarregado.valor.toPlainString())

            }


        }

        val db = AppDataBase.instancia(this)

        val produtoDao = db.produtoDao()

        botao.setOnClickListener {
            val produtoNovo = criarProduto()

            if (idProduto > 0) {
                produtoDao.altera(produtoNovo)
            } else {
                produtoDao.salva(produtoNovo)
            }

            finish()
        }
    }

    private fun criarProduto(): Produt {
        val editDescri = binding.descricao
        val descricao = editDescri.text.toString()
        val editValor = binding.editValor
        val campoValor = editValor.text.toString()
        val editNome = binding.editNome
        val nome = editNome.text.toString()

        val valor = if (campoValor.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(campoValor)
        }

        return Produt(
            id = idProduto,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}