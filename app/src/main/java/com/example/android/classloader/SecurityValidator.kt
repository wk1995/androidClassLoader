package com.example.android.classloader

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import dalvik.system.DexFile
import java.io.File
import java.io.IOException


// SecurityValidator.java
object SecurityValidator {
    fun verifyDex(dexFile: File): Boolean {
        try {
            // 1. 签名校验
            val packageInfo: PackageInfo = context.getPackageManager()
                .getPackageArchiveInfo(dexFile.path, PackageManager.GET_SIGNATURES)


            // 2. 类黑名单检测
            val df = DexFile(dexFile)
            val entries = df.entries()
            while (entries.hasMoreElements()) {
                val className = entries.nextElement()
                if (isInBlacklist(className)) {
                    return false
                }
            }


            // 3. 哈希校验
            return calculateSHA256(dexFile).equals(trustedHash)
        } catch (e: IOException) {
            return false
        }
    }
}