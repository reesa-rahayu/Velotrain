package com.example.velotrain

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import entities.Wagon
import models.Seat
import kotlin.random.Random

class PickSeat : AppCompatActivity() {
    private lateinit var travelClass: String
    private lateinit var seatList: List<Seat>
    private lateinit var wagonList: List<Wagon>
    private lateinit var classCode: TextView
    private lateinit var tvselectedSeat: TextView
    private lateinit var tvselectedWagon: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_seat)
        wagonList = generateWagons()
        seatList = generateSeats()

        travelClass = intent.getStringExtra("travelClass") ?: ""
        val passanger_index = intent.getIntExtra("passengerIndex", -1)

        classCode = findViewById(R.id.tv_classCode)
        classCode.text = travelClass.toString()

        tvselectedSeat = findViewById(R.id.tv_selectedSeat)
        tvselectedWagon = findViewById(R.id.tv_selectedWagon)

        val wagonLayout: LinearLayout = findViewById(R.id.linearLayoutWagon)
        var selectedWagon: TextView? = null

        val gridLayout: GridLayout = findViewById(R.id.gridLayoutSeat)
        var selectedSeat: TextView? = null

        val okButton = findViewById<TextView>(R.id.button_ok)

        for (wagon in wagonList){
            val wagonTextView = TextView(this)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width
                LinearLayout.LayoutParams.WRAP_CONTENT // Height
            )
            params.width = 150 // Set width based on your design
            params.height = 300 // Set height based on your design
            params.setMargins(10, 10, 10, 10) // Set margins (left, top, right, bottom)
            wagonTextView.layoutParams = params
            wagonTextView.setPadding(10, 10, 10, 10)
            wagonTextView.elevation = 10f
            wagonTextView.setBackgroundResource(R.drawable.seat_empty)
            wagonTextView.text = wagon.code
            wagonTextView.gravity = Gravity.CENTER

            wagonTextView.setOnClickListener {
                selectedWagon?.setBackgroundResource(R.drawable.seat_empty)
                selectedWagon = wagonTextView
                wagonTextView.setBackgroundResource(R.drawable.seat_blue)
                tvselectedWagon.text = wagonTextView.text

                gridLayout.removeAllViews()
                val wagonSeatList: List<Seat> = filterSeats(seatList, wagon.code)
                for (seat in wagonSeatList) {
                    val seatTextView = TextView(this)
                    val seatParams = GridLayout.LayoutParams()
                    seatParams.width = 100 // Set width based on your design
                    seatParams.height = 100 // Set height based on your design
                    seatParams.setMargins(10, 10, 10, 10) // Set margins (left, top, right, bottom)
                    seatTextView.layoutParams = seatParams
                    seatTextView.setPadding(10, 10, 10, 10)
                    seatTextView.elevation = 10f
                    seatTextView.setBackgroundResource(R.drawable.seat_empty)
                    seatTextView.text = seat.seatNumber
                    seatTextView.gravity = Gravity.CENTER
                    if(seat.seatNumber.contains("C") ){
                        seatParams.setMargins(150, 10, 10, 10)
                    }

                    if (seat.isAvailable) {
                        seatTextView.setBackgroundResource(R.drawable.seat_orange)
                        seatTextView.isEnabled = false // Disable occupied seats
                    } else {
                        seatTextView.setBackgroundResource(R.drawable.seat_empty) // White color
                        seatTextView.setOnClickListener {
                            selectedSeat?.setBackgroundResource(R.drawable.seat_empty)
                            selectedSeat = seatTextView
                            seatTextView.setBackgroundResource(R.drawable.seat_blue)
                            tvselectedSeat.text = seatTextView.text
                        }
                        okButton.setOnClickListener {
                            if (passanger_index != -1 && selectedSeat != null && selectedWagon != null) {
                                val returnIntent = Intent(
                                    this, ItineraryStats::class.java
                                )
                                returnIntent.putExtra("selected_seat", selectedSeat?.text.toString())
                                returnIntent.putExtra("selected_wagon", selectedWagon?.text.toString())
                                returnIntent.putExtra("passengerIndex", passanger_index)
                                setResult(Activity.RESULT_OK, returnIntent)
                                finish()
                            } else {
                                Toast.makeText(this, "Silahkan Pilih Kursi dan Gerbong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    gridLayout.addView(seatTextView)
                }
            }
            wagonLayout.addView(wagonTextView)
        }
    }

    private fun generateWagons(): List<Wagon> {
        val wagons = ArrayList<Wagon>()
        for (i in 1..5) {
            wagons.add(Wagon( i.toLong(), "Ekonomi", "Sawunggalih", "Ekonomi (A)", "EKO-$i"))
        }
        for (i in 1..5) {
            wagons.add(Wagon( i.toLong(), "Ekonomi", "Sawunggalih", "Bisnis (A)", "PRE-$i"))
        }
        return wagons
    }

    private fun generateSeats(): List<Seat> {
        val seats = ArrayList<Seat>()
        // Generate 20 sample seats, initially all are unoccupied
        for(a in 1..5){
            for (i in 1..20) {
                for (j in 1..4) {
                    val seatCode = (j+64).toChar()
                    seats.add(Seat( i.toLong(), "Ekonomi (A)", "Sawunggalih", "EKO-$a", "$i$seatCode", false))
                }
            }
        }
        randomSeats(seats)
        return seats
    }

    private fun filterSeats(seats: List<Seat>, wagonCode: String): List<Seat> {
        return seats.filter { seat ->
            seat.wagonCode == wagonCode
        }
    }

    private fun randomSeats(seats: List<Seat>): List<Seat> {
        val a = Random.nextInt(1, 400)
        for(i in 1..a){
            val b = Random.nextInt(1, 400)
            seats[b].isAvailable = true
        }
        return seats
    }

}