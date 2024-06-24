
import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class DatabaseContract  // Private constructor to prevent accidental instantiation
private constructor() {
    object Stations : BaseColumns {
        const val TABLE_NAME: String = "stations"
        const val COLUMN_ID: String = "id"
        const val COLUMN_NAME: String = "name"
        const val COLUMN_CODE: String = "code"
        const val COLUMN_ZONE_ID: String = "zone_id"
    }

    object Zones : BaseColumns {
        const val TABLE_NAME: String = "zones"
        const val COLUMN_ID: String = "id"
        const val COLUMN_NAME: String = "name"
        const val COLUMN_CODE: String = "code"
    }

    object Trains : BaseColumns {
        const val TABLE_NAME: String = "trains"
        const val COLUMN_ID: String = "id"
        const val COLUMN_CODE: String = "code"
        const val COLUMN_NAME: String = "name"
        const val COLUMN_START_STATION_CODE: String = "start_station_code"
        const val COLUMN_END_STATION_CODE: String = "end_station_code"
        const val COLUMN_TYPE: String = "type"
    }

    object Classes : BaseColumns {
        const val TABLE_NAME: String = "classes"
        const val COLUMN_ID: String = "id"
        const val COLUMN_NAME: String = "name"
        const val COLUMN_CODE: String = "code"
        const val COLUMN_TRAIN_CODE: String = "train_code"
        const val COLUMN_PRICE: String = "price"
        const val COLUMN_AVAILABLE_SEATS: String = "available_seats"
        const val COLUMN_TOTAL_SEATS: String = "total_seats"
    }

    object Wagons : BaseColumns {
        const val TABLE_NAME: String = "wagons"
        const val COLUMN_ID: String = "id"
        const val COLUMN_NAME: String = "name"
        const val COLUMN_TRAIN_CODE: String = "train_code"
        const val COLUMN_CLASS_CODE: String = "class_code"
        const val COLUMN_CODE: String = "code"
    }

    object Seats : BaseColumns {
        const val TABLE_NAME: String = "seats"
        const val COLUMN_ID: String = "id"
        const val COLUMN_CLASS_CODE: String = "class_code"
        const val COLUMN_TRAIN_CODE: String = "train_code"
        const val COLUMN_WAGON_CODE: String = "wagon_code"
        const val COLUMN_SEAT_NUMBER: String = "seat_number"
        const val COLUMN_STATUS: String = "status"
    }

    object Itineraries : BaseColumns {
        const val TABLE_NAME: String = "itineraries"
        const val COLUMN_ID: String = "id"
        const val COLUMN_COST: String = "cost"
        const val COLUMN_TRAIN_CODE: String = "train_code"
        const val COLUMN_CLASS_CODE: String = "class_code"
        const val COLUMN_DEPARTURE_TIME: String = "departure_time"
        const val COLUMN_ARRIVAL_TIME: String = "arrival_time"
        const val COLUMN_TOTAL_TIME: String = "total_time"
        const val COLUMN_FROM_STATION_CODE: String = "from_station_code"
        const val COLUMN_TO_STATION_CODE: String = "to_station_code"
    }

    object Users : BaseColumns {
        const val TABLE_NAME: String = "users"
        const val COLUMN_ID: String = "id"
        const val COLUMN_NAME: String = "name"
        const val COLUMN_EMAIL: String = "email"
        const val COLUMN_PASSWORD: String = "password"
    }

    object Passengers : BaseColumns {
        const val TABLE_NAME: String = "passengers"
        const val COLUMN_ID: String = "id"
        const val COLUMN_USER_ID: String = "user_id"
        const val COLUMN_NAME: String = "name"
        const val COLUMN_AGE: String = "age"
        const val COLUMN_GENDER: String = "gender"
        const val COLUMN_NIK: String = "nik"
    }

    object Tickets : BaseColumns {
        const val TABLE_NAME: String = "tickets"
        const val COLUMN_ID: String = "id"
        const val COLUMN_PASSENGER_ID: String = "passenger_id"
        const val COLUMN_TRAIN_CODE: String = "train_code"
        const val COLUMN_CLASS_CODE: String = "class_code"
        const val COLUMN_SEAT_NUMBER: String = "seat_number"
        const val COLUMN_STATUS: String = "status"
        const val COLUMN_CREATED_AT: String = "created_at"
        const val COLUMN_DATE: String = "date"
        const val COLUMN_TIME: String = "time"
        const val COLUMN_FROM_STATION_CODE: String = "from_station_code"
        const val COLUMN_TO_STATION_CODE: String = "to_station_code"
    }

}
