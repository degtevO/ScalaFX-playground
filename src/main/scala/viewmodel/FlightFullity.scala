package viewmodel

import javafx.scene.{chart => jfxsc}

import model.App

import scala.language.implicitConversions
import scala.util.Random
import scalafx.animation.PauseTransition
import scalafx.collections.ObservableBuffer
import scalafx.scene.chart.XYChart
import scalafx.util.Duration

object FlightFullity {
    val chartData = new ObservableBuffer[jfxsc.XYChart.Series[String, Number]]
    buildChartData match {
        case (airflot, redWings, azur, s7, pobeda, utAir, transAir) => chartData.addAll(airflot, redWings, azur, s7, pobeda, utAir, transAir)
    }

    private val timer = new PauseTransition(Duration(5000))
    timer.onFinished = { _ =>
        if (App.isAppEnabled && App.isSearchEnabled) {
            updateChartData()
        }

        timer.playFromStart()
    }
    timer.play

    private def updateChartData() = buildChartData match {
        case (airflot, redWings, azur, s7, pobeda, utAir, transAir) =>
            chartData.update(0, airflot)
            chartData.update(1, redWings)
            chartData.update(2, azur)
            chartData.update(3, s7)
            chartData.update(4, pobeda)
            chartData.update(5, utAir)
            chartData.update(6, transAir)
    }

    private def buildChartData = {
        val r = new Random()
        val AirflotValue = 90 + r.nextInt(10) - 3
        val RedWingsValue = 30 + r.nextInt(50) - 3
        val S7Value = 30 + r.nextInt(50) - 3
        val AzurValue = 90 + r.nextInt(10) - 3
        val PobedaValue = 60 + r.nextInt(20) - 3
        val UTAirValue = 20 + r.nextInt(60) - 3
        val TransAirValue = 60 + r.nextInt(20) - 3

        val Airflot = new XYChart.Series[String, Number]
        val RedWings = new XYChart.Series[String, Number]
        val Azur = new XYChart.Series[String, Number]
        val S7 = new XYChart.Series[String, Number]
        val Pobeda = new XYChart.Series[String, Number]
        val UTAir = new XYChart.Series[String, Number]
        val TransAir = new XYChart.Series[String, Number]

        Airflot.getData.add(XYChart.Data[String, Number]("Airflot", AirflotValue))
        RedWings.getData.add(XYChart.Data[String, Number]("RedWings", RedWingsValue))
        Azur.getData.add(XYChart.Data[String, Number]("Azur", AzurValue))
        S7.getData.add(XYChart.Data[String, Number]("S7", S7Value))
        Pobeda.getData.add(XYChart.Data[String, Number]("Pobeda", PobedaValue))
        UTAir.getData.add(XYChart.Data[String, Number]("UTAir", UTAirValue))
        TransAir.getData.add(XYChart.Data[String, Number]("TransAir", TransAirValue))

        (Airflot, RedWings, Azur, S7, Pobeda, UTAir, TransAir)
    }
}
