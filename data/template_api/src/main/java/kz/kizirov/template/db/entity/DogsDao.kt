package kz.kizirov.template.db.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDao {
    @Query("SELECT * FROM Dogs")
    fun getAll(): Flow<List<Dogs>>

    @Query("SELECT * FROM Dogs WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<Dogs>

    @Insert
    suspend fun insertAll(vararg users: Dogs)

    @Query("DELETE FROM Dogs")
    suspend fun delete()
}