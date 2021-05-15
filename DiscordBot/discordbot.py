import os
import discord
import contextvars
import datetime as dt
import random
from BotHelperFunctions import BotHelperFunctions
from Game import Game
from dotenv import load_dotenv
load_dotenv()
token = os.getenv('DISCORD_TOKEN')
bot_GUILD = os.getenv('DISCORD_GUILD')
client = discord.Client()
delete_queue={}
prev=None
b = BotHelperFunctions()
time = dt.datetime.now()

@client.event
async def on_ready():
        print(f'{client.user.name} connected')
@client.event
async def on_message(message):
        if message.author == client.user:
                return
        if message.content.startswith("!roll"):
                try:
                        rng = b.get_roll_range(str(message.content))
                except ValueError:
        #default value of range(1-00), and 1 die
                    rng=[1, 100, 1]


                if rng[2]>1:
                        vals=[]
                        total=0
                        for i in range(0,int(rng[2])):
                                roll_val = random.randint(rng[0], rng[1])
                                vals.append(roll_val)
                                total+=roll_val
                        roll_val = random.randint(rng[0], rng[1])
                        if len(vals) <= 50:
                                msg='**('+ str(rng[2])+'d'+str(rng[1])+')**'+str(message.author.name)+" rolled ** " +str(total)+':'+str(vals)+"**"
                        else:
                                msg = '**(' + str(rng[2]) + 'd' + str(rng[1]) + ')**' + str(
                                        message.author.name) + " rolled ** " + str(total) + "**"
                else:
                        roll_val = random.randint(rng[0], rng[1])
                        msg ="**("+str(rng[0])+"," +str(rng[1]) + ")**:"+str(message.author.name)+" rolled ** "+str(roll_val)+"**"
                await message.channel.send(msg)
        if message.content=='!card':
                g=Game()
                chat_channel=0
                check=False
                msg=''
                players=[]
                ch_test=message.author.voice
                if ch_test is not None:
                    chat_channel=ch_test.channel
                    players_m=chat_channel.members
                    if len(players_m)>=1:
                        for p in players_m:
                            players.append(p.name)
                msg=g.play(players, 1)
                await message.channel.send(msg)
if __name__ == '__main__':
        print('starting client')
        print('current Time'+ time.strftime("%c"))
        client.run(token)