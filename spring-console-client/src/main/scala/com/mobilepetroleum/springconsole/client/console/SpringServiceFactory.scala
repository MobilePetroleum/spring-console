package com.mobilepetroleum.springconsole.client.console

import com.mobilepetroleum.SpringService

trait SpringServiceFactory {
  def create(host: String, port: String, rmiName: String = "spring-console"): EnchantedSpringService
}
