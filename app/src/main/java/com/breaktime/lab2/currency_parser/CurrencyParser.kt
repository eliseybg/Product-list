package com.breaktime.lab2.currency_parser

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class CurrencyParser @Inject constructor() {
    var data = HashMap<String, Pair<Double, Double>>()

    fun update() {
        val document: Document

        try {
            document = Jsoup.connect("https://myfin.by/currency/minsk").get()
        } catch (e: Exception) {
            Log.d("ERROR", "update: ${e.message}")
            return
        }
        val answer = document.body().html()
        var index = answer.indexOf("best: 'a:13:{s:3:")
        val page = answer.substring(index + 13, index + 198)
        index = page.indexOf("\"")
        val resList = mutableListOf<String>()
        while (index != -1) {
            val endIndex = page.indexOf("\"", index + 1)
            resList += page.substring(index + 1, endIndex)
            index = page.indexOf("\"", endIndex + 1)
        }
        data[resList[0]] = resList[2].toDouble() to resList[4].toDouble()
        data[resList[5]] = resList[7].toDouble() to resList[9].toDouble()
        data[resList[10]] = resList[12].toDouble() * 100 to resList[14].toDouble() * 100
    }
}