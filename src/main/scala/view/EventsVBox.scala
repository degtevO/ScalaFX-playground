package view

import viewmodel.Events

import scalafx.Includes._
import scalafx.scene.control.{Label, ListView}
import scalafx.scene.layout.{StackPane, VBox}

class EventsVBox(spacing: Int) extends VBox(spacing) {
    private val eventsList = new ListView[String]
    eventsList.items = Events.eventsList

    children = Seq(
        new Label("Последние события"),
        new StackPane {
            children = eventsList
        }
    )
}
