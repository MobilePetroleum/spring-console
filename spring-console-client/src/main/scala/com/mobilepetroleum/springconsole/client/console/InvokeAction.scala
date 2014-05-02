package com.mobilepetroleum.springconsole.client.console

import javax.inject.Inject
import org.slf4j.LoggerFactory
import com.google.gson.Gson

class InvokeAction extends ParameterAction {

  @Inject var springServiceFactory: SpringServiceFactory = _
  @Inject var gson: Gson = _

  val logger = LoggerFactory.getLogger("console")

  override def run(filePath: String): Unit = {
    val command = SpringCommandLoader.load(filePath, classOf[SpringCommand])

    for (remote <- command.remotes)
      execute(remote, command.invocations)
  }

  def execute(remote: Remote, invocations: Array[Invocation]): Unit = {

    val springService = try {
      springServiceFactory.create(remote.ip, remote.port, remote.rmiName)
    } catch {
      case e: Exception =>
        logger.error(s"Can not connect to ${remote.ip}:${remote.port}:${remote.rmiName}, ${e.getMessage}")
        return
    }

    for (invocation <- invocations) {
      try {
        logger.info(represent(remote, invocation))
        val result = springService.invoke(invocation)
        logger.info(s"[${remote.name}] Result: $result")
      } catch {
        case e: Exception => logger.error(s"[${remote.name}] ${e.getMessage}".replace("\n", " "), e)
      }
    }

  }

  def represent(remote: Remote, invocation: Invocation): String = {
    val params = for (i <- 0 until invocation.params.length)
    yield gson.toJson(invocation.values.getAsJsonArray.get(i))

    s"[${remote.name}] ${invocation.beanName}::${invocation.method}" + params.mkString("(", ", ", ")")
  }
}
