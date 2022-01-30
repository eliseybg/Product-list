package com.breaktime.lab2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class CurrencyParser {
    val map = HashMap<String, Pair<Double, Double>>()

    init {
        GlobalScope.launch(Dispatchers.Default) {
            val document: Document = Jsoup.connect("https://myfin.by/currency/minsk").get()
            val answer = document.body().html()
            val index = answer.indexOf("best: 'a:13:{s:3:")
            var data = answer.substring(index + 13, index + 198)
            map[data.substring(5, 8)] =
                data.substring(30, 35).toDouble() to data.substring(53, 58).toDouble()
            data = data.substring(61)
            map[data.substring(5, 8)] =
                data.substring(30, 35).toDouble() to data.substring(53, 58).toDouble()
            data = data.substring(61)
            map[data.substring(5, 8)] =
                data.substring(30, 36).toDouble() * 100 to data.substring(54, 60).toDouble() * 100
        }
    }
}