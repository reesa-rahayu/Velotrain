package database

import DatabaseContract.Stations.COLUMN_ID
import DatabaseContract.Stations.TABLE_NAME
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.sql.SQLException

class StationHelper(context: Context) {
    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: StationHelper? = null
        fun getInstance(context: Context): StationHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: StationHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }
    fun close() {
        dataBaseHelper.close()
        if (database.isOpen) {
            database.close()
        }
    }
    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_ID ASC",
            null
        )
    }
    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$COLUMN_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }
    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }
    fun update(id: String, values: ContentValues?) : Int {
        return database.update(DATABASE_TABLE, values, "$COLUMN_ID = ?", arrayOf(id))
    }
    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$COLUMN_ID = '$id'", null)
    }
}