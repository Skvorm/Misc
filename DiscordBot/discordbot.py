import os
import discord
import contextvars
import json
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

def save_count():
        print('called')
        global count
        save_data= {'count': count,'wCount':wCount}
        with open('save.txt','w') as outfile:
                json.dump(save_data,outfile)
        print('saving count value of: '+str(count))

def load_count():
        global count
        global wCount
        with open('save.txt') as json_file:
                save_data=json.load(json_file)
                count=save_data['count']
                wCount=save_data['wCount']

async def get_count(message):
        global count
        await message.channel.send('count is ' + str(count))
async def set_count():
        global count
        count+=1
async def check_msg(message,user):
        async for m in channel.history(limit=100):
                if m.author == user:
                        print(str(m) + str(type(m)) + "\n")
                        messages.append(m)
#deletes (100 by default) messages from a specified user on the current chat channel
async def clean(message, user_id, message_count=100):
        print("from"+str(message.author))
        channel=message.channel
        print("cleaning")
        #bot6 = client.get_user(159985870458322944)
        user=client.get_user(user_id)
        #print(bot6)
        #channel=251953520775790603
        #messages = await bot6.history(limit=100).flatten()
        messages=[]
        async for m in channel.history(limit=message_count):
                if m.author==user:
                        print(str(m)+str(type(m))+"\n")
                        messages.append(m)
        print(str(type(messages[0])))
       #messages=await channel.history(limit=100).flatten()
        deleted=0
        for m in range(1,len(messages), 1):
                if messages[m].author==user:
                        print(str(m)+"\n")
                        try:
                                await messages[m].delete()
                                deleted+=1
                        except (discord.errors.HTTPException,discord.errors.Forbidden):
                                print("deleting message" + str(m) + "failed")

        await channel.send(str(deleted)+ " messages deleted")
        print(str(len(messages)-1)+ " messages deleted")
        print("cleaned")

@client.event
async def on_ready():
        for guild in client.guilds:
                if guild.name == bot_GUILD:
                        break
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
                    rng=[1,100,1]


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
                    check=True
                    players_m=chat_channel.members
                    if len(players_m)>=1:
                        for p in players_m:
                            players.append(p.name)
                # for p in players:
                #     card = g.draw(message.author)
                #     print(card+" : "+p.name)
                #     msg+=card+":"+p.name+'\n'
                msg=g.play(players,False,1)

                await message.channel.send(msg)
        if message.content=='!count':
                await set_count()
                await get_count(message)
                save_count()
try:
        try:
                load_count()
        except json.decoder.JSONDecodeError:
                print('file format incorrect')
except FileNotFoundError:
        print('file not found')
        count=0
        wCount=0
if __name__ == '__main__':
        print('starting client')
        print('current Time'+ time.strftime("%c"))
        client.run(token)