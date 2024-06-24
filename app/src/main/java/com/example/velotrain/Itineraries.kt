package com.example.velotrain

import adapters.ItineraryAdapter
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import models.Itinerary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Itineraries : AppCompatActivity() {

    // Views
    private lateinit var startStationName: TextView
    private lateinit var endStationName: TextView
    private lateinit var startStationCode: TextView
    private lateinit var endStationCode: TextView
    private lateinit var dateDeparture: TextView
    private lateinit var passengerCount: TextView
    private lateinit var noDataTextView: TextView
    private var userId: Long = 0

    // Calendar instance
    private val calendar: Calendar = Calendar.getInstance()

    // Constants for intent extras
    companion object {
        const val EXTRA_START_STATION_NAME = "start_station_name"
        const val EXTRA_END_STATION_NAME = "end_station_name"
        const val EXTRA_START_STATION_CODE = "start_station_code"
        const val EXTRA_END_STATION_CODE = "end_station_code"
        const val EXTRA_DEPARTURE_DATE = "departure_date"
        const val EXTRA_PASSENGER_COUNT = "passenger_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itineraries)

        // Back button
        val imageItinerariesBack = findViewById<ImageView>(R.id.bt_back)
        imageItinerariesBack.setOnClickListener { onBackPressed() }

        // Initialize views
        startStationName = findViewById(R.id.tv_depart_station)
        endStationName = findViewById(R.id.tv_arrive_station)
        startStationCode = findViewById(R.id.tv_dept_code)
        endStationCode = findViewById(R.id.tv_arr_code)
        dateDeparture = findViewById(R.id.tv_departure_date_itinerary)
        passengerCount = findViewById(R.id.tv_count)

        // Retrieve intent extras
        intent.extras?.let { bundle ->
            startStationName.text = bundle.getString(EXTRA_START_STATION_NAME, "")
            endStationName.text = bundle.getString(EXTRA_END_STATION_NAME, "")
            startStationCode.text = bundle.getString(EXTRA_START_STATION_CODE, "")
            endStationCode.text = bundle.getString(EXTRA_END_STATION_CODE, "")
            dateDeparture.text = bundle.getString(EXTRA_DEPARTURE_DATE, "")
            passengerCount.text = bundle.getString(EXTRA_PASSENGER_COUNT, "0")
            userId = bundle.getLong("user_id", 0)
        }

        // Date picker listener
        dateDeparture.setOnClickListener {
            showDatePicker()
        }

        // Initialize GridView with itineraries
        val gridView = findViewById<GridView>(R.id.grid_itenaries)
        val itineraries = Itinerary.getSampleItineraries(startStationCode.text.toString(), endStationCode.text.toString())
        if(itineraries.isEmpty()){
            noDataTextView = findViewById(R.id.tv_no_data)
            noDataTextView.visibility = View.VISIBLE
        }

        // Set adapter for GridView
        gridView.adapter = ItineraryAdapter(this, itineraries)

        // Item click listener for GridView items
        gridView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ItineraryStats::class.java).apply {
                putExtra("cost", itineraries[position].cost)
                putExtra("from", itineraries[position].from)
                putExtra("fromTime", itineraries[position].fromTime)
                putExtra("sisa", itineraries[position].sisa)
                putExtra("trainName", itineraries[position].trainName)
                putExtra("to", itineraries[position].to)
                putExtra("toTime", itineraries[position].toTime)
                putExtra("travelClass", itineraries[position].travelClass)
                putExtra("travelTime", itineraries[position].travelTime)
                putExtra("passengerCount", passengerCount.text.toString())
                putExtra("dateDeparture", dateDeparture.text.toString())
                putExtra("startStationName", startStationName.text.toString())
                putExtra("endStationName", endStationName.text.toString())
                putExtra("userId", userId)
            }
            startActivity(intent)
        }
    }

    // Function to show date picker dialog
    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(year, month, dayOfMonth)
                updateDateInView()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
        datePicker.show()
    }

    // Function to update date view
    private fun updateDateInView() {
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        val formattedDate = sdf.format(calendar.time)
        dateDeparture.text = formattedDate
    }
}