import java.util.*;
public class NPCGen
{Random r= new Random();
    NameRandom n = new NameRandom();
    boolean spec=false;
    private double xp;
    private int head,torso,leg,power,lvl,vict,loss;
    private String hc,tc,lc,name;
    public NPCGen() 
    {
        name="default";
        lvl=1;
        head=0;
        torso=0;
        leg=0;
        power=0;
        hc="black";
        tc="black";
        lc="black";
        xp=0;
        vict=0;
        loss=0;
    }

    public void xpGain(NPCGen opp,int state)
    { //state 1-victory
        //    0-tie
        //   -1-loss
        double diff,xpg;
        diff = this.lvl-opp.lvl;
        if(state==1)
        {
            if (this.lvl>opp.lvl)
                xpg = (1/diff)*4;
            else 
                xpg = (4 * opp.lvl);
        }
        else if(state==0)
        {
            xpg=.75*opp.lvl;
        }
        else
        {
            xpg=.25*opp.lvl;
        }
        this.xp=xpg+this.xp;

    }

    public double getXp()
    {
        return xp;
    }

    public int getVict()
    {
        return vict;
    }

    public int getLoss()
    {
        return loss;
    }

    public int compareTo(NPCGen g)
    {
        if (this.power > g.power)
            return 1;
        if (this.power == g.power)
            return 0;
        if (this.power < g.power)
            return -1;
        else 
            return 0;

    }

    public void battle(NPCGen npc1,NPCGen npc2,boolean disp)
    {int result,ran,npc1Att,npc2Att;
        boolean fumb1=false,fumb2=false;
        //result=npc1.compareTo(npc2);
        int ran1=r.nextInt(3);
        npc1Att=npc1.power*ran1;
        int ran2=r.nextInt(3);
        npc2Att=npc2.power*ran2;
        result=Integer.compare(npc1Att,npc2Att);
        if (disp==true)
        {
            if((ran1==0)&&(ran2==0))
            {
                fumb1=true;
                fumb2=true;
                System.out.println("Both Combatants fumbled");
            }
            else if (ran1==0)
            {fumb1=true;
                System.out.println(npc1.getName() + " fumbled");   
            }
            else if(ran2==0)
            {fumb2=true;
                System.out.println(npc2.getName() + " fumbled");   
            }
        }
        npc2Att=npc2.power*ran2;
        result=Integer.compare(npc1Att,npc2Att);

        if (result==1)
        {  if(disp==true)
            {
                System.out.println(npc1.getName() + " is the victor");
            }
            npc1.xpGain(npc2,1);
            npc2.xpGain(npc1,-1);
            npc1.vict++;
            npc2.loss++;
        }

        if (result==0)
        {   if(disp==true){
                System.out.println(npc1.getName() + npc2.getName()+ " Tied");
            }
            npc1.xpGain(npc2,0);
            npc2.xpGain(npc1,0);

        }
        if (result==-1)
        { 
            if(disp==true){
                System.out.println(npc2.getName() + " is the victor");
            }
            npc2.xpGain(npc1,1);
            npc1.xpGain(npc2,-1);
            npc2.vict++;
            npc1.loss++;
        }

    }

    public void displayStats()
    {
        System.out.print(" Name = "+ getName());
        if(getSpec()==true)
        {
            System.out.println(" - (Boss)");
        }
        else
        {
            System.out.println(" - (Normal)");
        }
        System.out.println(" Head - "+getH()+" - "+getHc());
        System.out.println("Torso - "+getT()+" - "+getTc());
        System.out.println(" Legs - "+getL()+" - "+getLc());
        System.out.println("Overall Power - " +getPower());

    }

    public int partChoose()
    {
        int val= r.nextInt(3)+1;
        return val;

    }

    public int getVal()
    {   Random r= new Random();
        int val = r.nextInt(9)+1;
        return val;
    }

    public String colorC()
    {int val = getVal();
        String color =null;
        if(val<3)
            color="blue";
        else if((val>3)&&(val<7))
            color="green";
        else 
            color="red";
        return color;
    }

    public int getLvl()
    {
        return lvl;
    }

    public void Generate(int powerLvl)
    {   name= n.GenName();
        head=getVal();
        torso=getVal();
        leg=getVal();
        power=head+torso+leg;
        if(Special()==true)
            powerLvl=(powerLvl*(r.nextInt(3)+1));
        if(power<powerLvl)
            powerAdj(powerLvl);

        hc=colorC();
        tc=colorC();
        lc=colorC();
    }

    public String getName()
    {
        return name;
    }

    public int getH()
    {
        return head;
    }

    public int getT()
    {
        return torso;
    }

    public int getL()
    {
        return leg;   
    }

    public String getHc()
    {
        return hc;
    }

    public String getTc()
    {
        return tc;
    }

    public String getLc()
    {
        return lc;

    }

    public int getPower()
    { power=getH()+getT()+getL();
        return power;
    }

    public void powerAdj(int powerLvl)
    { int step=1;
        while(getPower()<powerLvl)
        {
            int val= partChoose();
            if (val<=1)
                setH(getH()+step);
            else if(val==2)
                setT(getT()+step);
            else if(val>=3)
                setL(getL()+step);
        }

    }

    public void setH(int val)
    {
        head=val;
    }

    public void setT(int val)
    {
        torso=val;
    }

    public void setL(int val)
    {
        leg=val;   
    }

    public void setHc(String val)
    {
        hc=val;
    }

    public void setTc(String val)
    {
        tc=val;
    }

    public void setLc(String val)
    {
        lc=val;

    }

    public void setName(String val)
    {
        name=val;
    }

    public boolean Special()
    {
        spec=false;
        double val = r.nextDouble();
        if (val<.5)
            spec=true;
        return spec;

    }

    public boolean getSpec()
    {
        return spec;
    }

    public void battleSeq(NPCGen n,NPCGen n2,int batNum,boolean disp)
    {   
        for(int x=0;x<batNum;x++)
        {

        n.battle(n,n2,disp);
        if (disp==true)
        {
            System.out.print(n.power + " -- " );
            System.out.println(n.getName()+"-XP-"+n.getXp());
            System.out.print(n2.power + " -- " );
            System.out.println(n2.getName()+"-XP-"+n2.getXp());
            if((int)n.xp%10==0)
            {
                n.lvl++;
                n.powerAdj((n.power+(10*n.getVal())));
                System.out.print(n.power + " -- " );
                System.out.println(n.getName() + " leveled up to " +n.lvl);
            }
            if((int)n2.xp%10==0)
            {
                n2.lvl++;
                n2.powerAdj((n2.power+(10*n2.getVal())));
                System.out.print(n2.power + " -- " );
                System.out.println(n2.getName() + " leveled up to lvl " +n2.lvl);
            }

        }
        else

        {
            if((int)n.xp%10==0)
            {
                n.lvl++;
                n.powerAdj((n.power+(10*n.getVal())));
                System.out.print(n.power + " -- " );
                System.out.println(n.getName() + " leveled up to " +n.lvl);
            }
            if((int)n2.xp%10==0)
            {
                n2.lvl++;
                n2.powerAdj((n2.power+(10*n2.getVal())));
                System.out.print(n2.power + " -- " );
                System.out.println(n2.getName() + " leveled up to lvl " +n2.lvl);
            }

        }
    }
}
}
