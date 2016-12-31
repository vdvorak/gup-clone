package samples

class App {

  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)

  def main(args : Array[String]) {
    println("concat arguments = " + foo(args))
  }

}
