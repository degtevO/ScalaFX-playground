package viewmodel

import model.App

import scala.util.Random
import scalafx.animation.PauseTransition
import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalafx.util.Duration

object Tickets {
    val allSoldTickets = IntegerProperty(0)
    val soldTicketsAirflot = IntegerProperty(0)
    val soldTicketsRedWings = IntegerProperty(0)
    val soldTicketsAzur = IntegerProperty(0)
    val soldTicketsS7 = IntegerProperty(0)
    val soldTicketsPobeda = IntegerProperty(0)
    val soldTicketsUTAir = IntegerProperty(0)
    val soldTicketsTransAir = IntegerProperty(0)

    private val random = new Random()

    private val timer = new PauseTransition(Duration(1000))
    timer.onFinished = { _ =>
        if (App.isAppEnabled && App.isSearchEnabled) {
            soldTicketsAirflot.value = getSoldTickets(90, 7) + soldTicketsAirflot.value
            soldTicketsRedWings.value = getSoldTickets(30, 7) + soldTicketsRedWings.value
            soldTicketsAzur.value = getSoldTickets(30, 7) + soldTicketsAzur.value
            soldTicketsS7.value = getSoldTickets(90, 7) + soldTicketsS7.value
            soldTicketsPobeda.value = getSoldTickets(60, 7) + soldTicketsPobeda.value
            soldTicketsUTAir.value = getSoldTickets(20, 7) + soldTicketsUTAir.value
            soldTicketsTransAir.value = getSoldTickets(60, 7) + soldTicketsTransAir.value

            recountAllTickets
        }

        timer.playFromStart()
    }
    timer.play

    private def getSoldTickets(chance: Int, maxCount: Int) =
        if (isSold(chance)) {
            random.nextInt(maxCount)
        } else {
            0
        }

    private def isSold(chance: Int) = if (random.nextInt(100) <= chance) true else false

    private def recountAllTickets = {
        allSoldTickets.value = soldTicketsAirflot.value +
            soldTicketsRedWings.value +
            soldTicketsAzur.value +
            soldTicketsS7.value +
            soldTicketsPobeda.value +
            soldTicketsUTAir.value +
            soldTicketsTransAir.value
    }
}
