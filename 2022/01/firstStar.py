from dataclasses import dataclass


@dataclass
class FoodItem:
    calories: int


@dataclass
class Elf:
    food_items: list[FoodItem]

    def calories_in_food(self) -> int:
        return sum(f.calories for f in self.food_items)


def read_input(filename):
    elves = []
    cur_elf = None
    with open(filename, 'r') as input_file:
        for i in input_file:
            if i == '\n':
                cur_elf = None
            else:
                if cur_elf is None:
                    cur_elf = Elf([])
                    elves.append(cur_elf)
                cur_elf.food_items.append(FoodItem(int(i)))
    print(max(elf.calories_in_food() for elf in elves))


if __name__ == '__main__':
    read_input('input')
