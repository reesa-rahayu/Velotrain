package entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import models.station

@Entity(tableName = "users")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
): Parcelable {
    companion object {
        fun makeUser() : ArrayList<User> {
            val users: ArrayList<User> = arrayListOf(
                User(id = 1, name = "Mafda Khoirotul Fatha", email = "mafda@gmail.com", password = "password"),
                User(id = 2, name = "Rahayu Kartika Sari", email = "rahayu@gmail.com", password = "password"),
                User(id = 3, name = "Mifa Amira Dewi", email = "mifa@gmail.com", password = "password"),
                User(id = 4, name = "Viviana Purba", email = "viviana@gmail.com", password = "password"),
            )
            return users
        }
        fun findById(id: Long, userList: List<User>): User? {
            for (user in userList) {
                if (user.id == id) {
                    return user
                }
            }
            return null
        }
        fun findByEmailAndPassword(email: String, password: String, userList: List<User>): User? {
            for (user in userList) {
                if (user.email == email && user.password == password) {
                    return user
                }
            }
            return null
        }

    }
}

