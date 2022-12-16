package projeto.android.primeiroappemkotlin.ROOM

import androidx.room.*
import projeto.android.primeiroappemkotlin.recyclerView.Produt

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produt")
    fun buscaTodos():List<Produt>

    @Insert
    fun salva(produt: Produt)

    @Delete
    fun remove(produt: Produt)

    @Update
    fun altera(produt: Produt)


    @Query("SELECT * FROM Produt WHERE id = :id")
    fun buscarPorId(id:Long) : Produt?

}