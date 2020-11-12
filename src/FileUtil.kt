package com.alvin

import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.ZipUtil
import org.apache.commons.io.FileUtils
import java.io.File
import java.util.*

//var url = "/Users/alvin/Pictures/作品集/B站"
var url = "/Users/alvin/Downloads"
val parentFile = File(url)
fun main() {
    if (Objects.isNull(parentFile)) {
        return
    }
    decompress(parentFile)
    copy(parentFile)
}

fun decompress(file: File) {
    if (file.isDirectory) {
        val listFiles = file.listFiles();
        listFiles?.let {
            for (item in listFiles) {
                try {
                    item.decompress()
                } catch (ex: Exception) {
                    println(ex)
                }
            }
        }
    }
}

/**
 * 把各自第一层文件夹中所有文件夹中的内容复制到第一层
 */
var dest: File = parentFile
fun copy(file: File) {
    if (file.isDirectory) {
        val listFiles = file.listFiles();
        listFiles?.let {
            for (item in listFiles) {
                if (item.isDirectory) {
                    val parentFile1 = item.parentFile
                    if (parentFile.absolutePath == parentFile1.absolutePath) {
                        dest = item
                    }
                    copy(item)
                } else {
                    FileUtil.copy(item, dest, false)
                }
                item.delete()
            }
        }
    }
}
