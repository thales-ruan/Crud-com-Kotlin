package projeto.android.primeiroappemkotlin.ROOM

import androidx.room.Dao
import androidx.room.Insert
import projeto.android.primeiroappemkotlin.Usuario

@Dao
interface UsuarioDao {

    @Insert
   suspend fun salva(usuario: Usuario)



}