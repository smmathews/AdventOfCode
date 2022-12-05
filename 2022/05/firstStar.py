import re

line_pattern = re.compile(r"move (?P<num>\d+) from (?P<initial>\d+) to (?P<final>\d+)")


def build_stack(file, num_stacks):
    stacks = []
    for i in range(0, num_stacks):
        stacks.append([])
    while True:
        line = file.readline()
        for i in range(0, len(line)-1):
            if i % 4 == 1:
                if line[i] == '1':
                    for stack in stacks:
                        stack.reverse()
                    return stacks
                elif line[i] != ' ':
                    stacks[i//4].append(line[i])


def read_input(filename, num_stacks):
    file = open(filename)
    stacks = build_stack(file, num_stacks)
    file.readline()# skip empty line
    with open(filename) as input_file:
        for line in file.readlines():
            result = re.match(line_pattern, line).groupdict()
            num = int(result["num"])
            for i in range(0, num):
                stacks[int(result["final"])-1].append(stacks[int(result["initial"])-1].pop())
    to_print = ""
    for i in range(0, len(stacks)):
        to_print += stacks[i].pop()
    print(to_print)


if __name__ == '__main__':
    read_input('input', 9)