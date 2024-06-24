package entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tickets", foreignKeys = [
    ForeignKey(
        entity = Train::class,
        parentColumns = ["code"],
        childColumns = ["trainCode"],
        onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = Class::class,
        parentColumns = ["code"],
        childColumns = ["classCode"],
        onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = Passenger::class,
        parentColumns = ["id"],
        childColumns = ["passengerId"],
        onDelete = ForeignKey.CASCADE),
    ForeignKey(
        entity = Seat::class,
        parentColumns = ["id"],
        childColumns = ["seatNumber"],
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Station::class,
        parentColumns = ["id"],
        childColumns = ["startStationCode"],
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Station::class,
        parentColumns = ["id"],
        childColumns = ["EndStationCode"],
        onDelete = ForeignKey.CASCADE)
])
@Parcelize
data class Ticket(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "ticket_code") val tiketCode: String,
    @ColumnInfo(name = "train_name") val trainName: String,
    @ColumnInfo(name = "class_code") val classCode: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "departure_time") val departureTime: String,
    @ColumnInfo(name = "arrive_time") val arriveTime: String,
    @ColumnInfo(name = "travel_time") val travelTime: String,
    @ColumnInfo(name = "from_station_code") val fromStationCode: String,
    @ColumnInfo(name = "from_station_name") val fromStationName: String,
    @ColumnInfo(name = "to_station_code") val toStationCode: String,
    @ColumnInfo(name = "to_station_name") val toStationName: String,
    val passangerList: ArrayList<Passenger>
): Parcelable