package com.mobilepetroleum.springconsole.client.console

import com.google.gson.JsonElement
import scala.beans.BeanProperty

class SpringCommand {
  @BeanProperty var remotes: Array[Remote] = new Array[Remote](0)
  @BeanProperty var invocations: Array[Invocation] = new Array[Invocation](0)
}

class Remote {
  @BeanProperty var name: String = null
  @BeanProperty var ip: String = null
  @BeanProperty var port: String = null
  @BeanProperty var rmiName: String = "spring-console"
}

class Invocation {
  @BeanProperty var beanName: String = null
  @BeanProperty var method: String = null
  @BeanProperty var params: Array[String] = new Array[String](0)
  @BeanProperty var values: JsonElement = null
}

