package com.example.velotrain

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import entities.Ticket
import entities.User
import helper.TicketStorage
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var startStation: TextView
    private lateinit var startStationName: TextView
    private lateinit var endStation: TextView
    private lateinit var endStationName: TextView
    private lateinit var reverseButton: ImageView
    private lateinit var itinerary: Button
    private lateinit var departureDate: TextView
    private lateinit var passangerCount: TextView
    private lateinit var plusButton: ImageView
    private lateinit var minusButton: ImageView
    private lateinit var username: TextView
    var userId: Long = 1

    private lateinit var popularDestination: LinearLayout
    private lateinit var container: LinearLayout

    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var Ticket : ArrayList<Ticket>

    private val startStationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedStationCode = result.data?.getStringExtra("selected_station_code")
            val selectedStationName = result.data?.getStringExtra("selected_station_name")
            selectedStationCode?.let {
                startStation.text = selectedStationCode
                selectedStationName?.let {
                    startStationName.text = selectedStationName
                }
            }
        }
    }
    private val endStationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedStationCode = result.data?.getStringExtra("selected_station_code")
            val selectedStationName = result.data?.getStringExtra("selected_station_name")
            selectedStationCode?.let {
                endStation.text = selectedStationCode
                selectedStationName?.let {
                    endStationName.text = selectedStationName
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = intent.getParcelableExtra<User>("loginUser")
        username = findViewById(R.id.tv_username)
        username.text = user?.name
        userId = user?.id!!

        startStation = findViewById(R.id.tv_departure_code)
        startStationName = findViewById(R.id.tv_departure_station)
        endStation = findViewById(R.id.tv_destination_code)
        endStationName = findViewById(R.id.tv_destination_station)

        startStation.setOnClickListener {
            val intent = Intent(this, PickStation::class.java)
            startStationLauncher.launch(intent)
        }
        startStationName.setOnClickListener {
            val intent = Intent(this, PickStation::class.java)
            startStationLauncher.launch(intent)
        }

        endStation.setOnClickListener {
            val intent = Intent(this, PickStation::class.java)
            endStationLauncher.launch(intent)
        }
        endStationName.setOnClickListener {
            val intent = Intent(this, PickStation::class.java)
            endStationLauncher.launch(intent)
        }

        reverseButton = findViewById(R.id.ic_reverse)
        reverseButton.setOnClickListener {
            val temp = startStation.text
            startStation.text = endStation.text
            endStation.text = temp
            val tempName = startStationName.text
            startStationName.text = endStationName.text
            endStationName.text = tempName
        }

        departureDate = findViewById(R.id.tv_departure_date)
        updateDateTimeTextView()
        departureDate.setOnClickListener {
            showDatePicker()
        }
        passangerCount = findViewById(R.id.tv_passangers_count)
        plusButton = findViewById(R.id.ic_plus_passanger)
        minusButton = findViewById(R.id.ic_minus_passanger)

        plusButton.setOnClickListener {
            val count = passangerCount.text.toString().toInt()
            if (count < 5) {
                passangerCount.text = (count + 1).toString()
            }
        }
        minusButton.setOnClickListener {
            val count = passangerCount.text.toString().toInt()
            if (count > 1) {
                passangerCount.text = (count - 1).toString()
            }
        }

        itinerary = findViewById(R.id.bt_find_ticket)
        itinerary.setOnClickListener {
            if (startStation.text.isEmpty() || endStation.text.isEmpty() || departureDate.text.isEmpty() || passangerCount.text.isEmpty()) {
                Toast.makeText(this, "Silahkan isi semua data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (startStation.text == endStation.text) {
                Toast.makeText(this, "Asal dan tujuan tidak boleh sama", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (passangerCount.text.toString().toInt() < 1) {
                Toast.makeText(this, "Jumlah penumpang tidak boleh kurang dari 1", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (passangerCount.text.toString().toInt() > 5) {
                Toast.makeText(this, "Jumlah penumpang tidak boleh lebih dari 5", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                val intent = Intent(this, Itineraries::class.java)
                intent.putExtra("start_station_code", startStation.text.toString())
                intent.putExtra("end_station_code", endStation.text.toString())
                intent.putExtra("departure_date", departureDate.text.toString())
                intent.putExtra("passenger_count", passangerCount.text.toString())
                intent.putExtra("start_station_name", startStationName.text.toString())
                intent.putExtra("end_station_name", endStationName.text.toString())
                intent.putExtra("user_id", userId)
                startActivity(intent)
            }
        }

        popularDestination = findViewById(R.id.ll_popular)
        popularDestination.setOnClickListener {
            endStation.text = "JKT"
            endStationName.text = "Jakarta"
        }

    }

    private fun updateDateTimeTextView() {
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        val fullDate: String = sdf.format(calendar.timeInMillis)
        departureDate.setText(fullDate)
    }

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

    private fun updateDateInView() {
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        val formattedDate = sdf.format(calendar.time)
        departureDate.text = formattedDate
    }
}
