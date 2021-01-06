package `in`.programy.itunesapi.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Item::class],version = 1)
@TypeConverters(TypeConvertor::class)
abstract class TunesDatabase : RoomDatabase(){
    abstract fun getItemDao(): TunesDao

    companion object{
        @Volatile
        private var instance: TunesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK){
            instance
                ?: createDatabase(
                    context
                ).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context): TunesDatabase {
            return Room.databaseBuilder(context,
                TunesDatabase::class.java,"itemDb")
                .build()
        }
    }
}