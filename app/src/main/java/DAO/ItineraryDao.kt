package DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import entities.Itinerary

@Dao
interface ItineraryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItinerary(itinerary: Itinerary): Long

    @Update
    suspend fun updateItinerary(itinerary: Itinerary)

    @Delete
    suspend fun deleteItinerary(itinerary: Itinerary)

    @Query("SELECT * FROM itineraries")
    suspend fun getAllItineraries(): List<Itinerary>

    @Query("SELECT * FROM itineraries WHERE from_station_code = :fromStationCode AND to_station_code = :toStationCode")
    suspend fun findItineraries(fromStationCode: String, toStationCode: String): List<Itinerary>

    @Query("SELECT * FROM itineraries WHERE from_station_code = :startStationCode AND to_station_code = :endStationCode ORDER BY cost ASC")
    suspend fun findItinerariesByCostAsc(startStationCode: String, endStationCode: String): List<Itinerary>

    @Query("SELECT * FROM itineraries WHERE from_station_code = :startStationCode AND to_station_code = :endStationCode ORDER BY cost DESC")
    suspend fun findItinerariesByCostDec(startStationCode: String, endStationCode: String): List<Itinerary>

    @Query("SELECT * FROM itineraries WHERE from_station_code = :startStationCode AND to_station_code = :endStationCode ORDER BY departure_time ASC")
    suspend fun findItinerariesByTimeAsc(startStationCode: String, endStationCode: String): List<Itinerary>

    @Query("SELECT * FROM itineraries WHERE from_station_code = :startStationCode AND to_station_code = :endStationCode ORDER BY departure_time DESC")
    suspend fun findItinerariesByTimeDesc(startStationCode: String, endStationCode: String): List<Itinerary>
}