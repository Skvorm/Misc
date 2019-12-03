from Deck import Deck
from random import shuffle


class Game:
    used_cards = []
    d = Deck()
    shuffle(d.currentDeck)

    toggle = False
    defined =False
    while not toggle:
        inp = input("how many cards? \n")
        if inp == "quit":
            toggle=True
            break
        try:
            card_num=int(inp)
            defined=True
        except ValueError:
            print("Please enter a valid number")
            defined=False
        if defined:
            ret_cards=d.card_list_sort(d.get_cards(card_num))
            print(ret_cards)
            print(d.get_highest(ret_cards))
        ##print(d.card_list_sort(ret_cards))
        if d.size > 0:
            print(d.size)
        else:
            d.resetdeck()
            shuffle(d.currentDeck)
            print("Deck Reset and Shuffled")
