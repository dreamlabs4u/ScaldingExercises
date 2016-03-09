import org.specs2._

import my.sample.Sample

class SampleSpec extends Specification {

  // Specification for the text and number method in Sample object
  def is = s2"""
    the method text should return 'Hello World' $e1
    the method text should not return 'World' $e2

    the method number should return 5 $e3
    the method number should not return 3 $e4
  """

  def e1 = Sample.text must beEqualTo("Hello World")        //positive test
  def e2 = Sample.text must beEqualTo("World").iff(false)   //negative test
  def e3 = Sample.number must be_==(5)                      //positive test
  def e4 = Sample.number must be_==(3).iff(false)           //negative test
}

