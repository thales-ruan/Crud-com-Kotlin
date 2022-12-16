package projeto.android.primeiroappemkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import projeto.android.primeiroappemkotlin.MainActivity.Companion.CHAVE_PRODUTO
import projeto.android.primeiroappemkotlin.ROOM.AppDataBase
import projeto.android.primeiroappemkotlin.databinding.ActivityFormBinding
import projeto.android.primeiroappemkotlin.databinding.ActivityTelaDetalhesBinding
import projeto.android.primeiroappemkotlin.recyclerView.Produt
import projeto.android.primeiroappemkotlin.util.tentaCarregarImagem

private const val TAG = "DetalhesProduto"

class TelaDetalhes : AppCompatActivity() {

    private val CHAVE_PRODUTO_ID: String? = "PRODUTO_ID"
    private var produtoId: Long = 0
    private var produto: Produt? = null
    private val binding by lazy {
        ActivityTelaDetalhesBinding.inflate(layoutInflater)
    }
    val produtoDao by lazy {
        AppDataBase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        buscaProduto()
    }

    private fun buscaProduto() {
        produto = produtoDao.buscarPorId(produtoId)
        produto?.let {
            preencheCampos(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.remover -> {
                produto?.let { produtoDao.remove(it) }
                finish()
            }
            R.id.editar -> {
                Intent(this, Form::class.java)
                    .apply {
                        putExtra(CHAVE_PRODUTO, produto)
                        startActivity(this)
                    }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L )


    }

    private fun preencheCampos(produtoCarregado: Produt) {
        with(binding) {
            imageView.tentaCarregarImagem(produtoCarregado.imagem)
            nome.text = produtoCarregado.nome
            descricao.text = produtoCarregado.descricao
        }
    }
}
