from enum import Enum, IntEnum
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


def compute_my_choice(outcome: str, o: OpponentChoice) -> MyChoice:
    non_wrapped_my_choice = 0
    if outcome == "X": # Lose
        non_wrapped_my_choice = o-1
    elif outcome == "Y": #Draw
        non_wrapped_my_choice = o
    else: #Win
        non_wrapped_my_choice = o+1
    if non_wrapped_my_choice > 3:
        non_wrapped_my_choice -= 3
    if non_wrapped_my_choice < 1:
        non_wrapped_my_choice += 3
    return MyChoice(non_wrapped_my_choice)


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
            result = re.match(r"(?P<opponent_choice>\w) (?P<outcome>\w)", line)
            opponent_choice = OpponentChoice[result.groupdict()["opponent_choice"]]
            total += compute_score(opponent_choice, compute_my_choice(result.groupdict()["outcome"], opponent_choice))
    print(total)


if __name__ == '__main__':
    read_input('input')
