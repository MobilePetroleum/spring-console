package com.mobilepetroleum.springconsole.client.console

import javax.inject.Inject


class Cli @Inject()(val command: CommandParserConfiguration) {

  def run(args: Array[String]) {
    command.parse(args)
  }

}
