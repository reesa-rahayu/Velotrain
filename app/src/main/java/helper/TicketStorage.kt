package helper
import android.content.Context
import entities.Ticket

object TicketStorage {
    private val ticketList = mutableListOf<Ticket>()

    fun saveTicket(context: Context, ticket: Ticket) {
        ticketList.add(ticket)
    }

    fun getTicketList(context: Context): List<Ticket> {
        return ticketList.toList()
    }
}