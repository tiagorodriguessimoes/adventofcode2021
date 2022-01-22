package com.github.tiagorodriguessimoes
package day1sonarsweep

import scala.io.Source

@main def Main(args: String*): Unit =
  val file =
    scala.io.Source.fromFile(s"src/main/scala/resources/input.txt").getLines()
  val calculateDiffBetween = file.toSeq
    .sliding(2)
    .map { case Seq(x, y, _*) =>
      y.toInt - x.toInt
    }
    .toList
  val result = calculateDiffBetween.count(_ > 0)
  print(result)
