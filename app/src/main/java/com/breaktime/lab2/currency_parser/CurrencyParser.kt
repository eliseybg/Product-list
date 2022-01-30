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
        val index = answer.indexOf("best: 'a:13:{s:3:")
        var page = answer.substring(index + 13, index + 198)
        this@CurrencyParser.data[page.substring(5, 8)] =
            page.substring(30, 35).toDouble() to page.substring(53, 58).toDouble()
        page = page.substring(61)
        this@CurrencyParser.data[page.substring(5, 8)] =
            page.substring(30, 35).toDouble() to page.substring(53, 58).toDouble()
        page = page.substring(61)
        this@CurrencyParser.data[page.substring(5, 8)] =
            page.substring(30, 36).toDouble() * 100 to page.substring(54, 60).toDouble() * 100
    }
}