package view

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.{HBox, VBox}


object Main extends JFXApp {
    private val borderStyle = "" +
        "-fx-background-color: white;" +
        "-fx-border-color: black;" +
        "-fx-border-width: 1;" +
        "-fx-border-radius: 3;" +
        "-fx-padding: 6;" +
        "-fx-font-size: 14px;"

    stage = new PrimaryStage() {
        title = "Avia Operator"
        scene = new Scene {
            root = new VBox(8) {
                children = Seq(
                    new Label("Оператор: Ивашкова А.Д.") {
                        style = "-fx-padding: 6;" +
                            "-fx-font-size: 14px;"
                    },
                    new DigitalClock {
                        style = "-fx-padding: 6;" +
                            "-fx-font-size: 14px;"
                    },
                    new HBox(15) {
                        children = Seq(
                            new TicketsVBox(6) {
                                style = borderStyle; margin = Insets(6);
                            },
                            new FlightFullityVBox(6) {
                                style = borderStyle; margin = Insets(6)
                            },
                            new EventsVBox(6) {
                                style = borderStyle; margin = Insets(6)
                            }
                        )
                    }
                )
            }
        }
    }
}
