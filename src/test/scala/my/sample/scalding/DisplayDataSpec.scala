package my.sample.scalding

import scala.io.Source

import com.twitter.scalding._

import org.specs2.mutable.Specification

import my.sample.scalding._

class DisplayDataSpec extends Specification with TupleConversions {
  val input1 = getSource("src/test/resources/input1") // read data form input1 file
  val input2 = getSource("src/test/resources/input2") // read data form input2 file

  //print the result of scalding job to console
  JobTest("my.sample.scalding.DisplayData").
    arg("input", "inputFile").
    arg("output", "outputFile").
    source(TextLine("inputFile"), input1).
    sink[String](TextLine("outputFile")){ 
      outbuffer => outbuffer.foreach(println) 
    }.run.finish
    
  //test the result of scalding job
  "Display Data" should {
    JobTest("my.sample.scalding.DisplayData").
      arg("input", "inputFile").
      arg("output", "out").
      source(TextLine("inputFile"), input2).
      sink[String](TextLine("out")){ outbuffer => {
        "succeed with data 'Hello World'" in {outbuffer(0) must be_==("Hello World")} // positive test
        "fail with data 'Hello'" in {outbuffer(0) must be_==("Hello").iff(false)}}    // negative test
      }.run.finish
    }

  // method to source the file content
  def getSource(file: String) = {
    Source.fromFile(file).getLines()
      .toList.map(line => (line.hashCode, line))
  }
}

