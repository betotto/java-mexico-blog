package javamexico.blog.utils

import com.google.gson.GsonBuilder

object GsonParser {
    val localGson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz").create()
}