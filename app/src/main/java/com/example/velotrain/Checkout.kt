package com.example.velotrain

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import entities.Passenger

class Checkout : AppCompatActivity() {
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
    private var userId: Long = 0

    private lateinit var passengerList: ArrayList<Passenger>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Back button
        val imageItinerariesBack = findViewById<ImageView>(R.id.imagecheckoutsBack)
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
        fromName = intent.getStringExtra("startStationName").toString()
        toName = intent.getStringExtra("endStationName").toString()
        fromDate = intent.getStringExtra("dateDeparture").toString()
        toDate = intent.getStringExtra("dateDeparture").toString()
        userId = intent.getLongExtra("userId", 0)
        passengerCount = intent.getStringExtra("passengerCount").toString()

        val From = findViewById<TextView>(R.id.checkoutFrom)
        val FromTime = findViewById<TextView>(R.id.checkoutFromTime)
        val FromDate = findViewById<TextView>(R.id.checkoutFromDate)
        val FromName = findViewById<TextView>(R.id.checkoutFromName)
        val TrainName = findViewById<TextView>(R.id.checkoutTitle)
        val To = findViewById<TextView>(R.id.checkoutTo)
        val ToTime = findViewById<TextView>(R.id.checkoutToTime)
        val ToDate = findViewById<TextView>(R.id.checkoutToDate)
        val ToName = findViewById<TextView>(R.id.checkoutToName)
        val TravelClass = findViewById<TextView>(R.id.checkoutTravelClass)
        val ClassInfo = findViewById<TextView>(R.id.checkoutTravelClassInfo)
        val PassengerCount = findViewById<TextView>(R.id.passanger_count)
        val TotalCost = findViewById<TextView>(R.id.total_cost)

        From.text = from
        FromTime.text = fromTime
        TrainName.text = trainName
        To.text = to
        ToTime.text = toTime
        TravelClass.text = travelClass
        ClassInfo.text = trainName
        PassengerCount.text = passengerCount
        FromDate.text = fromDate
        ToDate.text = toDate
        FromName.text = fromName
        ToName.text = toName
        PassengerCount.text = passengerCount

        val totalCost = cost.toDouble() * passengerCount.toInt()
        TotalCost.text = totalCost.toString()

        passengerList = ArrayList()
        for (i in 0 until passengerCount.toInt()) {
            val passenger = intent.getParcelableExtra<Passenger>("passenger$i")
            if (passenger != null) {
                passengerList.add(passenger)
            }
        }

        val payButton = findViewById<TextView>(R.id.bt_payment)
        payButton.setOnClickListener {
            val intent = Intent(this, Payment::class.java).apply {
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
                putExtra("userId", userId)
                putExtra("totalCost", totalCost)
            }
            // Add passengers data to intent
            for (i in 0 until passengerCount.toInt()) {
                val thePassenger = passengerList[i]
                intent.putExtra("passenger$i", thePassenger)
            }
            startActivity(intent)
        }

    }
}
