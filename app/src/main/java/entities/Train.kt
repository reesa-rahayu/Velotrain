package entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "trains", foreignKeys = [
    ForeignKey(entity = Station::class,
        parentColumns = ["id"],
        childColumns = ["startStationCode"],
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Station::class,
        parentColumns = ["id"],
        childColumns = ["EndStationCode"],
        onDelete = ForeignKey.CASCADE)
])
data class Train(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "start_station_code") val startStationCode: String,
    @ColumnInfo(name = "end_station_code") val endStationCode: String,
    @ColumnInfo(name = "type") val type: String
)