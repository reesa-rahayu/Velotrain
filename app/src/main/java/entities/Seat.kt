package entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seats")
data class Seat(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "class_code") val classCode: String,
    @ColumnInfo(name = "train_code")val trainCode: String,
    @ColumnInfo(name = "wagon_code")val wagonCode: String,
    @ColumnInfo(name = "seat_number")val seatNumber: Int,
    @ColumnInfo(name = "status") val status: String
)