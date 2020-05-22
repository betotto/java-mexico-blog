package javamexico.blog.utils

import javax.cache.Cache
import javax.cache.annotation.CacheDefaults
import javax.inject.Inject
import javax.inject.Singleton

@CacheDefaults(cacheName = "custom")
@Singleton
class SessionHandling {

    @Inject
    private var cache: Cache<String, Any>? = null

    val getString = { key: String ->
        cache?.get(key) ?: ""
    }

    val setString = {(key, value): Pair<String, String> ->
        cache?.put(key, value)
    }
}