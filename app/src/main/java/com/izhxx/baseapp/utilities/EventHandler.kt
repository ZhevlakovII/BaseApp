package com.izhxx.baseapp.utilities

interface EventHandler<T> {
    fun obtainEvent(event: T)
}