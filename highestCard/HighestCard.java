import java.util.Random;
import java.util.Scanner;

public class HighestCard
{
public static void main(String []args)
{
       Random r = new Random();
       int players=0,currentPlayer=1;
       int turn=1;
        Scanner kb = new Scanner(System.in);
        boolean toggle=false;
        Card c = new Card();
        int num=0;
        int cc=0;//cards selected
        String tempCard="card";
        int pos=0;
       // System.out.println("Number of Players");
       // players=kb.nextInt();  
        while(!toggle)
        {
            System.out.println("How many cards");
            System.out.printf("%2s Cards remaining in current deck %n",(52-cc));
            num=kb.nextInt();
            if(num>52)
                num=52;
            if((num+cc)>52)
            {
                num=52-cc;
                System.out.printf(" Only %2s cards in deck, picking %2s cards %n",num,num); 
            }
            cc+=num;
            System.out.println("Picked Cards");
            for(int x=1;x<=num;x++)
            {
                System.out.println(x + ":" + c.nextCard(pos));
                pos++;

            }
            c.printCt();
            System.out.println("Continue 1/Yes 0/No");
            num=kb.nextInt();
            if(num==0)
            {
                toggle=true;
                break;
            }

            if(cc>=52)
            {
                System.out.printf("%10s%n","reset");
                c.setupDeck();
                c.shuffleDeck();
                c.resetCt();
                pos=0;
                cc=0;
                turn =1;
            }
        }

    }
    
    
}

