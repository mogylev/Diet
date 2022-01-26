package com.mohylov.diet.ui.presentation.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewBindingPropertyDelegate<V : ViewBinding>(
    fragment: Fragment,
    private val factory: (View) -> V
) : ReadOnlyProperty<Fragment, V> {

    private var binding: V? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleObserver ->
                    viewLifecycleObserver.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): V {
        val binding = binding
        if (binding != null) {
            return binding
        }
        return binding ?: factory.invoke(thisRef.requireView())
    }

}

fun <V : ViewBinding> Fragment.viewBinding(factory: (View) -> V) =
    ViewBindingPropertyDelegate(this, factory)