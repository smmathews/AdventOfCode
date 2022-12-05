from pyroaring import BitMap
import re

line_pattern = re.compile(r"move (?P<num>\d+) from (?P<initial>\d+) to (?P<final>\d+)")

test_stacks = [
    [],
    ['Z','N'],
    ['M','C','D'],
    ['P'],
]

def read_input(filename):
    with open(filename) as input_file:
        for line in input_file:
            result = re.match(line_pattern, line).groupdict()
            num = int(result["num"])
            for i in range(0, num):
                test_stacks[int(result["final"])].append(test_stacks[int(result["initial"])].pop())
    toPrint = ""
    for i in range(1, len(test_stacks)):
        toPrint += test_stacks[i].pop()
    print(toPrint)

if __name__ == '__main__':
    read_input('input')