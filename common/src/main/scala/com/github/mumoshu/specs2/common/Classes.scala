package com.github.mumoshu.specs2.common

trait Foo {
  val src: Int

  private val prv: Int = src + 1
  private val prv2: Int = prv + 1
  private def transform(n: Int) = transform2(n)
  private def transform2(n: Int) = n.toString

  val fooDef1 = transform(prv)
  val fooDef2 = transform(prv2)
  val fooVal1 = transform(prv)
  val fooVal2 = transform(prv2)
  lazy val fooLazyVal1 = transform(prv)
  lazy val fooLazyVal2 = transform(prv2)
}

trait Bar {
  val src: Int

  private val prv: Int = src + 1
  private val prv2: Int = prv + 1
  private def transform(n: Int) = transform2(n)
  private def transform2(n: Int) = n.toString

  val barDef1 = transform(prv)
  val barDef2 = transform2(prv2)
  val barVal1 = transform(prv)
  val barVal2 = transform2(prv2)
  lazy val barLazyVal1 = transform(prv)
  lazy val barLazyVal2 = transform(prv2)
}
