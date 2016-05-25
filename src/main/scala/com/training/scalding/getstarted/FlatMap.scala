package com.training.scalding.getstarted

import com.twitter.scalding._

class FlatMap(args: Args) extends Job(args) {
  val kidsList = List(
    ("john", "orange,apple"),
    ("liza", "banana,mango"),
    ("nina", "orange")
  )

  val pipe =
    IterableSource[(String, String)](kidsList, ('kid, 'fruits)).read
      .flatMapTo('fruits -> 'fruit) { text: String => text.split(",")}
      .debug
      .write(TextLine(args("output")))

}
