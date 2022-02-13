package com.github.tiagorodriguessimoes

import cats.effect.{IO, IOApp}
import fs2.{Stream, text}
import fs2.io.file.{Files, Path}

/**
 *
 * @param vertical depth
 * @param horizontal forward movement
 */
sealed case class Position(vertical: Int, horizontal: Int)
sealed case class FileContent(operation: String, value: Int)
object FileContent {
  def apply(s: String): FileContent = {
    val Array(v1,v2) = s.split("\\s+")
    FileContent(v1, v2.toInt)
  }
}

object App extends IOApp.Simple {

  val inputFile = "input.csv"

  val calculatePositionPart1: Stream[IO, Unit] = {
    var vertical = 0
    var horizontal = 0
    Files[IO].readAll(Path(s"src/main/resources/$inputFile"))
      .through(text.utf8.decode)
      .through(text.lines)
      .map(line =>
        FileContent(line) match {
          case FileContent("forward", value) => horizontal += value
          case FileContent("down", value) => vertical += value
          case FileContent("up", value) => vertical -= value
          case FileContent(_, _) => ()
        }
      )
      .evalTapChunk(x => IO(println(s"$vertical,$horizontal")))
  }

  val calculatePositionPart2: Stream[IO, Unit] = {
    var aim = 0
    var vertical = 0
    var horizontal = 0
    Files[IO].readAll(Path(s"src/main/resources/$inputFile"))
      .through(text.utf8.decode)
      .through(text.lines)
      .map(line =>
        FileContent(line) match {
          case FileContent("forward", value) =>
            horizontal += value
            vertical += aim * value
          case FileContent("down", value) => aim += value
          case FileContent("up", value) => aim -= value
          case FileContent(_, _) => ()
        }
      )
      .evalTapChunk(x => IO(println(s"$vertical,$horizontal. Total: ${vertical * horizontal}")))
  }

  def run: IO[Unit] =
    calculatePositionPart1.compile.drain
    calculatePositionPart2.compile.drain
}
