package com.example.android.classloader

import android.content.Context
import dalvik.system.DexClassLoader

class DynamicClassLoader(parent: ClassLoader,context:Context) : DexClassLoader(
    "/sdcard/plugin.dex",
    context.getDir("dex", Context.MODE_PRIVATE).absolutePath,
    null,
    parent // 可配置的父加载器
) {
    // 重写loadClass打破双亲委派
    override fun loadClass(name: String, resolve: Boolean): Class<*> {
        return if (name.startsWith("com.plugin.")) {
            findClass(name) // 优先自己加载
        } else {
            super.loadClass(name, resolve)
        }
    }
}