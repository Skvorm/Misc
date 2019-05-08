import java.util.*;
public class npcBattle
{
    public static void main(String [] args)

    {   Scanner kb = new Scanner(System.in);
        Random r = new Random();
        NPCGen n = new NPCGen();
        NPCGen n2 = new NPCGen();
        int r1,r2,input=1;
        String inp=null;
        r1=r.nextInt(100)+1;
        r2=r.nextInt(100)+1;
        System.out.println();
        n.Generate(r1);
        n2.Generate(r2);
        n.displayStats();
        System.out.println();
        n2.displayStats();
        boolean tick=false;
        do
        { 

            System.out.println("(1 or 2 for stats)-(3 to battle");
            System.out.println("(4 for battle series)(0 to quit)");
            input=kb.nextInt();
            if (input==0)
            {
                tick=true;
            }
            else if(input==1)
            {
                n.displayStats();
            }
            else if(input==2)
            {
                n2.displayStats();
            }
            else if(input==3)
            {
                n.battleSeq(n,n2,1,true);
            }
            else if(input==4)
            {   int v=n.getVict(),v2=n2.getVict(); 
                System.out.println("Enter number of battles");
                int num = kb.nextInt();
                n.battleSeq(n,n2,num,false);
                System.out.println("Series Results");
                System.out.println(n.getName()+ " - " + (n.getVict()-v) + " Victories");
                System.out.println(n2.getName()+ " - " + (n2.getVict()-v2) + " Victories");

            }
        }while (!tick);
        System.out.println(n.getLvl()+ "-" + n.getName()+ " -Victories- " + n.getVict()+ "|" + " " + n.getLoss() + " Losses");
        System.out.println(n2.getLvl()+ "-" + n2.getName()+ " -Victories- " + n2.getVict()+ "|" + " " + n2.getLoss() + " Losses");
    }
}
