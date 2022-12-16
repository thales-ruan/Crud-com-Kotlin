package projeto.android.primeiroappemkotlin.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import projeto.android.primeiroappemkotlin.converter.Converter
import projeto.android.primeiroappemkotlin.recyclerView.Produt

@Database(entities = [Produt::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao

    companion object {
        fun instancia(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "orgs.db"
            ).allowMainThreadQueries()
                .build()
        }
    }

}