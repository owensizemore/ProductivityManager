import scala.io.Source
import scala.util.Random

object Runner {

  def main(args: Array[String]): Unit = {
    start()
  }

  def start(): Unit = {

    println("Welcome to Productivity Manager!")

    var stop = false

    while (!stop) {

      println("Please select an option:\n1. Test Challenges\n2. Simulate Unblocking\n3. Quit")
      val input = scala.io.StdIn.readLine()

      if (input == "1") {

        testChallenges()

      } else if (input == "2") {

        simulate()

      } else if (input == "3") {

        stop = true

      } else {

        println("Your input was not properly formatted. Try again.\n")

      }
    }

  }

  def testChallenges(): Unit = {
    var stop = false

    while (!stop) {

      println("\nWhich challenges would you like to test?\n1. Type a randomly-generated code\n2. Type sentences\n3. Type sentences in reverse\n4. Count how many times letters are found in a line\n5. Solve arithmetic\n6. Quit")
      val input = scala.io.StdIn.readLine()

      if (input == "1") {

        println("How many characters should the code have?")
        val numChars = scala.io.StdIn.readInt()
        typeCode(numChars)

      } else if (input == "2") {

        println("How many sentences should have to be typed?")
        val sentencesTyped = scala.io.StdIn.readInt()
        typeSentences(sentencesTyped, false)

      } else if (input == "3") {

        println("How many sentences should have to be typed?")
        val sentencesTyped = scala.io.StdIn.readInt()
        typeSentences(sentencesTyped, true)

      } else if (input == "4") {

        println("How many different letters should be found?")
        val numLetters = scala.io.StdIn.readInt()
        println("How many letters should be in the line you are searching?")
        val lineLength = scala.io.StdIn.readInt()
        findLetterInstances(numLetters, lineLength)

      } else if (input == "5") {

        println("How many arithmetic problems should be solved?")
        val problems = scala.io.StdIn.readInt()
        solveArithmetic(problems)

      } else if (input == "6") {

        stop = true

      } else {

        println("Your input was not properly formatted. Try again.\n")

      }
    }

  }

  def simulate(): Unit = {

    var correctInput = true

    do {

      println("Please select a difficulty level for the challenges.\nEasy\nMedium\nHard")
      val input = scala.io.StdIn.readLine()

      if (input.equalsIgnoreCase("Easy")) {

        for (_ <- 0 until 5) {
          completeChallenge(1)
        }
        println("Congratulations, you completed all of the challenges!")

      } else if (input.equalsIgnoreCase("Medium")) {

        for (_ <- 0 until 10) {
          completeChallenge(2)
        }
        println("Congratulations, you completed all of the challenges!")

      } else if (input.equalsIgnoreCase("Hard")) {

        for (_ <- 0 until 20) {
          completeChallenge(3)
        }
        println("Congratulations, you completed all of the challenges!")

      } else {
        println("Your input may have been misspelled. Please try again.\n")
        correctInput = false
      }

    } while (!correctInput)

  }

  def completeChallenge(level: Int): Unit = {

    val challengesArr = Array(1,2,3,4,5)

    val rand = new Random(System.nanoTime())

    val challenge = rand.nextInt(challengesArr.length)

    if (challenge == 1) {
      if (level == 1) {
        typeCode(16)
      } else if (level == 2) {
        typeCode(64)
      } else {
        typeCode(128)
      }
    } else if (challenge == 2) {
      if (level == 1) {
        typeSentences(3, false)
      } else if (level == 2) {
        typeSentences(6, false)
      } else {
        typeSentences(16, false)
      }
    } else if (challenge == 3) {
      if (level == 1) {
        typeSentences(2, true)
      } else if (level == 2) {
        typeSentences(4, true)
      } else {
        typeSentences(10, true)
      }
    } else if (challenge == 4) {
      if (level == 1) {
        findLetterInstances(3, 25)
      } else if (level == 2) {
        findLetterInstances(6, 100)
      } else {
        findLetterInstances(10, 150)
      }
    } else {
      if (level == 1) {
        solveArithmetic(5)
      } else if (level == 2) {
        solveArithmetic(10)
      } else {
        solveArithmetic(25)
      }
    }
  }

  def typeCode(len: Int): Unit = {

    println("BEGIN CHALLENGE: Type Code")

    val rand = new Random(System.nanoTime())
    val codes = Array("01234567890", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "!@#$%^&*()[]{}-=+_", "abcdefghijklmnopqrstuvwxyz")

    var completed = false

    while (!completed) {

      val sb = new StringBuilder(len)
      var category = ""

      for (_ <- 0 until len) {
        category = codes(rand.nextInt(codes.length))
        sb.append(category(rand.nextInt(category.length)))
      }

      println("Type out the following code exactly.\n" + sb.toString())
      val input = scala.io.StdIn.readLine()

      if (input.equals(sb.toString())) {
        completed = true
        println("Correct!")
      } else {
        println("Sorry, it looks like you got something wrong. You'll have to try again.\n")
      }
    }
    println("CHALLENGE COMPLETE: Code matches!\n")

  }

  def typeSentences(numSentences: Int, reversed: Boolean): Unit = {

    println("BEGIN CHALLENGE: Type Sentences")

    val rand = new Random(System.nanoTime())
    val text = Source.fromFile("loremipsum.txt").mkString.split("[.]").map(_.trim)

    var completed = false

    while (!completed) {

      val sb = new StringBuilder

      //Generate list of sentences with periods
      for (_ <- 0 until numSentences) {
        sb.append(text(rand.nextInt(text.length)))
        sb.append(". ")
      }

      if (reversed) {

        println("Type out the following sentence(s) in reverse order.\nExample: Hello World! -> !dlroW olleH\n\n" + sb.toString())
        val inputR = scala.io.StdIn.readLine()

        if (inputR.equals(sb.toString().substring(0, sb.toString().length - 1).reverse)) {

          println("Nice job! Your input is identical to the code.")
          completed = true
        } else {
          println("Sorry, it looks like you got something wrong. You'll have to try again.\n")
        }

      } else {

        println("Type out the following sentence(s) exactly.\n" + sb.toString())
        val input = scala.io.StdIn.readLine()

        if (input.equals(sb.toString().substring(0, sb.toString().length - 1))) {
          println("Nice job! Your input is identical to the code.")
          completed = true
        } else {
          println("Sorry, it looks like you got something wrong. You'll have to try again.\n")
        }
      }

    }
    println("CHALLENGE COMPLETE: All sentences typed!\n")

  }

  def findLetterInstances(numKeys: Int, len: Int): Unit = {

    println("BEGIN CHALLENGE: Find the Letters")

    val rand = new Random(System.nanoTime())
    val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    //Get String to find keys from
    val sb = new StringBuilder(len)
    for (_ <- 0 until len) {
      sb.append(letters(rand.nextInt(letters.length)))
    }

    println(sb.toString())

    //Get keys and respond with message
    for (_ <- 0 until numKeys) {

      var correct = false

      while (!correct) {
        //Create key
        val key = letters(rand.nextInt(letters.length))

        //Check how many times the key is found
        val count = sb.count(_ == key)

        //Prompt user
        println("How many times is the letter " + key + " found in this line?")

        var correctInput = true
        var timesFound = 0

        do {
          try {
            timesFound = scala.io.StdIn.readInt()
            correctInput = true
          } catch {
            case e: NumberFormatException => println("Your input was not a number. Try again.")
              correctInput = false
          }
        } while (!correctInput)

        if (timesFound == count) {
          println("Correct!")
          correct = true
        } else {
          println("Sorry, that's not correct. The answer we were looking for was " + count + ". Let's try annother one:")
        }
      }

    }
    println("CHALLENGE COMPLETE: All letters found!\n")

  }

  def solveArithmetic(numProblems: Int): Unit = {

    println("BEGIN CHALLENGE: Arithmetic Problems")

    val rand = new Random(System.nanoTime())

    val operations = "+-*"

    for (_ <- 0 until numProblems) {
      var correct = false

      while (!correct) {
        val term1 = rand.nextInt(1000000) + 100000
        val term2 = rand.nextInt(1000000) + 100000
        val operation = operations(rand.nextInt(operations.length))

        //calculate sum/difference/product
        val finalVal = operation match {
          case '+' => term1+term2
          case '-' => term1-term2
          case '*' => term1*term2
        }

        println("Calculate this equation:\n" + term1 + " " + operation + " " + term2 + " = ?")

        var correctInput = true
        var input = 0

        do {
          try {
            input = scala.io.StdIn.readInt()
            correctInput = true
          } catch {
            case e: NumberFormatException => println("Your input was not a number. Try again.")
              correctInput = false
          }
        } while (!correctInput)

        if (input == finalVal) {
          println("Correct!")
          correct = true
        } else {
          println("Sorry, that's not correct. The answer we were looking for was " + finalVal + ". Let's try another problem:")
        }
      }
    }
    println("CHALLENGE COMPLETE: All equations solved!\n")
  }
}
