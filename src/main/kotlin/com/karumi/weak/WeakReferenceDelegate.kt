package com.karumi.weak

import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

fun <T> weak(value: T) = WeakReferenceDelegateReadOnly(value)
fun <T> weakVar() = WeakReferenceDelegate<T>()

class WeakReferenceDelegateReadOnly<out T>(value: T) {
    private val weakReference: WeakReference<T> = WeakReference(value)
    operator fun getValue(thisRef: Any, property: KProperty<*>): T? = weakReference.get()
}

class WeakReferenceDelegate<T> {
    private var weakReference: WeakReference<T>? = null
    operator fun getValue(thisRef: Any, property: KProperty<*>): T? = weakReference?.get()
    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        weakReference = WeakReference(value)
    }
}