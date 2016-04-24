package my.sample.scalding

import com.twitter.scalding._

class PersonalDetails(args: Args) extends Job(args) {
  val data = TextLine(args("input")).read.mapTo('line -> ('firstname, 'lastname, 'age)){
    text: String => parseText(text)
  }.write(Tsv(args("output")))

  def parseText(str: String) = {
    ( str.split(" ").toList.get(0),
      str.split(" ").toList.get(1),
      str.split(" ").toList.get(2) )
  }
}
