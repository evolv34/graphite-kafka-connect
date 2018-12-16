package com.evolv.metrics.utils

import java.io.{BufferedReader, InputStreamReader}
import java.net._
import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class MockServer(port: Int) {

  def init():Future[String] = Future {
    val server = new ServerSocket(port)
    val s = server.accept()

    val serverInputStream = new BufferedReader(new InputStreamReader(s.getInputStream))

    val data = serverInputStream.readLine()
    serverInputStream.close()
    s.close()
    server.close()
    data
  }
}

object MockServer {
  def apply(port: Int): MockServer = new MockServer(port)
}
