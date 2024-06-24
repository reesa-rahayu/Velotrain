package database

import DatabaseContract
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "velotrain.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        createStationsTable(db)
        createZonesTable(db)
        createTrainsTable(db)
        createClassesTable(db)
        createWagonsTable(db)
        createSeatsTable(db)
        createUsersTable(db)
        createPassengersTable(db)
        createTicketsTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE  IF EXISTS ${DatabaseContract.Users.TABLE_NAME}")
        db.execSQL("DROP TABLE  IF EXISTS ${DatabaseContract.Stations.TABLE_NAME}")
        db.execSQL("DROP TABLE  IF EXISTS ${DatabaseContract.Seats.TABLE_NAME}")
        db.execSQL("DROP TABLE  IF EXISTS ${DatabaseContract.Trains.TABLE_NAME}")
        db.execSQL("DROP TABLE  IF EXISTS ${DatabaseContract.Wagons.TABLE_NAME}")
        db.execSQL("DROP TABLE  IF EXISTS ${DatabaseContract.Classes.TABLE_NAME}")
        db.execSQL("DROP TABLE  IF EXISTS ${DatabaseContract.Passengers.TABLE_NAME}")
        db.execSQL("DROP TABLE  IF EXISTS ${DatabaseContract.Tickets.TABLE_NAME}")
        db.execSQL("DROP TABLE  IF EXISTS ${DatabaseContract.Zones.TABLE_NAME}")
        onCreate(db)
    }

    private fun createStationsTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Stations.TABLE_NAME} (" +
                "${DatabaseContract.Stations.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Stations.COLUMN_NAME} TEXT," +
                "${DatabaseContract.Stations.COLUMN_CODE} TEXT," +
                "${DatabaseContract.Stations.COLUMN_ZONE_ID} INTEGER," +
                "FOREIGN KEY(${DatabaseContract.Stations.COLUMN_ZONE_ID}) REFERENCES ${DatabaseContract.Zones.TABLE_NAME}(${DatabaseContract.Zones.COLUMN_ID})" +
                ")"
        db.execSQL(createTableQuery)
    }

    private fun createZonesTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Zones.TABLE_NAME} (" +
                "${DatabaseContract.Zones.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Zones.COLUMN_NAME} TEXT," +
                "${DatabaseContract.Zones.COLUMN_CODE} TEXT" +
                ")"
        db.execSQL(createTableQuery)
    }

    private fun createTrainsTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Trains.TABLE_NAME} (" +
                "${DatabaseContract.Trains.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Trains.COLUMN_CODE} TEXT," +
                "${DatabaseContract.Trains.COLUMN_NAME} TEXT," +
                "${DatabaseContract.Trains.COLUMN_START_STATION_CODE} TEXT," +
                "${DatabaseContract.Trains.COLUMN_END_STATION_CODE} TEXT," +
                "${DatabaseContract.Trains.COLUMN_TYPE} TEXT," +
                "FOREIGN KEY(${DatabaseContract.Trains.COLUMN_START_STATION_CODE}) REFERENCES ${DatabaseContract.Stations.TABLE_NAME}(${DatabaseContract.Stations.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Trains.COLUMN_END_STATION_CODE}) REFERENCES ${DatabaseContract.Stations.TABLE_NAME}(${DatabaseContract.Stations.COLUMN_CODE})" +
                ")"
        db.execSQL(createTableQuery)
    }

    private fun createClassesTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Classes.TABLE_NAME} (" +
                "${DatabaseContract.Classes.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Classes.COLUMN_NAME} TEXT," +
                "${DatabaseContract.Classes.COLUMN_CODE} TEXT," +
                "${DatabaseContract.Classes.COLUMN_TRAIN_CODE} TEXT," +
                "${DatabaseContract.Classes.COLUMN_PRICE} REAL," +
                "${DatabaseContract.Classes.COLUMN_AVAILABLE_SEATS} INTEGER," +
                "${DatabaseContract.Classes.COLUMN_TOTAL_SEATS} INTEGER," +
                "FOREIGN KEY(${DatabaseContract.Classes.COLUMN_TRAIN_CODE}) REFERENCES ${DatabaseContract.Trains.TABLE_NAME}(${DatabaseContract.Trains.COLUMN_CODE})" +
                ")"
        db.execSQL(createTableQuery)
    }

    private fun createWagonsTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Wagons.TABLE_NAME} (" +
                "${DatabaseContract.Wagons.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Wagons.COLUMN_NAME} TEXT," +
                "${DatabaseContract.Wagons.COLUMN_TRAIN_CODE} TEXT," +
                "${DatabaseContract.Wagons.COLUMN_CLASS_CODE} TEXT," +
                "${DatabaseContract.Wagons.COLUMN_CODE} TEXT," +
                "FOREIGN KEY(${DatabaseContract.Wagons.COLUMN_TRAIN_CODE}) REFERENCES ${DatabaseContract.Trains.TABLE_NAME}(${DatabaseContract.Trains.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Wagons.COLUMN_CLASS_CODE}) REFERENCES ${DatabaseContract.Classes.TABLE_NAME}(${DatabaseContract.Classes.COLUMN_CODE})" +
                ")"
        db.execSQL(createTableQuery)
    }

    private fun createSeatsTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Seats.TABLE_NAME} (" +
                "${DatabaseContract.Seats.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Seats.COLUMN_TRAIN_CODE} TEXT," +
                "${DatabaseContract.Seats.COLUMN_CLASS_CODE} TEXT," +
                "${DatabaseContract.Seats.COLUMN_WAGON_CODE} TEXT," +
                "${DatabaseContract.Seats.COLUMN_SEAT_NUMBER} INTEGER," +
                "${DatabaseContract.Seats.COLUMN_STATUS} TEXT," +
                "FOREIGN KEY(${DatabaseContract.Seats.COLUMN_TRAIN_CODE}) REFERENCES ${DatabaseContract.Trains.TABLE_NAME}(${DatabaseContract.Trains.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Seats.COLUMN_WAGON_CODE}) REFERENCES ${DatabaseContract.Wagons.TABLE_NAME}(${DatabaseContract.Wagons.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Seats.COLUMN_CLASS_CODE}) REFERENCES ${DatabaseContract.Classes.TABLE_NAME}(${DatabaseContract.Classes.COLUMN_CODE})" +
                ")"
        db.execSQL(createTableQuery)
    }

    private fun createUsersTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Users.TABLE_NAME} (" +
                "${DatabaseContract.Users.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Users.COLUMN_NAME} TEXT," +
                "${DatabaseContract.Users.COLUMN_EMAIL} TEXT," +
                "${DatabaseContract.Users.COLUMN_PASSWORD} TEXT" +
                ")"
        db.execSQL(createTableQuery)
    }

    private fun createPassengersTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Passengers.TABLE_NAME} (" +
                "${DatabaseContract.Passengers.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Passengers.COLUMN_USER_ID} INTEGER," +
                "${DatabaseContract.Passengers.COLUMN_NAME} TEXT," +
                "${DatabaseContract.Passengers.COLUMN_AGE} INTEGER," +
                "${DatabaseContract.Passengers.COLUMN_GENDER} TEXT," +
                "${DatabaseContract.Passengers.COLUMN_NIK} TEXT," +
                "FOREIGN KEY(${DatabaseContract.Passengers.COLUMN_USER_ID}) REFERENCES ${DatabaseContract.Users.TABLE_NAME}(${DatabaseContract.Users.COLUMN_ID})" +
                ")"
        db.execSQL(createTableQuery)
    }
    private fun createItinerariesTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Itineraries.TABLE_NAME} (" +
                "${DatabaseContract.Itineraries.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Itineraries.COLUMN_TRAIN_CODE} TEXT," +
                "${DatabaseContract.Itineraries.COLUMN_CLASS_CODE} TEXT," +
                "${DatabaseContract.Itineraries.COLUMN_COST} TEXT," +
                "${DatabaseContract.Itineraries.COLUMN_DEPARTURE_TIME} TEXT," +
                "${DatabaseContract.Itineraries.COLUMN_ARRIVAL_TIME} TEXT," +
                "${DatabaseContract.Itineraries.COLUMN_TOTAL_TIME} TEXT," +
                "${DatabaseContract.Itineraries.COLUMN_FROM_STATION_CODE} TEXT," +
                "${DatabaseContract.Itineraries.COLUMN_TO_STATION_CODE} TEXT," +
                "FOREIGN KEY(${DatabaseContract.Itineraries.COLUMN_TRAIN_CODE}) REFERENCES ${DatabaseContract.Trains.TABLE_NAME}(${DatabaseContract.Trains.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Itineraries.COLUMN_CLASS_CODE}) REFERENCES ${DatabaseContract.Classes.TABLE_NAME}(${DatabaseContract.Classes.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Itineraries.COLUMN_FROM_STATION_CODE}) REFERENCES ${DatabaseContract.Stations.TABLE_NAME}(${DatabaseContract.Stations.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Itineraries.COLUMN_TO_STATION_CODE}) REFERENCES ${DatabaseContract.Stations.TABLE_NAME}(${DatabaseContract.Stations.COLUMN_CODE})" +
                ")"
        db.execSQL(createTableQuery)
    }
    private fun createTicketsTable(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE ${DatabaseContract.Tickets.TABLE_NAME} (" +
                "${DatabaseContract.Tickets.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.Tickets.COLUMN_PASSENGER_ID} INTEGER," +
                "${DatabaseContract.Tickets.COLUMN_TRAIN_CODE} TEXT," +
                "${DatabaseContract.Tickets.COLUMN_CLASS_CODE} TEXT," +
                "${DatabaseContract.Tickets.COLUMN_SEAT_NUMBER} INTEGER," +
                "${DatabaseContract.Tickets.COLUMN_STATUS} TEXT," +
                "${DatabaseContract.Tickets.COLUMN_CREATED_AT} TEXT," +
                "${DatabaseContract.Tickets.COLUMN_DATE} TEXT," +
                "${DatabaseContract.Tickets.COLUMN_TIME} TEXT," +
                "${DatabaseContract.Tickets.COLUMN_FROM_STATION_CODE} TEXT," +
                "${DatabaseContract.Tickets.COLUMN_TO_STATION_CODE} TEXT," +
                "FOREIGN KEY(${DatabaseContract.Tickets.COLUMN_PASSENGER_ID}) REFERENCES ${DatabaseContract.Passengers.TABLE_NAME}(${DatabaseContract.Passengers.COLUMN_ID})," +
                "FOREIGN KEY(${DatabaseContract.Tickets.COLUMN_TRAIN_CODE}) REFERENCES ${DatabaseContract.Trains.TABLE_NAME}(${DatabaseContract.Trains.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Tickets.COLUMN_CLASS_CODE}) REFERENCES ${DatabaseContract.Classes.TABLE_NAME}(${DatabaseContract.Classes.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Tickets.COLUMN_FROM_STATION_CODE}) REFERENCES ${DatabaseContract.Stations.TABLE_NAME}(${DatabaseContract.Stations.COLUMN_CODE})," +
                "FOREIGN KEY(${DatabaseContract.Tickets.COLUMN_SEAT_NUMBER}) REFERENCES ${DatabaseContract.Seats.TABLE_NAME}(${DatabaseContract.Seats.COLUMN_SEAT_NUMBER})," +
                "FOREIGN KEY(${DatabaseContract.Tickets.COLUMN_TO_STATION_CODE}) REFERENCES ${DatabaseContract.Stations.TABLE_NAME}(${DatabaseContract.Stations.COLUMN_CODE})" +
                ")"
        db.execSQL(createTableQuery)
    }
}