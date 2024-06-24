package entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "classes",
    foreignKeys = [
        ForeignKey(entity = Train::class,
            parentColumns = ["code"],
            childColumns = ["trainCode"],
            onDelete = ForeignKey.CASCADE)
    ])
data class Class(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "train_code") val trainCode: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "available_seats") val availableSeats: Int,
    @ColumnInfo(name = "total_seats") val totalSeats: Int
)
