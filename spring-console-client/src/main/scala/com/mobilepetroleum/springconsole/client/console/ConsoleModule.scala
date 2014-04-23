package com.mobilepetroleum.springconsole.client.console

import com.google.inject.AbstractModule

class ConsoleModule extends AbstractModule {

  override protected def configure() {
    bind(classOf[SpringServiceFactory]).to(classOf[SimpleSpringServiceFactory])
  }

}