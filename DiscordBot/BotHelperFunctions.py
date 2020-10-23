import discord


class BotHelperFunctions:
    def get_roll_range(self, input_string,max_val=10000,max_die=1000):
        # parses input from !roll command
        # outputs list of[min,max,# of die]
        param = input_string.replace('!roll', '')
        param = param.strip()
        if param.find('d') != -1:
            param = param.split('d')
            print(param)
            start = 1
            if len(param) > 1:
                if param[0] == '':
                    param[0] = 1
                if param[1] == '':
                    param[1] = 6
                end = int(param[1])
                num_die = int(param[0])
                if end >= max_val or end <= 0:
                    end = 100
                if num_die > max_die or num_die <= 0:
                    num_die = 1
            else:
                end = int(param[0])
            out = [start, end, num_die]
        elif param.find('-') != -1:
            param = param.split('-')
            start = int(param[0])
            end = int(param[1])
            out = [start, end, 1]
        else:
            # min,max,times rolled
            out = [1, 100, 1]
        return out


if __name__ == '__main__':
    d = BotHelperFunctions()
    print(d.get_roll_range("!roll 1-100"))
    print(d.get_roll_range("!roll 2-59"))
    print(d.get_roll_range("!roll"))
    print('wor-d'.split('-'))
    # print(d.get_roll_range("!roll"))
