
class Deck:
    suit = ["Clubs","Diamonds","Spades","Hearts"]
    rank = ["Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"]
    rank_aces_high = ["Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King","Ace"]
    currentDeck=[]
    size=52

    def cmp_cards(self,card_1, card_2,aces_high=True):
        card_list1 = card_1.split()
        card_list2 = card_2.split()
        card1_rank_index= self.rank.index(card_list1[0])
        card2_rank_index= self.rank.index(card_list2[0])
        if card1_rank_index ==card2_rank_index:
            return 0
        if aces_high:
            if card1_rank_index == 0:
                card1_rank_index = 13
            if card2_rank_index == 0:
                card2_rank_index = 13
        if card2_rank_index > card1_rank_index:
            return 1
        else:
            return -1

    def card_list_sort(self,card_list,aces_high=True):
        if aces_high:
            sorted_list = sorted(card_list, key=lambda x: self.rank_aces_high.index(x.split()[0]))
        else:
            sorted_list=sorted(card_list,key = lambda x: self.rank.index(x.split()[0]))
        return sorted_list
    def get_highest(self,card_list,aces_high=True):
        sorted_list=self.card_list_sort(card_list,aces_high)
        highest_rank=""
        highest_cards=[]
        if len(sorted_list)==0:
            return "No Highest Card"
        elif len(sorted_list)==1:
            return sorted_list[0]
        elif len(sorted_list) >=2:
            if aces_high:
                highest_rank = max(sorted_list, key=lambda x: self.rank_aces_high.index(x.split()[0]))
            else:
                highest_rank=max(sorted_list, key = lambda x: self.rank.index(x.split()[0]))
        else:
            return "No Highest Card"
        highest_rank=highest_rank.split()[0]
        highest_cards=[card for card in sorted_list if highest_rank in card]
        return highest_cards


    def resetdeck(self):
        self.currentDeck = []
        self.size=52
        for x in list(self.suit):
            for y in list(self.rank):
                self.currentDeck.append(y+ " of " + x)
    def __init__(self):
        self.resetdeck()
    def get_card(self):
        return self.currentDeck.pop()
    def get_cards(self,number):
        card_list = []
        if number < 0:
            number=0
        if number > self.size:
            number = self.size
        print("getting "+str(number) +" cards")
        for i in range(number):
            card_list.append(self.get_card())
        self.size=self.size-number
        return card_list









