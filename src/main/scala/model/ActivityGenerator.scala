package model

import scala.util.Random

object ActivityGenerator {
    private val activities = List(
        "Запущены новогодние скидки",
        "Запущена реклама на ТВ",
        "Запущена реклама на радио",
        "Запущена рекламная компания",
        "Наняты промоутеры",
        "Заключен контракт с турфирмой"
    )
    private val random = new Random()

    def getActivity = {
        val num = random.nextInt(activities.length - 1)
        activities(num)
    }
}
