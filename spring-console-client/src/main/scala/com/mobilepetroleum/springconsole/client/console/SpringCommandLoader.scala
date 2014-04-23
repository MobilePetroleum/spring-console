package com.mobilepetroleum.springconsole.client.console

import java.io.{FileReader, File}
import com.google.gson.Gson
import org.slf4j.LoggerFactory

object SpringCommandLoader {

  val logger = LoggerFactory.getLogger(classOf[InvokeAction])
  val gson = new Gson()

  def load[T](path: String, clazz: Class[T]): T = {
    val file = new File(path)

    if (!file.exists()) {
      throw new RuntimeException("File with command do not exist: " + file.getAbsolutePath )
    }

    gson.fromJson(new FileReader(path), clazz)
  }

}
