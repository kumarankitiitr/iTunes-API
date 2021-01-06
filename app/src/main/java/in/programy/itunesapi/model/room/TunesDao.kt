package `in`.programy.itunesapi.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TunesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Query("SELECT * FROM items WHERE artistName LIKE :artist ")
    suspend fun getOfflineItems(artist: String): List<Item>
}