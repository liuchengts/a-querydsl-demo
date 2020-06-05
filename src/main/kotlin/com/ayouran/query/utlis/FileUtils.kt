package com.ayouran.query.utlis

import org.slf4j.LoggerFactory
import java.io.*


object FileUtils {
    private val logger = LoggerFactory.getLogger(javaClass)
    /**
     * 按行写文件（如果文件存在会先清除文件）
     *
     * @param path     文件路径
     * @param contents list的内容写入文件
     * @throws Exception
     */
    fun outFile(path: String, contents: List<String>) {
        val file = File(path)
        if (file.isFile) {
            var fileWriter: FileWriter? = null
            try {
                fileWriter = FileWriter(file)
                fileWriter.write("")
                fileWriter.flush()
            } catch (e: Exception) {
                logger.error("清空文件出错", e)
            } finally {
                fileWriter?.close()
            }
        } else {
            file.createNewFile()
        }
        var writer: BufferedWriter? = null
        try {
            writer = BufferedWriter(FileWriter(path))
            for (l in contents) {
                writer.write(l + "\r\n")
            }
        } catch (e: Exception) {
            logger.error("写入文件出错", e)
        } finally {
            writer?.close()
        }
    }

    /**
     * 按行写文件（如果文件存在会先清除文件）
     *
     * @param path     文件路径
     * @param content 内容写入文件
     * @throws Exception
     */
    fun outFile(path: String, content: String) {
        val file = File(path)
        if (file.isFile) {
            var fileWriter: FileWriter? = null
            try {
                fileWriter = FileWriter(file)
                fileWriter.write("")
                fileWriter.flush()
            } catch (e: Exception) {
                logger.error("清空文件出错", e)
            } finally {
                fileWriter?.close()
            }
        } else {
            file.createNewFile()
        }
        var writer: BufferedWriter? = null
        try {
            writer = BufferedWriter(FileWriter(path))
            writer.write(content)
        } catch (e: Exception) {
            logger.error("写入文件出错", e)
        } finally {
            writer?.close()
        }
    }

    /**
     * 按行读文件
     *
     * @param path     文件路径
     * @return 按行返回数据
     * @throws Exception
     */
    @Throws(Exception::class)
    fun putFile(path: String): MutableList<String> {
        val list = mutableListOf<String>()
        var inputStream: FileInputStream? = null
        var bufferedReader: BufferedReader? = null
        try {
            inputStream = FileInputStream(path)
            bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var str: String?
            while (bufferedReader.readLine().also { str = it } != null) {
                str?.let { list.add(it) }
            }
        } catch (e: Exception) {
            logger.error("读取文件出错", e)
        } finally {
            inputStream?.close()
            bufferedReader?.close()
        }
        return list
    }
}