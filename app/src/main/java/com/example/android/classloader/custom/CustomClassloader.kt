package com.example.android.classloader.custom

import timber.log.Timber

class CustomClassloader(parent: ClassLoader?) : ClassLoader(parent) {

    companion object {
        private const val TAG = "CustomClassloader"
    }

    override fun findClass(name: String?): Class<*> {
        Timber.d("findClass name $name")
        return try {
            // 自定义查找流程，可以是从文件系统、网络或者其他地方加载类
            val classData = loadClassData(name)  // 模拟加载字节数据
            defineClass(name, classData, 0, classData.size)
        } catch (e: Exception) {
            // 如果找不到，调用父类 ClassLoader 的 findClass()
            Timber.d("findClass Class not found by CustomClassloader, delegating to parent.")
            super.findClass(name)
        }
    }

    // 模拟加载字节数据的过程
    private fun loadClassData(className: String?): ByteArray {
        Timber.d("loadClassData className: $className")
        // 这里可以使用实际的数据加载逻辑
        return ByteArray(0)  // 假设返回空数据
    }

    override fun loadClass(name: String?): Class<*> {
        Timber.d("loadClass name $name")
        val clazz = findLoadedClass(name)
        Timber.d("findLoadedClass clazz $clazz")
        return super.loadClass(name)
    }

}