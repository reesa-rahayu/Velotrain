package entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "itineraries", foreignKeys = [
    ForeignKey(entity = Train::class,
        parentColumns = ["code"],
        childColumns = ["trainCode"],
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Station::class,
        parentColumns = ["code"],
        childColumns = ["startStationCode"],
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Train::class,
        parentColumns = ["code"],
        childColumns = ["endStationCode"],
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Class::class,
        parentColumns = ["id"],
        childColumns = ["classId"],
        onDelete = ForeignKey.CASCADE),
])
data class Itinerary (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "cost") val cost: Double,
    @ColumnInfo(name = "from_station_code") val fromStationCode: String,
    @ColumnInfo(name = "to_station_code") val toStationCode: String,
    @ColumnInfo(name = "departure_time") val departureTime: String,
    @ColumnInfo(name = "arrival_time") val arrivalTime: String,
    @ColumnInfo(name = "class_id") val classId: String,
    @ColumnInfo(name = "train_code") val trainCode: String,
    @ColumnInfo(name = "total_time") val totalTravelTime: String,
)