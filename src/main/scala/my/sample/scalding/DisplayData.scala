package my.sample.scalding

import com.twitter.scalding._

class DisplayData(args : Args) extends Job(args) with TupleConversions {
  val input  = TextLine(args("input"))   // input argument
  val output = TextLine(args("output"))  // output argument
  
  //writing the contents of input file to output file
  input
    .read
    .project('line)
    .write(output)
}
