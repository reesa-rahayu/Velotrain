package com.example.velotrain

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import entities.Passenger

class DetailTicket : AppCompatActivity() {
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
    private var totalCost: Double = 0.0
    private var userId: Long = 0
    private lateinit var ticketCode: String

    private lateinit var container: LinearLayout
    private lateinit var container2: LinearLayout
    private lateinit var passengerList: ArrayList<Passenger>
    private lateinit var passengerNumberTextView: TextView

    override fun onCreate(savedInstancedetaile: Bundle?) {
        super.onCreate(savedInstancedetaile)
        setContentView(R.layout.activity_detail_ticket)

        // Back button
        val imageItinerariesBack = findViewById<ImageView>(R.id.imageDetailsBack)
        imageItinerariesBack.setOnClickListener {
            val intent = Intent(this@DetailTicket, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        cost = intent.getStringExtra("cost").toString()
        from = intent.getStringExtra("from").toString()
        fromTime = intent.getStringExtra("fromTime").toString()
        sisa = intent.getStringExtra("sisa").toString()
        trainName = intent.getStringExtra("trainName").toString()
        to = intent.getStringExtra("to").toString()
        toTime = intent.getStringExtra("toTime").toString()
        travelClass = intent.getStringExtra("travelClass").toString()
        travelTime = intent.getStringExtra("travelTime").toString()
        fromName = intent.getStringExtra("startStationName").toString()
        toName = intent.getStringExtra("endStationName").toString()
        fromDate = intent.getStringExtra("dateDeparture").toString()
        toDate = intent.getStringExtra("dateDeparture").toString()
        userId = intent.getLongExtra("userId", 0)
        passengerCount = intent.getStringExtra("passengerCount").toString()
        totalCost = intent.getDoubleExtra("totalCost", 0.0)
        ticketCode = intent.getStringExtra("ticketCode").toString()

        val detailFrom = findViewById<TextView>(R.id.DetailFrom)
        val detailFromTime = findViewById<TextView>(R.id.DetailFromTime)
        val detailFromDate = findViewById<TextView>(R.id.DetailFromDate)
        val detailFromName = findViewById<TextView>(R.id.DetailFromName)
        val detailTrainName = findViewById<TextView>(R.id.DetailTitle)
        val detailTo = findViewById<TextView>(R.id.DetailTo)
        val detailToTime = findViewById<TextView>(R.id.DetailToTime)
        val detailToDate = findViewById<TextView>(R.id.DetailToDate)
        val detailToName = findViewById<TextView>(R.id.DetailToName)
        val detailTravelClass = findViewById<TextView>(R.id.DetailTravelClass)
        val detailClassInfo = findViewById<TextView>(R.id.DetailTravelClassInfo)
        val detailPassengerCount = findViewById<TextView>(R.id.passenger_count)
        val detailPaymentCode = findViewById<TextView>(R.id.TicketPaymentCode)

        detailFrom.text = from
        detailFromTime.text = fromTime
        detailTrainName.text = trainName
        detailTo.text = to
        detailToTime.text = toTime
        detailTravelClass.text = travelClass
        detailClassInfo.text = travelTime
        detailPassengerCount.text = passengerCount
        detailFromDate.text = fromDate
        detailToDate.text = toDate
        detailFromName.text = fromName
        detailToName.text = toName
        detailPaymentCode.text = ticketCode

        passengerList = ArrayList()
        for (i in 0 until passengerCount.toInt()) {
            val passenger = intent.getParcelableExtra<Passenger>("passenger$i")
            if (passenger != null) {
                passengerList.add(passenger)
            }
        }

        container = findViewById(R.id.container)
        val count = passengerCount.toInt()
        for (i in 0 until count) {
            val passengerLayout =
                layoutInflater.inflate(R.layout.passenger_summary_row, container, false)
            val passengerNumber = i + 1
            passengerNumberTextView = passengerLayout.findViewById(R.id.passenger_number)
            passengerNumberTextView.text = "$passengerNumber"
            val passengerName = passengerLayout.findViewById<TextView>(R.id.editTextTextPersonName2)
            passengerName.text = passengerList[i].name
            val passengerId = passengerLayout.findViewById<TextView>(R.id.editTextTextPersonName)
            passengerId.text = passengerList[i].nik.toString()
            val passengerWagon = passengerLayout.findViewById<TextView>(R.id.selected_wagon_code)
            passengerWagon.text = passengerList[i].wagonCode
            val passengerSeat = passengerLayout.findViewById<TextView>(R.id.selected_seat_number)
            passengerSeat.text = passengerList[i].seatNumber

            container.addView(passengerLayout)
            container.isClickable = false
            container.isFocusable = false
            container.isFocusableInTouchMode = false
            container.isEnabled = false
        }

        val btBoarding = findViewById<Button>(R.id.bt_boarding_pass)
        btBoarding.setOnClickListener {
            container2 = findViewById(R.id.container_barcode)
            for (i in 0 until count) {
                val barcodeLayout =
                    layoutInflater.inflate(R.layout.passanger_barcode_row, container2, false)
                val passengerNumber = i + 1
                passengerNumberTextView = barcodeLayout.findViewById(R.id.counterPenumpang)
                passengerNumberTextView.text = "$passengerNumber"

                container2.addView(barcodeLayout)
            }
            container2.visibility = View.VISIBLE
            btBoarding.isEnabled = false
        }

    }
}