package entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import models.station

@Entity(tableName = "stations", foreignKeys = [
    ForeignKey(entity = Zone::class,
        parentColumns = ["id"],
        childColumns = ["zoneId"],
        onDelete = ForeignKey.CASCADE)
])
@Parcelize
data class Station(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "zone_id") val zoneId: Long
) : Parcelable {
    companion object {
        fun getStations() : ArrayList<station> {
            val stations: ArrayList<station> = arrayListOf()
            stations.add(
                station(
                    "PSE", "Pasar Senen", "Jakarta Pusat",
                )
            )
            stations.add(
                station(
                    "BD", "Bandung", "Kota Bandung",
                )
            )
            stations.add(
                station(
                    "GMR", "Gambir", "Jakarta",
                )
            )
            stations.add(
                station(
                    "BKS", "Bekasi", "Kota Bekasi",
                )
            )
            stations.add(
                station(
                    "BL", "Blitar", "Kota Blitar",
                )
            )
            stations.add(
                station(
                    "CN", "Cirebon", "Kota Cirebon",
                )
            )

            stations.add(station("SGU", "Surabaya Gubeng", "Surabaya",))
            stations.add(
                station(
                "YK", "Yogyakarta", "Yogyakarta",
            )
            )
            stations.add(
                station(
                "PWS", "Purwosari", "Solo",
            )
            )
            stations.add(
                station(
                "KSB", "Kesamben", "Blitar",
            )
            )
            stations.add(
                station(
                "MLK", "Malang Kota Lama", "Malang"
            )
            )
            stations.add(
                station(
                "SBP", "Sumber Pucung","Malang",
            )
            )
            stations.add(
                station(
                "AJ", "Arjasa", "Jember",
            )
            )
            stations.add(
                station(
                "SMC", "Semarang Poncol", "Semarang",
            )
            )
            stations.add(
                station(
                "CRB", "Caruban", "Madiun",
            )
            )
            stations.add(
                station(
                    "MND", "Madiun", "Madiun",
                ))
            val sortedStations = stations.sortedBy { it.name }
            return ArrayList(sortedStations)
        }
    }
}