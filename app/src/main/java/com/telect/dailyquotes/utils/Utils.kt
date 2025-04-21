package com.telect.dailyquotes.utils

import android.text.Html

object Utils {

    fun convertHtmlToString(html: String): String {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
    }
}