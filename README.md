# Weak Delegate

This project implements a Kotlin wrapper for the almighty (and sometimes scary) [weak references](https://docs.oracle.com/javase/7/docs/api/java/lang/ref/WeakReference.html) so that you can use them in a more friendly way.

## Usage

Weak references are often feared but are one of the most important tools Java and Kotlin developers have. If you are still unsure if you should use them just read one of the multiple resources linked below.

However, if you ever tried to use a weak reference in kotlin you probably found yourself writing things like:

```kotlin
class SomeClass {
    private var wr: WeakReference<Data>? = null

    fun foo(data: Data) {
        wr = WeakReference(data)
    }

    fun bar() {
        val sr = wr?.get()

        if (sr != null) {
            print(sr)
        }
    }
}
```

Would not it be better if we used the full potential of [delegated properties](https://kotlinlang.org/docs/reference/delegated-properties.html) for that matter? Weak Delegate creates one for you. Compare the previous example to the usage of Weak Delegate:

```kotlin
class SomeClass {
    private val weakRef: Data? by weak(Data("Some Data"))
    private var weakRefVar: Data? by weak()

    fun set(data: Data) {
        weakRefVar = data
    }

    fun bar() {
        val strongRef = weakRef

        if (strongRef != null) {
            print(strongRef)
        }
    }
}
```

No more references to `WeakReference`s or `get` methods. Internally, Weak Delegate is doing the WeakReference <-> Nullable conversions for you.

## Installation

Add the [jitpack](https://jitpack.io/) repository and include the WeakDelegate dependency to your `build.gradle` configuration:

```groovy
repositories { 
     maven { url "https://jitpack.io" }
}
dependencies {
      compile 'com.karumi.weak:WeakDelegate:1.0.0'
}
```

## References

* [Understanding Weak References Blog](https://community.oracle.com/blogs/enicholas/2006/05/04/understanding-weak-references)
* [Weak, Soft, and Phantom References in Java (and Why They Matter)](https://dzone.com/articles/weak-soft-and-phantom-references-in-java-and-why-they-matter)
* [Java: difference between strong/soft/weak/phantom reference](https://stackoverflow.com/a/23519721/3959570)

License
-------

    Copyright 2017 Karumi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
