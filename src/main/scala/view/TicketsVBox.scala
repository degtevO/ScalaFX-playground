package view

import viewmodel.Tickets

import scalafx.scene.control.Label
import scalafx.scene.layout.{HBox, VBox}

import scalafx.Includes._

class TicketsVBox(spacing: Int) extends VBox(spacing) {
    children = Seq(
        new Label("Количество проданных билетов"),
        new HBox(5) {
            children = Seq(
                new Label("Всего:"),
                new Label {
                    text <== Tickets.allSoldTickets.asString
                }
            )
        },
        new HBox(5) {
            children = Seq(
                new Label("Аэрофлот:"),
                new Label {
                    text <== Tickets.soldTicketsAirflot.asString
                }
            )
        },
        new HBox(5) {
            children = Seq(
                new Label("RedWings:"),
                new Label {
                    text <== Tickets.soldTicketsRedWings.asString
                }
            )
        },
        new HBox(5) {
            children = Seq(
                new Label("AzurAir:"),
                new Label {
                    text <== Tickets.soldTicketsAzur.asString
                }
            )
        },
        new HBox(5) {
            children = Seq(
                new Label("S7 Airlines:"),
                new Label {
                    text <== Tickets.soldTicketsS7.asString
                }
            )
        },
        new HBox(5) {
            children = Seq(
                new Label("Победа:"),
                new Label {
                    text <== Tickets.soldTicketsPobeda.asString
                }
            )
        },
        new HBox(5) {
            children = Seq(
                new Label("UTAir:"),
                new Label {
                    text <== Tickets.soldTicketsUTAir.asString
                }
            )
        },
        new HBox(5) {
            children = Seq(
                new Label("ТрансАэро:"),
                new Label {
                    text <== Tickets.soldTicketsTransAir.asString

                }
            )
        }
    )
}
