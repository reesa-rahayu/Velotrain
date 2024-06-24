package helper

import android.database.Cursor
import entities.Itinerary
import entities.Station

object MappingHelper {
    fun stationToArrayList(notesCursor: Cursor?): ArrayList<Station> {
        val stationList = ArrayList<Station>()
        notesCursor?.apply {
            while (moveToNext()) {
                val id =
                    getLong(getColumnIndexOrThrow(DatabaseContract.Stations.COLUMN_ID))
                val name =
                    getString(getColumnIndexOrThrow(DatabaseContract.Stations.COLUMN_NAME))
                val code =
                    getString(getColumnIndexOrThrow(DatabaseContract.Stations.COLUMN_CODE))
                val zoneId =
                    getLong(getColumnIndexOrThrow(DatabaseContract.Stations.COLUMN_ZONE_ID))
                stationList.add(Station(id, name, code, zoneId))
            }
        }
        return stationList
    }

    fun ItineraryToArrayList(notesCursor: Cursor?): ArrayList<Itinerary> {
        val itineraryList = ArrayList<Itinerary>()
        notesCursor?.apply {
            while (moveToNext()) {
                val id =
                    getLong(getColumnIndexOrThrow(DatabaseContract.Itineraries.COLUMN_ID))
                val trainCode =
                    getDouble(getColumnIndexOrThrow(DatabaseContract.Itineraries.COLUMN_TRAIN_CODE))
                val toStationCode =
                    getString(getColumnIndexOrThrow(DatabaseContract.Itineraries.COLUMN_TO_STATION_CODE))
                val fromStationCode =
                    getString(getColumnIndexOrThrow(DatabaseContract.Itineraries.COLUMN_FROM_STATION_CODE))
                val classCode =
                    getString(getColumnIndexOrThrow(DatabaseContract.Itineraries.COLUMN_CLASS_CODE))
                val arrivalTime =
                    getString(getColumnIndexOrThrow(DatabaseContract.Itineraries.COLUMN_ARRIVAL_TIME))
                val departureTime =
                    getString(getColumnIndexOrThrow(DatabaseContract.Itineraries.COLUMN_DEPARTURE_TIME))
                val totalTime =
                    getString(getColumnIndexOrThrow(DatabaseContract.Itineraries.COLUMN_TOTAL_TIME))
                val cost =
                    getString(getColumnIndexOrThrow(DatabaseContract.Itineraries.COLUMN_COST))
                itineraryList.add(Itinerary(id, trainCode, toStationCode, fromStationCode, classCode, arrivalTime, departureTime, totalTime, cost))
            }
        }
        return itineraryList
    }
}