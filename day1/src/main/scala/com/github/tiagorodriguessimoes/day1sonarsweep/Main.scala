package com.github.tiagorodriguessimoes
package day1sonarsweep

import scala.io.Source
import scala.util.{Failure, Success, Try, Using}
import scala.io.Source

@main def Main(args: String*): Unit =

  def part1(tryStr: Option[String]): Option[Int] =
    val calculateDiffBetween = tryStr.map(a =>
      a.toSeq
        .sliding(2)
        .map { case Seq(x, y, _*) =>
          y.toInt - x.toInt
        }
        .toList
    )
    calculateDiffBetween.map(a => a.count(_ > 0))

  val tryStr: Try[String] =
    Using(Source.fromFile(s"src/main/scala/resources/input.txt"))(_.mkString)

  tryStr match
    case Failure(exception) => println(s"Error: ${exception.getMessage}")
    case Success(value) => {
      val part1Result = part1(Some(value))
      println(s"Part 1: ${part1Result.getOrElse("No result")}")
    }
