package com.example.android.classloader

import java.lang.System.identityHashCode
import java.lang.reflect.Proxy
import java.util.WeakHashMap

// ClassLoaderMonitor.kt
object ClassLoaderMonitor {
    private val watchedLoaders = WeakHashMap<ClassLoader, MonitorData>()

    fun instrument(classLoader: ClassLoader) {
        val clProxy = Proxy.newProxyInstance(
            classLoader,
            arrayOf(ClassLoader::class.java)
        ) { proxy, method, args ->
            if (method.name == "loadClass") {
                recordClassLoading(classLoader, args[0] as String)
            }
            method.invoke(classLoader, *args)
        }

        // 使用反射替换ContextClassLoader
        Thread.currentThread().contextClassLoader = clProxy as ClassLoader
    }

    private fun recordClassLoading(loader: ClassLoader, className: String) {
        // 记录加载时间、调用栈等信息
    }

    fun dumpHierarchy(): String {
        val sb = StringBuilder()
        var current = ClassLoader.getSystemClassLoader()
        while (current != null) {
            sb.append("├─").append(current.javaClass.simpleName)
                .append(" (")
                .append(identityHashCode(current))
                .append(")\n")
            current = current.parent
        }
        return sb.toString()
    }
}