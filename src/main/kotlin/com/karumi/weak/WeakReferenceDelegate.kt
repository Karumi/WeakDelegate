package com.karumi.weak

import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

inline fun <reified T> weak(value: T? = null) = WeakReferenceDelegate(value)

class WeakReferenceDelegate<T>(v: T? = null) {
    private var weakReference: WeakReference<T?> = WeakReference(v)

    operator fun getValue(thisRef: Any, property: KProperty<*>): T? = weakReference.get()
    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        weakReference = WeakReference(value)
    }
}
