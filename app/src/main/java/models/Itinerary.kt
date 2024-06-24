package models

data class Itinerary (
    val cost: String,
    val from: String,
    val fromTime: String,
    val sisa: String,
    val trainName: String,
    val to: String,
    val toTime: String,
    val travelClass: String,
    val travelTime: String
) {
    companion object {
        fun getSampleItineraries(startStation: String, endStation: String): ArrayList<Itinerary> {
            val itineraries: ArrayList<Itinerary> = arrayListOf(
                Itinerary("149.000", "PSE", "14:00", "Sisa 2", "Joglosemarkerto", "SGU", "18:35", "Ekonomi - A", "6 jam 35 menit"),
                Itinerary("74.000", "PSE", "06:30", "Sisa 24", "Joglosemarkerto", "SGU", "11:35", "Ekonomi - C", "5 jam 5 menit"),
                Itinerary("335.000", "PSE", "03:00", "", "Bima", "SGU", "06:35", "Eksekutif - A", "3 jam 35 menit"),
                Itinerary("149.000", "PSE", "14:00", "Sisa 2", "Bengawan", "SGU", "18:35", "Ekonomi - A", "6 jam 35 menit"),
                Itinerary("149.000", "PSE", "14:00", "Sisa 2", "Joglosemarkerto", "SGU", "18:35", "Ekonomi - A", "6 jam 35 menit"),
                Itinerary("410.000", "BD", "20.00", "Sisa 3", "Mutiara Selatan", "SGU", "07.08", "Ekonomi - C", "11 jam 8 menit"),
                Itinerary("600.000", "BD", "18:10", "Sisa 4", "Turangga", "SGU", "04:21", "Ekonomi - B", "10 jam 10 menit"),
                Itinerary("680.000", "BD", "07.40", "Sisa 2", "Argo Wilis", "SGU", "17:35", "Ekonomi - A", "9 jam 35 menit"),
                Itinerary("1.000.000", "BD", "18.10", "Tersedia", "Turangga Panoramic", "SGU", "04:21", "Eksekutif - C", "10 jam 11 menit"),
                Itinerary("1.250.000", "BD", "07.40", "Sisa 2", "Wilis Panoramic", "SGU", "17:35", "Eksekutif - C", "9 jam 55 menit"),
                Itinerary("440.000", "BD", "06.56", "Tersedia", "Lodaya", "SGU", "13:55", "Eksekutif - A", "7 jam 0 menit"),
                Itinerary("625.000", "BD", "07.40", "Tersedia", "Argo Wilis", "SGU", "13:35", "Eksekutif - B", "5 jam 55 menit"),
                Itinerary("490.000", "PSE", "08:30", "Tersedia", "Joglosemarkerto", "YK", "12:55", "Ekonomi - B", "4 jam 25 menit"),
                Itinerary("700.000", "GMR", "06:00", "Sisa 1", "Argo Bromo Anggrek", "SGU", "12:45", "Eksekutif - A", "6 jam 45 menit"),
                Itinerary("380.000", "BKS", "09:45", "Sisa 5", "Harina", "BKS", "14:10", "Ekonomi - A", "4 jam 25 menit"),
                Itinerary("550.000", "BKS", "15:20", "Tersedia", "Tawang Alun", "YK", "21:45", "Ekonomi - B", "6 jam 25 menit"),
                Itinerary("820.000", "CN", "12:15", "Sisa 2", "Arjuna", "SL", "17:35", "Eksekutif - C", "5 jam 20 menit"),
                Itinerary("950.000", "YK", "09:30", "Tersedia", "Gajayana", "MLK", "15:10", "Eksekutif - B", "5 jam 40 menit"),
                Itinerary("560.000", "PWS", "07:20", "Sisa 3", "Progo", "SMC", "12:45", "Ekonomi - A", "5 jam 25 menit"),
                Itinerary("670.000", "KSB", "18:00", "Tersedia", "Gaya Baru Malam Selatan", "CRB", "23:35", "Eksekutif - C", "5 jam 35 menit"),
                Itinerary("710.000", "MLK", "11:40", "Sisa 1", "Jayabaya", "AJ", "17:25", "Eksekutif - B", "5 jam 45 menit"),
                Itinerary("540.000", "SBP", "13:00", "Tersedia", "Logawa", "SMC", "18:30", "Ekonomi - A", "5 jam 30 menit"),
                Itinerary("490.000", "AJ", "14:30", "Sisa 4", "Mutiara Timur", "YK", "20:10", "Ekonomi - B", "5 jam 40 menit"),
                Itinerary("830.000", "SMC", "08:10", "Tersedia", "Turangga", "SGU", "14:45", "Eksekutif - A", "6 jam 35 menit"),
                Itinerary("570.000", "CRB", "17:30", "Sisa 2", "Jayakarta", "MN", "22:40", "Ekonomi - A", "5 jam 10 menit"),
                Itinerary(
                    "410.000", "BD", "20:00", "Sisa 3", "Mutiara Selatan", "SGU", "07:08", "Ekonomi - C", "11 jam 8 menit"
                ),
                Itinerary(
                    "600.000", "BD", "18:10", "Sisa 4", "Turangga", "SGU", "04:21", "Ekonomi - B", "10 jam 10 menit"
                ),
                Itinerary(
                    "680.000", "BD", "07:40", "Sisa 2", "Argo Wilis", "SGU", "17:35", "Ekonomi - A", "9 jam 35 menit"
                ),
                Itinerary(
                    "1.000.000", "BD", "18:10", "Tersedia", "Turangga Panoramic", "SGU", "04:21", "Eksekutif - C", "10 jam 11 menit"
                ),
                Itinerary(
                    "1.250.000", "BD", "07:40", "Sisa 2", "Wilis Panoramic", "SGU", "17:35", "Eksekutif - C", "9 jam 55 menit"
                ),
                Itinerary(
                    "440.000", "BD", "06:56", "Tersedia", "Lodaya", "YK", "13:55", "Eksekutif - A", "7 jam 0 menit"
                ),
                Itinerary(
                    "625.000", "BD", "07:40", "Tersedia", "Argo Wilis", "YK", "13:35", "Eksekutif - B", "5 jam 55 menit"
                ),
                Itinerary(
                    "320.000", "SGU", "14:25", "Sisa 12", "Jayabaya", "SMC", "19:06", "Ekonomi - B", "4 jam 41 menit"
                ),
                Itinerary(
                    "18.000", "SGU", "04:32", "Sisa 1", "CL Dhoho CL Penataran", "KSB", "10:18", "Ekonomi - B", "5 jam 46 menit"
                ),
                Itinerary(
                    "170.000", "SGU", "15:50", "Sisa 3", "Pasundan", "CRB", "08:06", "Ekonomi - C", "2 jam 16 menit"
                ),
                Itinerary(
                    "205.000", "SGU", "14:10", "Sisa 15", "Jayakarta", "SMC", "16:12", "Ekonomi - C", "2 jam 2 menit"
                ),
                Itinerary(
                    "250.000", "SGU", "07:00", "Sisa 1", "Sancaka", "YK", "11:00", "Ekonomi - B", "4 jam 0 menit"
                ),
                Itinerary(
                    "490.000", "SGU", "08:15", "Sisa 5", "Argo Wilis", "YK", "11:57", "Eksekutif - B", "3 jam 42 menit"
                ),
                Itinerary(
                    "165.000", "SGU", "05:50", "Sisa 10", "Pasundan", "PWS", "10:23", "Ekonomi - A", "4 jam 33 menit"
                ),
                Itinerary(
                    "275.000", "SGU", "12:00", "Sisa 4", "Gaya Baru Malam Selatan", "PWS", "11:00", "Ekonomi - C", "4 jam 7 menit"
                ),
                Itinerary(
                    "170.000", "CRB", "08:08", "Sisa 1", "Sancaka", "PWS", "10:23", "Ekonomi - B", "2 jam 15 menit"
                ),
                Itinerary(
                    "88.000", "CRB", "17:16", "Sisa 9", "Sri Tanjung", "PWS", "19:20", "Ekonomi - C", "2 jam 04 menit"
                ),
                Itinerary(
                    "350.000", "CRB", "06:20", "Tersedia", "Bangun Karta", "PSE", "16:53", "Ekonomi - C", "10 jam 33 menit"
                ),
                Itinerary(
                    "280.000", "CRB", "16:26", "Sisa 2", "Brantas", "PSE", "02:51", "Ekonomi - C", "10 jam 25 menit"
                ),
                Itinerary(
                    "195.000", "CRB", "12:02", "Tersedia", "Malioboro Expres", "MLK", "16:00", "Ekonomi - A", "3 jam 58 menit"
                ),
                Itinerary(
                    "325.000", "CRB", "06:20", "Tersedia", "Bangunkarta", "MLK", "08:55", "Ekonomi - C", "2 jam 36 menit"
                )
            )
            // Filter itineraries by startStation and endStation
            val filteredItineraries = itineraries.filter { Itinerary ->
                Itinerary.from.equals(startStation, ignoreCase = true) &&
                        Itinerary.to.equals(endStation, ignoreCase = true)
            }
            return ArrayList(filteredItineraries)
        }
    }
}
