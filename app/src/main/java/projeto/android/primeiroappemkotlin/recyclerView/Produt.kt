package projeto.android.primeiroappemkotlin.recyclerView

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
@Entity
@Parcelize
class Produt (
    @PrimaryKey(autoGenerate = true)val id: Long = 0L,
    val nome: String,
    val descricao : String,
    val valor: BigDecimal,
    val imagem: String? = null
        ): Parcelable


