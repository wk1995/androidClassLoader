package com.example.android.classloader

import android.util.Log

class SimpleClassLoader(parent: ClassLoader) : ClassLoader(parent) {

    companion object {
        private const val TAG = "SimpleClassLoader"
    }

    override fun loadClass(name: String?): Class<*> {
        val result = super.loadClass(name)
        Log.d(TAG, "loadClass  name: $name result: $result")
        return result
    }

    override fun findClass(name: String?): Class<*> {
        val result = super.findClass(name)
        Log.d(TAG, "findClass  name: $name result: $result")
        return result
    }
}