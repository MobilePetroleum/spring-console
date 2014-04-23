package com.mobilepetroleum.springconsole.client.console

import com.google.inject.Guice


class ConsoleApplication {

  def start(args: Array[String]) {
    val injector = Guice.createInjector(new ConsoleModule)
    injector.getInstance(classOf[Cli]).run(args)
  }

}
