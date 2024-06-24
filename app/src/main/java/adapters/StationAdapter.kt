package adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.velotrain.R
import entities.Station
import models.Itinerary
import models.station

class StationAdapter(private val context: Activity, private val arrayList: ArrayList<station>)
    : ArrayAdapter<station>(context, R.layout.station_row, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = convertView ?: inflater.inflate(R.layout.station_row, parent, false)

        val stationName: TextView = view.findViewById(R.id.station_name)
        val stationCode: TextView = view.findViewById(R.id.station_code)
        val zoneName: TextView = view.findViewById(R.id.zone_name)

        val station = arrayList[position]

        stationName.text = station.name
        stationCode.text = station.code
        zoneName.text = station.zone

        return view
    }
}