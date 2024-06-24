package com.example.velotrain

import adapters.StationAdapter
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.velotrain.databinding.ActivityPickStationBinding
import entities.Station
import models.station

class PickStation : AppCompatActivity() {

    private lateinit var binding: ActivityPickStationBinding
    private lateinit var adapter: StationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pick_station)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityPickStationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Pilih Stasiun"

        val stations = Station.getStations()

        val listView: ListView = findViewById(R.id.lv_stations)
        listView.adapter = StationAdapter(this, stations)
        listView.setOnItemClickListener { _, _, position, _ ->
            val returnIntent = Intent(
                this,
                MainActivity::class.java
            )
            returnIntent.putExtra("selected_station_code", stations[position].code)
            returnIntent.putExtra("selected_station_name", stations[position].name)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}