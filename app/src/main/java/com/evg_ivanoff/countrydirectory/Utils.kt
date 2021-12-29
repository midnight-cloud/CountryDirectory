package com.evg_ivanoff.countrydirectory

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.*

fun languagesToString(langs: List<Language>): String {
//    var res = ""
//    for((index, lang) in langs.withIndex()){
//        if (index != langs.lastIndex)
//            res += "${lang.name}, "
//        else
//            res += lang.name
//    }
//    return res
    return langs.joinToString { it.name }
}

fun formatNumber(num: Long): String {
    val str = NumberFormat.getInstance(Locale("ru", "RU")).format(num)
    return str
}

suspend fun loadSvg(imageView: ImageView, url: String){
    if(url.lowercase(Locale.ENGLISH).endsWith("svg")){
        val imageLoader = ImageLoader.Builder(imageView.context)
            .componentRegistry{
                add(SvgDecoder(imageView.context))
            }
            .build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .target(imageView)
            .build()
        imageLoader.execute(request)
    } else {
        imageView.load(url)
    }
}