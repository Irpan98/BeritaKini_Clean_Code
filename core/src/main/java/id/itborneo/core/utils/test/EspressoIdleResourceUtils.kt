package id.itborneo.core.utils.test

import android.util.Log
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        Log.d("EspressoIdlingResource", espressoTestIdlingResource.toString())
        espressoTestIdlingResource.decrement()

    }

    fun idlingResource(): IdlingResource {
        return espressoTestIdlingResource
    }
}