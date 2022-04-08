package com.mohylov.diet.ui.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


class ScopedComponentDelegate<T>(
    private val viewModelStoreOwner: ViewModelStoreOwner,
    private val componentProvider: () -> T
) : Lazy<T> {


    private var cachedComponent: T? = null

    override fun isInitialized(): Boolean {
        return cachedComponent != null
    }

    override val value: T
        get() {
            val component = cachedComponent
            if (component != null) return component

            val provider =
                ViewModelProvider(viewModelStoreOwner, object : ViewModelProvider.Factory {

                    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
                        return ComponentHolder(componentProvider()) as VM
                    }

                })
            val componentHolder = provider.get(ComponentHolder::class.java)

            return (componentHolder.component as T).also { cachedComponent = it }
        }

}

fun <T> ViewModelStoreOwner.scopedComponent(componentProvider: () -> T): Lazy<T> {
    return ScopedComponentDelegate(this, componentProvider)
}