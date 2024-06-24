package models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat (
    val id: Long,
    val classCode: String,
    val trainName: String,
    val wagonCode: String,
    val seatNumber: String,
    var isAvailable: Boolean,
): Parcelable {
    fun setSeatAvalibitily(b: Boolean) {
        isAvailable = b
    }
}