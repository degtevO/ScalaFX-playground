package view

import viewmodel.FlightFullity

import scalafx.scene.chart.{BarChart, CategoryAxis, NumberAxis}
import scalafx.scene.layout.{StackPane, VBox}

class FlightFullityVBox(spacing: Int) extends VBox(spacing) {
    private val xAxis = new CategoryAxis()
    private val yAxis = new NumberAxis()
    private val barChart = BarChart(xAxis, yAxis)

    barChart.title = "Заполняемость рейсов"
    barChart.barGap = 1
    barChart.animated = false
    barChart.data = FlightFullity.chartData

    children = Seq(
        new StackPane {
            children = barChart
        }
    )
}
