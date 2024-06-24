package entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import models.Seat

@Entity(tableName = "passengers", foreignKeys = [
    ForeignKey(entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE)
])
@Parcelize
data class Passenger(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nik") val nik: String,
    @ColumnInfo(name = "seat") var seatNumber: String? = null,
    @ColumnInfo(name = "wagon") var wagonCode: String? = null,
) : Parcelable {
    companion object{
        fun makePassenger(): ArrayList<Passenger> {
            val passenger: ArrayList<Passenger> = arrayListOf(
                Passenger(userId = 1, name = "Mafda Khoirotul Fatha", nik = "3502010101010001"),
                Passenger(userId = 2, name = "Rahayu Kartika Sari", nik = "3201010101010002"),
                Passenger(userId = 3, name = "Mifa Amira Dewi", nik = "6401010101010003"),
                Passenger(userId = 4, name = "Viviana Purba", nik = "7101010101010004")
            )
            return passenger
        }
        fun findByUserId(passengerList: List<Passenger>, user_Id: Long): Passenger? {
            for (passenger in passengerList) {
                if (passenger.userId == user_Id) {
                    return passenger
                }
            }
            return null
        }
    }
}
