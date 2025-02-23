package com.example.android.classloader.custom

import timber.log.Timber

class CustomClassloaderScreenViewModel {

    companion object {
        private const val TAG = "CustomClassloaderScreenViewModel"
    }

    fun loadClassNormal(cls: Class<CustomClass>) {
        val classLoader = cls.classLoader
        classLoader?.loadClass(cls.name)
        val instance = cls.getDeclaredConstructor().newInstance()
        Timber.tag(TAG).d("loadClassNormal instance getInt ${instance.getInt()}")
    }

    fun loadClass(cls: Class<CustomClass>) {
        printCurrentClassLoaders(cls)
        val customClassloader = CustomClassloader(cls.classLoader)
        val newCls = customClassloader.loadClass(cls.name)
        val instance = newCls.getDeclaredConstructor().newInstance()
        if (instance is CustomClass) {
            Timber.tag(TAG).d("loadClassNormal instance getInt ${instance.getInt()}")
        } else {
            Timber.tag(TAG).d("loadClassNormal instance  $instance")
        }
    }

    private fun printCurrentClassLoaders(cls: Class<CustomClass>) {
        var classLoader = cls.classLoader
        while(classLoader!=null) {
            Timber.tag(TAG).d("classloader $classLoader")
            classLoader = classLoader.parent
        }
    }

}