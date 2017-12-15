package com.karumi.weak

import org.junit.Test

class WeakReferenceDelegateTest {

    private val strongReference = SomeClass("Strong reference")
    private val weakReference: SomeClass? by weak(strongReference)
    private val nullWeakReference: SomeClass? by weak(SomeClass("Null weak reference"))
    private var weakVarReference: SomeClass? by weakVar()

    @Test
    fun `Weak reference delegate should return empty optional if reference is cleaned up`() {
        System.gc()

        assert(nullWeakReference == null)
    }

    @Test
    fun `Weak reference delegate should return stored value if strong reference is still present`() {
        System.gc()

        assert(weakReference == strongReference)
    }

    @Test
    fun `Var weak reference delegate should return empty optional if reference is cleaned up`() {
        weakVarReference = SomeClass("Var null weak reference")

        System.gc()

        assert(weakVarReference == null)
    }

    @Test
    fun `Var weak reference delegate should return stored value if strong reference is still present`() {
        val strongReference = SomeClass("Var strong reference")
        weakVarReference = strongReference

        System.gc()

        assert(weakVarReference == strongReference)
    }

    private data class SomeClass(val value: String)
}