package com.rafaelbarbosatec.sdk.core.livecycle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

abstract class RepositoryLiveCycle : LifecycleOwner {

    private val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    constructor() {
        start()
    }

    fun start() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun destroy() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

}