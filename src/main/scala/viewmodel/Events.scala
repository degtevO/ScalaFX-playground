package viewmodel

import java.text.SimpleDateFormat
import java.util.Date

import model.{ActivityGenerator, App}
import view.Main

import scala.util.Random
import scalafx.animation.PauseTransition
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonType}
import scalafx.util.Duration

object Events {
    private val dateFormat = new SimpleDateFormat("HH:mm:ss")

    val eventsList = new ObservableBuffer[String]
    addEvent("Запуск приложения")

    private var systemInProblem = false
    private var random = new Random()

    private val timer = new PauseTransition(Duration(1000))
    timer.onFinished = { _ =>
        if (!systemInProblem) generateTrouble
        timer.playFromStart()
    }
    timer.play

    private def generateTrouble = {
        val num = random.nextInt(20)

        if (num == 5) {
            systemInProblem = true
            addEvent("Обнаружена низкая загруженность рейсов")
            alert1
        } else if (num == 10) {
            systemInProblem = true
            addEvent("Обнаружена высокая нагрузка на сервера")
            alert2
        } else if (num == 15) {
            systemInProblem = true
            addEvent("Обнаружен сбой ГДС")
            alert3
        }
    }

    private val buttonCost = new ButtonType("Снизить цены")
    private val buttonActivity = new ButtonType("Создать акцию")
    private val buttonWorks = new ButtonType("Закрыться на профилактические работы")
    private val buttonSearch = new ButtonType("Заблокировать поиск на сайте")

    private def alert1 = {
        val result = new Alert(AlertType.Warning) {
            initOwner(Main.stage)
            title = "Warning!!!"
            headerText = s"Рейс №${random.nextInt(899) + 100} заполнен меньше чем на 60%!"
            contentText = "Что будем делать?"
            buttonTypes = Seq(buttonCost, buttonActivity, ButtonType.Cancel)
        }.showAndWait()

        result match {
            case Some(`buttonCost`) => addEvent("Снижены цены на некоторые непопулярные направления")
            case Some(`buttonActivity`) => addEvent(ActivityGenerator.getActivity)
            case _ => Unit
        }

        systemInProblem = false
    }

    private def alert2 = {
        val result = new Alert(AlertType.Warning) {
            initOwner(Main.stage)
            title = "Warning!!!"
            headerText = "Перегрузка сервера!"
            contentText = "Нужно срочно переезжать на другой сервер!"
            buttonTypes = Seq(buttonWorks, ButtonType.Cancel)
        }.showAndWait()

        result match {
            case Some(`buttonWorks`) => closeApp(random.nextInt(7) + 3)
            case _ => systemInProblem = false
        }
    }

    private def alert3 = {
        val result = new Alert(AlertType.Warning) {
            initOwner(Main.stage)
            title = "Warning!!!"
            headerText = "ГДС закрылась на проф.работы!"
            contentText = "Что будем делать?"
            buttonTypes = Seq(buttonWorks, buttonSearch, ButtonType.Cancel)
        }.showAndWait()

        result match {
            case Some(`buttonWorks`) => closeApp(random.nextInt(7) + 3)
            case Some(`buttonSearch`) => closeSearch(random.nextInt(7) + 3)
            case _ => systemInProblem = false
        }
    }

    private def addEvent(msg: String) = {
        val dateStr = dateFormat format new Date
        eventsList.add(s"$dateStr - $msg")
    }

    private def closeApp(timeout: Int) = {
        addEvent("Сервера закрыты на профилактические работы")
        App.isAppEnabled = false
        val timer = new PauseTransition(Duration(timeout * 1000))
        timer.onFinished = { _ =>
            addEvent("Профилактические работы завершены")
            App.isAppEnabled = true
            systemInProblem = false
        }
        timer.play
    }

    private def closeSearch(timeout: Int) = {
        addEvent("Поиск на сайте заблокирован")
        App.isAppEnabled = false
        val timer = new PauseTransition(Duration(timeout * 1000))
        timer.onFinished = { _ =>
            addEvent("ГДС вновь доступна. Поиск разблокирован")
            App.isAppEnabled = true
            systemInProblem = false
        }
        timer.play
    }
}
