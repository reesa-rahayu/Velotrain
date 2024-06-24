package com.example.velotrain

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.room.ColumnInfo
import entities.Passenger

class ItineraryStats : AppCompatActivity() {
    private lateinit var cost: String
    private lateinit var from: String
    private lateinit var fromTime: String
    private lateinit var sisa: String
    private lateinit var trainName: String
    private lateinit var to: String
    private lateinit var toTime: String
    private lateinit var travelClass: String
    private lateinit var travelTime: String
    private lateinit var passengerCount: String
    private lateinit var fromName: String
    private lateinit var toName: String
    private lateinit var fromDate: String
    private lateinit var toDate: String
    private lateinit var buttonAddMe: Button
    private lateinit var selectedSeatNumber: TextView
    private lateinit var selectedWagonCode: TextView
    private var userId: Long = 0


    private lateinit var container: LinearLayout
    private lateinit var passengerList: ArrayList<Passenger>
    private lateinit var passengerNumberTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itinerary_stats)

        // Back button
        val imageItinerariesBack = findViewById<ImageView>(R.id.imageItineraryStatsBack)
        imageItinerariesBack.setOnClickListener { onBackPressed() }


        cost = intent.getStringExtra("cost").toString()
        from = intent.getStringExtra("from").toString()
        fromTime = intent.getStringExtra("fromTime").toString()
        sisa = intent.getStringExtra("sisa").toString()
        trainName = intent.getStringExtra("trainName").toString()
        to = intent.getStringExtra("to").toString()
        toTime = intent.getStringExtra("toTime").toString()
        travelClass = intent.getStringExtra("travelClass").toString()
        travelTime = intent.getStringExtra("travelTime").toString()
        passengerCount = intent.getStringExtra("passengerCount").toString()
        fromName = intent.getStringExtra("startStationName").toString()
        toName = intent.getStringExtra("endStationName").toString()
        fromDate = intent.getStringExtra("dateDeparture").toString()
        toDate = intent.getStringExtra("dateDeparture").toString()
        userId = intent.getLongExtra("userId", 0)

        val statCost = findViewById<TextView>(R.id.itineraryStatCost)
        val statFrom = findViewById<TextView>(R.id.itineraryStatFrom)
        val statFromTime = findViewById<TextView>(R.id.itineraryStatFromTime)
        val statFromDate = findViewById<TextView>(R.id.itineraryStatFromDate)
        val statFromName = findViewById<TextView>(R.id.itineraryStatFromName)
        val statSisa = findViewById<TextView>(R.id.itineraryStatSisa)
        val statTrainName = findViewById<TextView>(R.id.itineraryStatTitle)
        val statTo = findViewById<TextView>(R.id.itineraryStatTo)
        val statToTime = findViewById<TextView>(R.id.itineraryStatToTime)
        val statToDate = findViewById<TextView>(R.id.itineraryStatToDate)
        val statToName = findViewById<TextView>(R.id.itineraryStatToName)
        val statTravelClass = findViewById<TextView>(R.id.itineraryStatTravelClass)
        val statClassInfo = findViewById<TextView>(R.id.itineraryStatTravelClassInfo)
        val statPassengerCount = findViewById<TextView>(R.id.total_cost)

        statCost.text = cost
        statFrom.text = from
        statFromTime.text = fromTime
        statSisa.text = sisa
        statTrainName.text = trainName
        statTo.text = to
        statToTime.text = toTime
        statTravelClass.text = travelClass
        statClassInfo.text = travelTime
        statPassengerCount.text = passengerCount
        statFromDate.text = fromDate
        statToDate.text = toDate
        statFromName.text = fromName
        statToName.text = toName

        val imageItineraryStatsBack = findViewById<ImageView>(R.id.imageItineraryStatsBack)

        imageItineraryStatsBack.setOnClickListener {
            onBackPressed();
        }

        container = findViewById(R.id.container)
        val count = passengerCount.toInt()
        val passenger = Passenger.makePassenger()
        val userPassenger = Passenger.findByUserId(passenger, userId)
        passengerList = ArrayList()

        for (i in 0 until count) {
            val passengerLayout = layoutInflater.inflate(R.layout.passenger_detail_layout, container, false)
            val passengerNumber = i+1
            passengerNumberTextView = passengerLayout.findViewById(R.id.passenger_number)
            passengerNumberTextView.text = "$passengerNumber"

            val arraySpinner = arrayOf(
                "KTP", "SIM"
            )
            val s = passengerLayout.findViewById<Spinner>(R.id.spinnerId)
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, arraySpinner
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            s.adapter = adapter

            container.addView(passengerLayout)

            //add me button
            buttonAddMe = findViewById(R.id.bt_add_me)

            buttonAddMe.setOnClickListener {
                val passangerMeId = passengerLayout.findViewById<EditText>(R.id.editTextTextPersonName)
                val passangerMeName = passengerLayout.findViewById<EditText>(R.id.editTextTextPersonName2)
                passangerMeId.setText(userPassenger?.nik.toString())
                passangerMeId.isEnabled = false
                passangerMeId.isFocusable = false
                passangerMeId.isFocusableInTouchMode = false
                passangerMeId.isClickable = false
                passangerMeName.setText(userPassenger?.name)
                passangerMeName.isEnabled = false
                passangerMeName.isFocusable = false
                passangerMeName.isFocusableInTouchMode = false
                passangerMeName.isClickable = false
                buttonAddMe.isEnabled = false
            }

            selectedSeatNumber = passengerLayout.findViewById<TextView>(R.id.selected_seat_number)
            selectedWagonCode = passengerLayout.findViewById<TextView>(R.id.selected_wagon_code)

            //seat pick button
            val buttonPickSeat = passengerLayout.findViewById<TextView>(R.id.bt_pick_seat)
            buttonPickSeat.setOnClickListener {
                val intent = Intent(this, PickSeat::class.java)
                intent.putExtra("passengerIndex", i)
                intent.putExtra("travelClass", i)
                pickSeatLauncher.launch(intent)
            }
        }

        val buttonNext = findViewById<Button>(R.id.buttonPickSeat)

        buttonNext.setOnClickListener {
            //store to ArrayList
            for (i in 0 until count) {
                val passengerLayout = container.getChildAt(i)
                val userId = userId
                val passengerId = passengerLayout.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
                val passengerName = passengerLayout.findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()
                val passengerSeat = passengerLayout.findViewById<TextView>(R.id.selected_seat_number).text.toString()
                val passengerWagon = passengerLayout.findViewById<TextView>(R.id.selected_wagon_code).text.toString()
                val initPassenger = Passenger(i.toLong(), userId, passengerName, passengerId, passengerSeat, passengerWagon)
                passengerList.add(initPassenger)
            }

            val intent = Intent(this, Checkout::class.java).apply {
                putExtra("cost", cost)
                putExtra("from", from)
                putExtra("fromTime", fromTime)
                putExtra("trainName", trainName)
                putExtra("to", to)
                putExtra("toTime", toTime)
                putExtra("travelClass", travelClass)
                putExtra("travelTime", travelTime)
                putExtra("passengerCount", passengerCount)
                putExtra("dateDeparture", fromDate)
                putExtra("startStationName", fromName)
                putExtra("endStationName", toName)
                putExtra("travelClass", travelClass)
                putExtra("userId", userId)
            }
            // Add passengers data to intent
            for (i in 0 until count) {
                val thePassenger = passengerList[i]
                intent.putExtra("passenger$i", thePassenger)
            }

            startActivity(intent)
        }

    }

    private val pickSeatLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedSeat = result.data?.getStringExtra("selected_seat")
            val selectedWagon = result.data?.getStringExtra("selected_wagon")
            val passengerIndex = result.data?.getIntExtra("passengerIndex", -1) ?: -1

            if (passengerIndex != -1 && selectedSeat != null && selectedWagon != null) {

                val passengerLayout = container.getChildAt(passengerIndex)
                val selectedSeatNumber = passengerLayout.findViewById<TextView>(R.id.selected_seat_number)
                val selectedWagonCode = passengerLayout.findViewById<TextView>(R.id.selected_wagon_code)

                selectedSeatNumber.text = selectedSeat
                selectedWagonCode.text = selectedWagon
            } else {
                // Handle the case where passengerIndex is out of bounds or selectedSeat/selectedWagon is null
                Log.e("PILIH KURSI", "Invalid passengerIndex")
            }
        }
    }
}