package DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import entities.Station
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {
    @Insert
    suspend fun insert(station: Station)

    @Query("SELECT * FROM stations")
    fun getAllStations(): Flow<List<Station>>
}