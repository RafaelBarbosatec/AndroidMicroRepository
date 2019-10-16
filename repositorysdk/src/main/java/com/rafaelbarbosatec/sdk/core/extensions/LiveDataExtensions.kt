package com.rafaelbarbosatec.sdk.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.listen(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    val ob = Observer<T> {
        it?.let(observer)
    }
    this.observe(owner, ob)
}