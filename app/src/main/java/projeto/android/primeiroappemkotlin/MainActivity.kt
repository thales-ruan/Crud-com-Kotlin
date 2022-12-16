package projeto.android.primeiroappemkotlin

import android.content.Intent
import android.location.GnssAntennaInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import projeto.android.primeiroappemkotlin.ROOM.AppDataBase
import projeto.android.primeiroappemkotlin.constante.CHAVE_PRODUTO_ID
import projeto.android.primeiroappemkotlin.recyclerView.AdapterRecycler
import projeto.android.primeiroappemkotlin.recyclerView.Produt
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {


    private val adapter = AdapterRecycler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configRecycler()
        configFab()
    }

    override fun onResume() {
        super.onResume()

       // adapter.atualiza(dao.buscarTodos())

        val db = AppDataBase.instancia(this)

        val produtoDao = db.produtoDao()

        val scope = MainScope()
        scope.launch {
            val proutos = withContext(Dispatchers.IO){
                produtoDao.buscaTodos()
            }
            adapter.atualiza(proutos)
        }

       // adapter.atualiza(produtoDao.buscaTodos())

    }

    private fun configFab() {
        val float = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        float.setOnClickListener {
            val intent = Intent(this, Form::class.java)
            startActivity(intent)

        }
    }

    private fun configRecycler() {
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)

        recycler.adapter = adapter

        adapter.quandoClicaNoItemListener = {
            val intent = Intent(
                this,
                TelaDetalhes::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)

            }
            Log.i("TAG", "configRecycler: clicou")
            startActivity(intent)
        }

    }

}