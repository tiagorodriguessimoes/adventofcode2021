package com.github.tiagorodriguessimoes
package day1sonarsweep

import scala.io.Source

@main def Main(args: String*): Unit =

  val tryStr =
    scala.io.Source
      .fromFile(s"src/main/scala/resources/input.txt")
      .getLines()
      .map(_.toInt)
      .toSeq

  println(part1(tryStr))
  println(part2(tryStr))

def calculateDiffBetweenTandNext(l: Seq[Int]): List[Int] =
  l.sliding(2)
    .map { case Seq(x, y, _*) =>
      y - x
    }
    .toList

def part1(sq: Seq[Int]): Int =
  calculateDiffBetweenTandNext(sq).count(_ > 0)

def part2(sq: Seq[Int]): Int =
  val grouping3: Seq[Int] = sq
    .sliding(3)
    .map { case Seq(x, y, z, _*) =>
      z + y + x
    }
    .toSeq

  calculateDiffBetweenTandNext(grouping3).count(_ > 0)
