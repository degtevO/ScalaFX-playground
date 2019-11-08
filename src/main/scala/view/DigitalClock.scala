package view

import java.text.SimpleDateFormat
import java.util.Date

import scalafx.animation.PauseTransition
import scalafx.scene.control.Label
import scalafx.util.Duration

class DigitalClock extends Label {
    private val dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")

    this.setText(getCurTimeStr)

    private val timer = new PauseTransition(Duration(1000))
    timer.onFinished = { _ =>
        this.setText(getCurTimeStr)
        timer.playFromStart()
    }
    timer.play

    private def getCurTimeStr = dateFormat format new Date
}
