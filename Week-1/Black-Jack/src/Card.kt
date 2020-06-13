class Card(val pip: String, private val suit: Char, var changable: Boolean) {

    fun printCardInfo() {
        println("Pip: $pip Suit: $suit")
    }

    //Gets colored logo for suit
    private fun getSuitLogo(): Any {
        val suitLogo = when (suit) {
            'H' -> {
                StringBuilder("${27.toChar()}[31m\u2665${27.toChar()}[0m")
            }
            'D' -> {
                StringBuilder("${27.toChar()}[31m\u2666${27.toChar()}[0m")
            }
            'S' -> {
                StringBuilder("${27.toChar()}[0m\u2660${27.toChar()}[0m")
            }
            else -> {
                StringBuilder("${27.toChar()}[0m\u2663${27.toChar()}[0m")
            }

        }
        return suitLogo
    }

    //Fixed the bug when printing 10
    private fun printPip(card: Card, top: Boolean): String {
        val tempString: String
        if (top) {
            tempString = when (card.pip) {
                "10" -> "${card.pip}            |"
                else -> "${card.pip}             |"
            }
        } else {
            tempString = when (card.pip) {
                "10" -> "            ${card.pip}|"
                else -> "             ${card.pip}|"
            }
        }
        return tempString
    }

    //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/
    //Doc on StringBuilder()
    fun printCard(hand: Set<Card>, dealer: Boolean): Any {
        val cardPip: Any

        //Bug when the number is a 10
        //Wonder if they are actually going to read my code..... Hi!
        /*
            So after some research and looking at math. The max number of cards that you can hold w/out busting would be 11...
            But this is like extremely rare, and probably will never happen. However, I did see that most casino's put in a rule
            That says you can't have more than five cards.... I'm thinking that I will should just do ASCII art for up to five cards
            Because Idk how I feel about doing art for 11 cards... that is rough. So I'm going to implement the 5 card rule.
            https://www.reddit.com/r/theydidthemath/comments/6zvjx8/request_blackjack_the_maximum_number_of_cards_a/
            Thx reddit XD
            Below was the first print before changing it.
            val cardOutline = """
            .--------------.
            |$pip             |
            |              |
            |              |
            |      $suitLogo       |
            |              |
            |              |
            |             $pip|
            '--------------'

        """.trimIndent()
        */
        //This is so ugly.... I'm so sorry... idk how else to do it.
        if (dealer) {
            return when (hand.size) {
                1 -> {
                    """
                .--------------.
                |--------------|
                |--------------|
                |===  Back  ===|
                |===   of   ===|
                |===  Card  ===|
                |--------------|
                |--------------|
                '--------------'
       
                    """.trimIndent()
                }
                2 -> {
                    """
                .--------------.  .--------------.
                |--------------|  |${printPip(hand.elementAt(1), true)}
                |--------------|  |              |
                |===  Back  ===|  |              |
                |===   of   ===|  |      ${hand.elementAt(1).getSuitLogo()}       |
                |===  Card  ===|  |              |
                |--------------|  |              |
                |--------------|  |${printPip(hand.elementAt(1), false)}
                '--------------'  '--------------'
          
                    """.trimIndent()
                }
                3 -> {
                    """
                .--------------.  .--------------.  .--------------.
                |--------------|  |${printPip(hand.elementAt(1), true)}  |${printPip(hand.elementAt(2), true)}
                |--------------|  |              |  |              |
                |===  Back  ===|  |              |  |              |
                |===   of   ===|  |      ${hand.elementAt(1).getSuitLogo()}       |  |      ${hand.elementAt(2).getSuitLogo()}       |
                |===  Card  ===|  |              |  |              |
                |--------------|  |              |  |              |
                |--------------|  |${printPip(hand.elementAt(1), false)}  |${printPip(hand.elementAt(2), false)}
                '--------------'  '--------------'  '--------------'
          
                    """.trimIndent()
                }
                4 -> {
                    """
                .--------------.  .--------------.  .--------------.  .--------------.
                |--------------|  |${printPip(hand.elementAt(1), true)}  |${printPip(hand.elementAt(2), true)}  |${printPip(hand.elementAt(3), true)}
                |--------------|  |              |  |              |  |              |
                |===  Back  ===|  |              |  |              |  |              |
                |===   of   ===|  |      ${hand.elementAt(1).getSuitLogo()}       |  |      ${hand.elementAt(2).getSuitLogo()}       |  |      ${hand.elementAt(3).getSuitLogo()}       |
                |===  Card  ===|  |              |  |              |  |              |
                |--------------|  |              |  |              |  |              |
                |--------------|  |${printPip(hand.elementAt(1), false)}  |${printPip(hand.elementAt(2), false)}  |${printPip(hand.elementAt(3), false)}
                '--------------'  '--------------'  '--------------'  '--------------'
          
                    """.trimIndent()
                }
                5 -> {
                    """
                .--------------.  .--------------.  .--------------.  .--------------.  .--------------.
                |--------------|  |${printPip(hand.elementAt(1), true)}  |${printPip(hand.elementAt(2), true)}  |${printPip(hand.elementAt(3), true)}  |${printPip(hand.elementAt(4), true)}
                |--------------|  |              |  |              |  |              |  |              |
                |===  Back  ===|  |              |  |              |  |              |  |              |
                |===   of   ===|  |      ${hand.elementAt(1).getSuitLogo()}       |  |      ${hand.elementAt(2).getSuitLogo()}       |  |      ${hand.elementAt(3).getSuitLogo()}       |  |      ${hand.elementAt(4).getSuitLogo()}       |
                |===  Card  ===|  |              |  |              |  |              |  |              |
                |--------------|  |              |  |              |  |              |  |              |
                |--------------|  |${printPip(hand.elementAt(1), false)}  |${printPip(hand.elementAt(2), false)}  |${printPip(hand.elementAt(3), false)}  |${printPip(hand.elementAt(5), false)}
                '--------------'  '--------------'  '--------------'  '--------------'  '--------------'
            
                    """.trimIndent()
                }
                else -> ""
            }
        } else {
            return when (hand.size) {
                1 -> {
                    """
                .--------------.
                |${printPip(hand.elementAt(0), true)}
                |              |
                |              |
                |      ${getSuitLogo()}       |
                |              |
                |              |
                |${printPip(hand.elementAt(0), false)}
                '--------------'
    
            """.trimIndent()
                }
                2 -> {
                    """
                .--------------.  .--------------.
                |${printPip(hand.elementAt(0), true)}  |${printPip(hand.elementAt(1), true)}
                |              |  |              |
                |              |  |              |
                |      ${hand.elementAt(0).getSuitLogo()}       |  |      ${hand.elementAt(1).getSuitLogo()}       |
                |              |  |              |
                |              |  |              |
                |${printPip(hand.elementAt(0), false)}  |${printPip(hand.elementAt(1), false)}
                '--------------'  '--------------'
                
                """.trimIndent()
                }
                3 -> {
                    """
                .--------------.  .--------------.  .--------------.
                |${printPip(hand.elementAt(0), true)}  |${printPip(hand.elementAt(1), true)}  |${printPip(hand.elementAt(2), true)}
                |              |  |              |  |              |
                |              |  |              |  |              |
                |      ${hand.elementAt(0).getSuitLogo()}       |  |      ${hand.elementAt(1).getSuitLogo()}       |  |      ${hand.elementAt(2).getSuitLogo()}       |
                |              |  |              |  |              |
                |              |  |              |  |              |
                |${printPip(hand.elementAt(0), false)}  |${printPip(hand.elementAt(1), false)}  |${printPip(hand.elementAt(2), false)}
                '--------------'  '--------------'  '--------------'
                
                """.trimIndent()
                }
                4 -> {
                    """
                .--------------.  .--------------.  .--------------.  .--------------.
                |${printPip(hand.elementAt(0), true)}  |${printPip(hand.elementAt(1), true)}  |${printPip(hand.elementAt(2), true)}  |${printPip(hand.elementAt(3), true)}
                |              |  |              |  |              |  |              |
                |              |  |              |  |              |  |              |
                |      ${hand.elementAt(0).getSuitLogo()}       |  |      ${hand.elementAt(1).getSuitLogo()}       |  |      ${hand.elementAt(2).getSuitLogo()}       |  |      ${hand.elementAt(3).getSuitLogo()}       |
                |              |  |              |  |              |  |              |
                |              |  |              |  |              |  |              |
                |${printPip(hand.elementAt(0), false)}  |${printPip(hand.elementAt(1), false)}  |${printPip(hand.elementAt(2), false)}  |${printPip(hand.elementAt(3), false)}
                '--------------'  '--------------'  '--------------'  '--------------'
                
                """.trimIndent()
                }
                5 -> {
                    """
                .--------------.  .--------------.  .--------------.  .--------------.  .--------------.
                |${printPip(hand.elementAt(0), true)}  |${printPip(hand.elementAt(1), true)}  |${printPip(hand.elementAt(2), true)}  |${printPip(hand.elementAt(3), true)}  |${printPip(hand.elementAt(4), true)}
                |              |  |              |  |              |  |              |  |              |
                |              |  |              |  |              |  |              |  |              |
                |      ${hand.elementAt(0).getSuitLogo()}       |  |      ${hand.elementAt(1).getSuitLogo()}       |  |      ${hand.elementAt(2).getSuitLogo()}       |  |      ${hand.elementAt(3).getSuitLogo()}       |  |      ${hand.elementAt(4).getSuitLogo()}     |
                |              |  |              |  |              |  |              |  |              |
                |              |  |              |  |              |  |              |  |              |
                |${printPip(hand.elementAt(0), false)}  |${printPip(hand.elementAt(1), false)}  |${printPip(hand.elementAt(2), false)}  |${printPip(hand.elementAt(3), false)}  |${printPip(hand.elementAt(4), false)}
                '--------------'  '--------------'  '--------------'  '--------------'  '--------------'
                
                """.trimIndent()
                }
                else -> ""

            }
        }
    }

}