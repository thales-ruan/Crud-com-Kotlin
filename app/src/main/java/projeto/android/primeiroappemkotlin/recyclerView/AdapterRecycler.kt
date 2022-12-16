package projeto.android.primeiroappemkotlin.recyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import projeto.android.primeiroappemkotlin.R
import projeto.android.primeiroappemkotlin.databinding.ItemAdapterBinding
import projeto.android.primeiroappemkotlin.util.tentaCarregarImagem
import java.text.NumberFormat
import java.util.*

class AdapterRecycler(

    private val context: Context,

    produts: List<Produt> = emptyList(),
    var quandoClicaNoItemListener: (produto : Produt) -> Unit = {}
) : RecyclerView.Adapter<AdapterRecycler.ViewHolder>() {


    private val produts = produts.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemAdapterBinding
            .inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val produto = produts[position]
        holder.vincula(produto)

    }

    override fun getItemCount(): Int {
        return produts.size
    }

    fun atualiza(produtos: List<Produt>) {
        this.produts.clear()
        this.produts.addAll(produtos)
        notifyDataSetChanged()
    }

  inner  class ViewHolder(binding: ItemAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

        val nome = binding.nomeProduto
        val valor = binding.valorProduto
        val descricao = binding.descricaoProduto
        val img = binding.imageView

        private lateinit var produto: Produt

        //Rensponsavel pelo click do recycler

        init {
            itemView.setOnClickListener {
                Log.i("ListaProdutosAdapter", "clicando no item")
                if(::produto.isInitialized) {
                    quandoClicaNoItemListener(produto)
                }
            }
        }


            fun vincula(produto: Produt) {

                this.produto = produto
                nome.text = produto.nome


                val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                val moeda: String = formatador.format(produto.valor)
                valor.text = moeda
                descricao.text = produto.descricao

                val visibilidade = if (produto.imagem != null) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

                img.visibility = visibilidade

                img.tentaCarregarImagem(produto.imagem)


            }



    }
}