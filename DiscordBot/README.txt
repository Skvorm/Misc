A simple Discord Bot that allows users to roll various-sided dice(d6,d10,d20,etc) or random numbers in a range, and also allows users to play a very simple card game(assigns card to each user in the calling channel, highest cards win).
Commands
-!roll
	Can be used with minimum and maximum range
	Can also be used to emulate dice rolls with a variable number of dice and number of sides
	Example
		!roll 1-100
		!roll 3d6
		!roll 1d20
-!card
	-draws a card for each user currently in the same voice channel as the sending user. The highest value is the winner.(Very Exciting, I know)



-Requires
	-Discord.py
	-Dot-env (Stores Discord Api Key)
