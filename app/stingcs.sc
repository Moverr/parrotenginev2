def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString

randomString(10)