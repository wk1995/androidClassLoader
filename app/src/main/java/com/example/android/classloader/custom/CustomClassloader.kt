package com.example.android.classloader.custom

import timber.log.Timber

class CustomClassloader(parent: ClassLoader?) : ClassLoader(parent) {

    companion object {
        private const val TAG = "CustomClassloader"
    }

    override fun findClass(name: String?): Class<*> {
        Timber.d("findClass name $name")
        return super.findClass(name)
    }


    override fun loadClass(name: String?): Class<*> {
        Timber.d("loadClass name $name")
        val clazz=findLoadedClass(name)
        Timber.d("findLoadedClass clazz $clazz")
        return super.loadClass(name)
    }

}