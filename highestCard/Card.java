import java.util.*;
public class Card
{
    Random num = new Random();
    String [] suit ={"Clubs","Diamonds","Spades","Hearts"};
    String [] rank ={"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
    String [][] cardArr ={{"Clubs","Diamonds","Spades","Hearts"},{"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"}};
    String [] Deck = new String[52];
    int [] ctRank = new int[13];
    int [] ctSuit = new int[4];
    int [] picked;
    public Card()
    {
        resetCt();
        setupDeck();
        shuffleDeck();
    }

    void setupDeck()
    {
        String tempSuit;
        String tempRank;
        String card;
        int ct =0;
        for (int x=0;x<=3;x++)
        {
            tempSuit=suit[x];
            for (int y=0;y<=12;y++)
            {
                tempRank=rank[y];
                card = tempRank + " of " + tempSuit;
                Deck[ct]=card;
                ct++;
            }
        }

    }

    void shuffleDeck()
    { 
        String temp1,temp2;
        int rand;
        for (int x=0;x<=51;x++)
        {
            rand=num.nextInt(52);   
            temp1=Deck[x];  
            temp2=Deck[rand];
            Deck[x]=temp2;
            Deck[rand]=temp1;

        }
    }

    String pickCard(int pos)//1-52
    {
        int tempPos=pos-1;
        int s=0;
        int r=0;
        String card=Deck[tempPos];
        String temp="";
        for(int x=0;x<4;x++)
        { 
            if(card.endsWith(suit[x]))
            {
                s=x;
                break;

            }
        }
        for(int x=0;x<=12;x++)
        {   //System.out.println(y+ " : " + rank[y]);
            if(card.startsWith(rank[x]))
            {
                r=x;
                break;

            }
        }
        ctRank[r]++;
        ctSuit[s]++;
        temp=Deck[tempPos];
        Deck[tempPos]="";
        return temp;

    }

    int getRand(int max)
    {
        return num.nextInt(max);
    }

    void resetCt()
    {
        Arrays.fill(ctRank,0);
        Arrays.fill(ctSuit,0);   
    }
    void resetHand()
    {
     Arrays.fill(picked,0);
    }

    String getRank()
    {  int temp=num.nextInt(12);
        ctRank[temp]++;
        return rank[temp];
    }

    String getSuit()
    {
        int temp=num.nextInt(4);
        ctSuit[temp]++;
        return suit[temp];
    }

    String nextCard(int curPos)
    {   String card="";
        if(Deck[curPos].equals(""))
        {
            nextCard((curPos+1)%52);
        }
        else 
            card = pickCard(curPos+1);
        return card;

    }
    void printCt()
    {  System.out.printf("%10s%n","--Deck Totals--");
        System.out.printf("%10s%n","---Rank---");
        for (int x=0;x<=12;x++)
        {
            if (ctRank[x]>0)
            {
                System.out.printf("%8s %d%n",rank[x],ctRank[x]);
            }
        }
        System.out.printf("%10s%n","---Suit---");
        for (int x=0;x<=3;x++)
        {
            if (ctSuit[x]>0)
            {
                System.out.printf("%8s %d%n",suit[x],ctSuit[x]);
            }
        }
    }

    void printDeck()
    {
        for (int x=0;x<=51;x++)
        {
            System.out.println(x + ":"+ Deck[x]);
        }
    }

    }
