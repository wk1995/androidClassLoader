package com.example.android.classloader

import dalvik.system.DexFile

// 冲突检测工具
fun checkClassConflict(dexPath: String): Set<String> {
    val dexFile = DexFile(dexPath)
    val existingClasses = getLoadedClasses()
    return dexFile.entries().asSequence()
        .filter { existingClasses.contains(it) }
        .toSet()
}