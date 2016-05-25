package com.training.scalding.getstarted

import com.twitter.scalding._

import org.specs2.mutable.Specification

class FlatMapSpec extends Specification {
  //print the result of scalding job to console
  JobTest("com.training.scalding.getstarted.FlatMap").
    arg("output", "outputFile").
    sink[String](TextLine("outputFile")){
      outbuffer => {
        "succeed with data 'All list'" in {outbuffer.toList must be_==( List("orange", "apple", "banana", "mango", "orange"))}
      }
    }.run.finish
}
