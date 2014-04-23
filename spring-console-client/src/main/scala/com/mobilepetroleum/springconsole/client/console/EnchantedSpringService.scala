package com.mobilepetroleum.springconsole.client.console

import com.mobilepetroleum.{Invocable, MethodParameter, SpringService}
import com.google.gson.{Gson, JsonElement}
import org.slf4j.LoggerFactory

class EnchantedSpringService(val springService: SpringService) extends SpringService {

  val logger = LoggerFactory.getLogger("console")

  override def invoke(invocable: Invocable): String = {
    springService.invoke(invocable)
  }

  def invoke(beanName: String, methodName: String, parameters: Array[MethodParameter]): String = {
    invoke(new Invocable(beanName, methodName, parameters))
  }

  def invoke(invocation: Invocation): String = {
    invoke(invocation.beanName, invocation.method,
      createMethodParameter(invocation.params, invocation.values))
  }

  def createMethodParameter(params: Array[String], values: JsonElement): Array[MethodParameter] = {
    val ret = for (i <- 0 to params.length - 1)
      yield new MethodParameter(params(i), new Gson().toJson(values.getAsJsonArray.get(i)))

    ret.toArray
  }
}
