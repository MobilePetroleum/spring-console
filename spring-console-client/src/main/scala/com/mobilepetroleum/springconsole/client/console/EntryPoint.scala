package com.mobilepetroleum.springconsole.client.console

import org.slf4j.LoggerFactory

object EntryPoint {

  val logger = LoggerFactory.getLogger("console")

  def main(args: Array[String]) {
    try {
      new ConsoleApplication().start(args)
    } catch {
      case e: Exception => logger.error(s"${e.getMessage}", e)
    }
  }

}
