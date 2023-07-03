

package com.example.sagapp.android.extentions


import android.util.Log
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
fun <T : Any> Any.getTClass(): Class<T> {
    val type: Type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
    return type as Class<T>
}
fun Any?.showLogMessage(tag: String? = "SAG") =
    Timber.apply {
        if (!tag.isNullOrBlank())
            tag(tag)
    }.e(this.toString())

fun Any?.showLog(tag: String? = "SAG") = Log.e(tag,this.toString())
