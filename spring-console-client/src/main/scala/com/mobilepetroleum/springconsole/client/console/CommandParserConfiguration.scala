package com.mobilepetroleum.springconsole.client.console

import javax.inject.Inject
import collection.JavaConverters._

class CommandParserConfiguration @Inject()(val invokeAction: InvokeAction) {

  def parse(args: Array[String]) = {

    val possibleArgs = args.lift

    possibleArgs(0) match {
      case Some("e") => invokeAction.run(possibleArgs(1).getOrElse("./execute.json"))

      case _ => throw new RuntimeException("Unexpected arguments")
    }

  }

}
