package com.alvin

import cn.hutool.core.util.ZipUtil
import com.github.junrar.Junrar
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry
import org.apache.commons.compress.archivers.sevenz.SevenZFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths


fun File.decompress() {
    if (this.isDirectory) {
        return
    }
    when {
        this.name.endsWith(".rar") -> decompressRAR(this)
        this.name.endsWith(".zip") -> decompressZip(this)
        this.name.endsWith(".7z") -> decompress7z(this)
    }

}


/**
 * 解压缩RAR文件 不递归
 * @param file 压缩包文件
 * @param targetDir 目标文件夹
 * @param delete 解压后是否删除原压缩包文件
 */
private fun decompressRAR(file: File, targetDir: File = file.parentFile, delete: Boolean = true) {
    println("decompressRAR 文件名：" + file.name)
    Junrar.extract(file, targetDir)
    deleteFile(file, delete)
}

/**
 * 解压缩zip文件 不递归
 * @param file 压缩包文件
 * @param targetDir 目标文件夹
 * @param delete 解压后是否删除原压缩包文件
 */
private fun decompressZip(file: File, targetDir: File = file.parentFile, delete: Boolean = true) {
    println("decompressZip 文件名：" + file.name)
    ZipUtil.unzip(file, targetDir)
    deleteFile(file, delete)
}

fun deleteFile(file: File, delete: Boolean) {
    if (delete) {
        file.delete()
    }
}

/**
 * 解压缩RAR文件 不递归
 * @param file 压缩包文件
 * @param targetDir 目标文件夹
 * @param delete 解压后是否删除原压缩包文件
 */
fun decompress7z(file: File, targetDir: File = file.parentFile, delete: Boolean = true) {
    println("decompress7z 文件名：" + file.name)
    try {
        SevenZFile(file).use { sevenZFile ->
            var entry: SevenZArchiveEntry? = null
            while (sevenZFile.nextEntry.also { entry = it } != null) {
                val newFilePath = Paths.get(targetDir.absolutePath + File.separator + entry!!.name)
                // 处理目录
                if (entry!!.isDirectory) {
                    Files.createDirectories(newFilePath)
                }
                FileOutputStream(newFilePath.toFile()).use { outputStream ->
                    var length = 0
                    val buffer = ByteArray(2048)
                    while (sevenZFile.read(buffer).also { length = it } != -1) {
                        outputStream.write(buffer, 0, length)
                    }
                    outputStream.flush()
                }
            }
        }
        deleteFile(file, delete)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
