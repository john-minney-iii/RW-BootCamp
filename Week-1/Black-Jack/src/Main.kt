//We must add a dealer hand. God speed gents

fun main() {
    var continueGame = true
    gameLoop@while (continueGame) {
        var hasChanged = false
        //var cash: Int = 100 add this after the dealer hand
        val deck: MutableSet<Card> = initDeck()
        var hand: Set<Card> = dealCards(deck, null, 2)
        var dealerHand: Set<Card> = dealCards(deck,null,2)
        var dealerHandTotal = evalCards(dealerHand)
        if (evalCards(hand) == 21 || evalCards(dealerHand) == 21) {
            printGame(hand, dealerHand, evalCards(hand), dealerHandTotal, hasChanged,true)
        }else {
            printGame(hand, dealerHand, evalCards(hand), dealerHandTotal, hasChanged,false)
        }
        hitLoop@ while (evalCards(hand) < 21 && evalCards(dealerHand) < 21) { //Loop to keep playing
            if (hand.size == 5) {
                println("5 Card Charlie Rule: You Can't Take Anymore Cards. You got a hand of ${evalCards(hand)}")
                break@hitLoop
            }
            print("Hit/Stay? (H/S): ")
            when (readLine()?.toLowerCase()) {
                "h" -> {
                    hand = dealCards(deck, hand, 1)
                    checkForAce@for (card in hand) {
                        if ((card.pip == "A") && card.changable && (evalCards(hand) > 21)) {
                            hand = changeAce(hand)
                            hasChanged = true
                        }
                    }
                    if (dealerHandTotal <= 16) {
                        dealerHand = dealCards(deck,dealerHand,1)
                        checkForAce@for (card in dealerHand) {
                            if ((card.pip == "A") && card.changable && (evalCards(dealerHand) > 21)) {
                                dealerHand = changeAce(dealerHand)
                                dealerHandTotal = evalCards(dealerHand)
                            }
                        }
                        dealerHandTotal = evalCards(dealerHand)
                    }
                    if (dealerHandTotal == 21) {
                        printGame(hand, dealerHand, evalCards(hand), dealerHandTotal, hasChanged, true)
                        break@hitLoop
                    }
                    else if (evalCards(hand) > 21) {
                        printGame(hand, dealerHand, evalCards(hand), dealerHandTotal, hasChanged, true)
                        break@hitLoop
                    }else {
                        printGame(hand, dealerHand, evalCards(hand), dealerHandTotal, hasChanged, false)
                    }
                }
                "s" -> {
                    while (dealerHandTotal <= 16) {
                        dealerHand = dealCards(deck,dealerHand,1)
                        checkForAce@for (card in dealerHand) {
                            if ((card.pip == "A") && card.changable && (evalCards(dealerHand) > 21)) {
                                dealerHand = changeAce(dealerHand)
                                dealerHandTotal = evalCards(dealerHand)
                            }
                        }
                        dealerHandTotal = evalCards(dealerHand)
                    }
                    printGame(hand, dealerHand, evalCards(hand), dealerHandTotal, hasChanged, true)
                    break@hitLoop
                }
            }
        }
        print("Would you like to play again? (Y/N): ")
        when (readLine()?.toLowerCase()) {
            "n" -> {
                println("Thanks for Playing!")
                continueGame = false
            }
            "y" -> {
                continue@gameLoop
            }
        }
    }
}
//Initializes Deck
fun initDeck(): MutableSet<Card> {
    val tempDeck = mutableSetOf<Card>()
    val suits = arrayOf('H', 'D', 'S', 'C')
    for (suit in suits) {
        for (pip in 1..13) {
            when (pip) { //To get the A,J,Q,K cards... might change
                1 -> tempDeck.add(Card("A", suit, true))
                11 -> tempDeck.add(Card("J", suit, false))
                12 -> tempDeck.add(Card("Q", suit, false))
                13 -> tempDeck.add(Card("K", suit, false))
                else -> tempDeck.add(Card(pip.toString(), suit, false))
            }
        }
    }
    return tempDeck
}
//Deals Hand to Player
fun dealCards(deck: MutableSet<Card>,hand: Set<Card>?, n: Int): MutableSet<Card> {
    val tempHand = mutableSetOf<Card>()
    if (hand != null) { //Checks to see if the player's hand is empty. If not it will add to it
        for (card in hand) tempHand.add(card)
    }
    for (i in 1..n) {
        val tempCard = deck.random()
        tempHand.add(tempCard)
        deck.remove(tempCard) //Replace this later
    }
    return tempHand
    //if you find this, fun fact.... it is 11:00pm and I've drank 3 coffees. I don't need sleep when I have code
}
//Totals Cards
fun evalCards(hand: Set<Card>): Int {
    var handTotal = 0
    for (card in hand) {
        handTotal += when (card.pip) {
            "A" ->  {
                if (card.changable) {
                    11
                }else {
                    1
                }
            }
            "J","Q","K" -> 10
            else -> card.pip.toInt()
        }
    }
    return handTotal
}
//Uses the method in the Card class to print the cards
fun printGame(hand: Set<Card>,dealerHand: Set<Card>, total: Int, dealerTotal: Int, change: Boolean, gameEnd: Boolean) {
    for (i in 0..20) { //Attempt to make it more 'readable'
        println("")
    }
    println("Aces will change automatically to prevent a loss!")
    println("--------------------------------------------------------------------")
    println("Dealer Hand: ")
    if (gameEnd) {
        print(dealerHand.elementAt(0).printCard(dealerHand, false))
    }else {
        print(dealerHand.elementAt(0).printCard(dealerHand, true))
    }
    println("Your Hand: ")
    print(hand.elementAt(0).printCard(hand,false))
    //Nested when loops..... what! There is a first time for everything lol. This extra credit is good practice.
    println(when (dealerTotal) {
        21 -> {
            if (total != 21) "--------------------------------------------------------------------\nDealer Won with 21!"
            else if (total == 21) "--------------------------------------------------------------------\nDraw!"
            else ""
        }
        in 0..21 -> {
            when (total) {
                21 -> "--------------------------------------------------------------------\nWinner Winner Chicken Dinner!"
                in 0..21 -> {
                    if (gameEnd) {
                        if (dealerTotal > total) {
                            "--------------------------------------------------------------------\nDealer won with a hand of $dealerTotal. You had a hand of $total."
                        } else if (total > dealerTotal) {
                            "--------------------------------------------------------------------\nYou won with a hand of $total. Dealer had a hand of $dealerTotal"
                        }else if (total == dealerTotal) {
                            "--------------------------------------------------------------------\nDraw!"
                        }else {
                                "--------------------------------------------------------------------\nGame Error. Please Try Again."
                        }
                    }else {
                        "--------------------------------------------------------------------\nTotal of: $total"
                    }
                }
                else -> "--------------------------------------------------------------------\nBad Luck! Your Total Was $total. Dealer won with $dealerTotal"
            }
        }
        else -> "--------------------------------------------------------------------\nDealer Broke! You win!"
    })
}


fun changeAce(hand: Set<Card>): Set<Card> {
    for (card in hand) {
        if ((card.pip == "A") && card.changable) {
            card.changable = false

        }
    }
    return hand
}

