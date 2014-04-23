package com.mobilepetroleum.springconsole.client.console

import org.junit.Test
import org.mockito.Mockito._

class CommandParserConfigurationTest {

  @Test
  def shouldInvokeAction() {
    // given
    val invokeAction = mock(classOf[InvokeAction])
    val command = new CommandParserConfiguration(invokeAction)

    // when
    command.parse(Array[String]("e"))

    // then
    verify(invokeAction).run("./execute.json")
  }

}
