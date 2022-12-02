from enum import IntEnum
import re


class Shape(IntEnum):
    ROCK = 1
    PAPER = 2
    SCISSORS = 3


class OpponentChoice(IntEnum):
    A = Shape.ROCK
    B = Shape.PAPER
    C = Shape.SCISSORS


class MyChoice(IntEnum):
    X = Shape.ROCK
    Y = Shape.PAPER
    Z = Shape.SCISSORS


def compute_score(o: OpponentChoice, m: MyChoice):
    outcome_score = 0
    if o == m:
        outcome_score = 3
    elif o+1 == m or o-2 == m:
        outcome_score = 6
    return m+outcome_score


def read_input(filename):
    total = 0
    with open(filename, 'r') as input_file:
        for line in input_file:
            result = re.match(r"(?P<opponent_choice>\w) (?P<my_choice>\w)", line)
            total += compute_score(OpponentChoice[result.groupdict()["opponent_choice"]], MyChoice[result.groupdict()["my_choice"]])
    print(total)


if __name__ == '__main__':
    read_input('input')
