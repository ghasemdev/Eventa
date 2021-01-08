package com.jakode.eventa.ui.adapter

import androidx.annotation.LayoutRes

interface ViewType<out T> {
    @LayoutRes
    fun layoutId(): Int
    fun data(): T
}