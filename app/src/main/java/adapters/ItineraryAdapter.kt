package adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.velotrain.R
import models.Itinerary

class ItineraryAdapter
    (private val context: Activity, private val arrayList: ArrayList<Itinerary>)
    : ArrayAdapter<Itinerary>(context, R.layout.itinerary_row, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.itinerary_row, null)

        val cost: TextView = view.findViewById(R.id.itineraryCost)
        val from: TextView = view.findViewById(R.id.itineraryFrom)
        val fromTime: TextView = view.findViewById(R.id.itineraryFromTime)
        val sisa: TextView = view.findViewById(R.id.itinerarySisa)
        val title: TextView = view.findViewById(R.id.itineraryTitle)
        val to: TextView = view.findViewById(R.id.itineraryTo)
        val toTime: TextView = view.findViewById(R.id.itineraryToTime)
        val travelClass: TextView = view.findViewById(R.id.itineraryTravelClass)
        val travelClassInfo: TextView = view.findViewById(R.id.itineraryTravelClassInfo)

        cost.text = arrayList[position].cost
        from.text = arrayList[position].from
        fromTime.text = arrayList[position].fromTime
        sisa.text = arrayList[position].sisa
        title.text = arrayList[position].trainName
        to.text = arrayList[position].to
        toTime.text = arrayList[position].toTime
        travelClass.text = arrayList[position].travelClass
        travelClassInfo.text = arrayList[position].travelTime

        return view
    }
}