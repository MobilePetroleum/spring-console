package com.mobilepetroleum.springconsole.client.console

import com.mobilepetroleum.SpringService
import java.rmi.registry.{LocateRegistry, Registry}

class SimpleSpringServiceFactory extends SpringServiceFactory {

  override def create(host: String, port: String, rmiName: String): EnchantedSpringService =
    enchant(getRegistry(host, port).lookup(rmiName).asInstanceOf[SpringService])

  def enchant(springService: SpringService) : EnchantedSpringService =
    new EnchantedSpringService(springService)

  def getRegistry(host: String, port: String): Registry =
    LocateRegistry.getRegistry(host, Integer.parseInt(port))

}
