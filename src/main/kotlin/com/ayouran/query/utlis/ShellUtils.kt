package com.ayouran.query.utlis

import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object ShellUtils {
    private val logger = LoggerFactory.getLogger(javaClass)
    /**
     * 执行shell并且打印执行结果
     *
     * @param path 要执行的脚本路径
     * @throws IOException
     */
    @Throws(IOException::class)
    fun exec(path: String?) {
        val process = Runtime.getRuntime().exec(path)
        val input = BufferedReader(InputStreamReader(process.inputStream))
        var line: String? = ""
        while (input.readLine().also { line = it } != null) {
            logger.info(line)
        }
        input.close()
    }
}