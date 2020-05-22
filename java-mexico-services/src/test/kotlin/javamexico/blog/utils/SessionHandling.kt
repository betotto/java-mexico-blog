package javamexico.blog.utils

class SessionHandling {

    private val cache = mutableMapOf<String, String>()

    val getString = { key: String ->
        cache[key]
    }

    val setString = {(key, value): Pair<String, String> ->
        cache.put(key, value)
    }
}