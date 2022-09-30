package uz.msit.demo.core.utils

import uz.msit.demo.BuildConfig

object ApiEndpoint {
    private const val STAGE = "https://api.github.com/"
    private const val PRODUCTION = "https://api.github.com/"
    val BASE_URL = if(BuildConfig.DEBUG) STAGE else PRODUCTION
}