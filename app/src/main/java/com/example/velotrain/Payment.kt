package com.example.velotrain

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import entities.Passenger
import entities.Ticket
import helper.TicketStorage.saveTicket
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class Payment : AppCompatActivity() {
    private lateinit var cost: String
    private lateinit var from: String
    private lateinit var fromTime: String
    private lateinit var sisa: String
    private lateinit var trainName: String
    private lateinit var to: String
    private lateinit var toTime: String
    private lateinit var travelClass: String
    private lateinit var travelTime : String
    private lateinit var passengerCount: String
    private lateinit var fromName: String
    private lateinit var toName: String
    private lateinit var fromDate: String
    private lateinit var toDate: String
    private var totalCost: Double = 0.0
    private var userId: Long = 0

    private lateinit var passengerList: ArrayList<Passenger>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
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
        fromName = intent.getStringExtra("startStationName").toString()
        toName = intent.getStringExtra("endStationName").toString()
        fromDate = intent.getStringExtra("dateDeparture").toString()
        toDate = intent.getStringExtra("dateDeparture").toString()
        userId = intent.getLongExtra("userId", 0)
        passengerCount = intent.getStringExtra("passengerCount").toString()
        totalCost = intent.getDoubleExtra("totalCost", 0.0)

        val TrainName = findViewById<TextView>(R.id.paymentTrainName)
        val TotalCost = findViewById<TextView>(R.id.payment_total_cost)
        val ExpDate = findViewById<TextView>(R.id.paymentExpDateTime)
        val TrainCode = findViewById<TextView>(R.id.paymentTrainCode)

        TrainName.text = trainName
        TotalCost.text = totalCost.toString()

        val currentDateTime = LocalDateTime.now()
        val futureDateTime = currentDateTime.plusMinutes(10)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedFutureDateTime = futureDateTime.format(formatter)
        ExpDate.text = formattedFutureDateTime

        val randomCode = Random.nextInt(1, 500)
        TrainCode.text = randomCode.toString()

        passengerList = ArrayList()
        for (i in 0 until passengerCount.toInt()) {
            val passenger = intent.getParcelableExtra<Passenger>("passenger$i")
            if (passenger != null) {
                passengerList.add(passenger)
            }
        }

        val buttonFinish = findViewById<TextView>(R.id.bt_finish)
        buttonFinish.setOnClickListener {
            val ticketCode = generateCode()
            val newTicket = Ticket(
                1,
                ticketCode,
                trainName,
                travelClass,
                toDate,
                fromTime,
                toTime,
                travelTime,
                from,
                to,
                fromName,
                toName,
                passengerList)
            saveTicket(this, newTicket)

            val intent = Intent(this, DetailTicket::class.java).apply {
                putExtra("cost", cost)
                putExtra("from", from)
                putExtra("fromTime", fromTime)
                putExtra("trainName", trainName)
                putExtra("to", to)
                putExtra("toTime", toTime)
                putExtra("travelClass", travelClass)
                putExtra("passengerCount", passengerCount)
                putExtra("dateDeparture", fromDate)
                putExtra("startStationName", fromName)
                putExtra("endStationName", toName)
                putExtra("travelTime", travelTime)
                putExtra("userId", userId)
                putExtra("totalCost", totalCost)
                putExtra("dateDeparture", fromDate)
                putExtra("dateArrival", toDate)
                putExtra("ticketCode", ticketCode)
            }
            // Add passengers data to intent
            for (i in 0 until passengerCount.toInt()) {
                val thePassenger = passengerList[i]
                intent.putExtra("passenger$i", thePassenger)
            }
            startActivity(intent)
        }
    }
    private fun generateCode():String{
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" // Define the characters to use
        val random = Random.Default

        val paymentCode = (1..8)
            .map { chars[random.nextInt(chars.length)] }
            .joinToString("")
        return paymentCode
    }
}