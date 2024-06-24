package entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "wagons",
    foreignKeys = [
        ForeignKey(entity = Train::class,
            parentColumns = ["code"],
            childColumns = ["trainCode"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Class::class,
            parentColumns = ["code"],
            childColumns = ["classId"],
            onDelete = ForeignKey.CASCADE)
    ])
data class Wagon(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "train_code") val trainCode: String,
    @ColumnInfo(name = "class_id") val classCode: String,
    @ColumnInfo(name = "code") val code: String
)
