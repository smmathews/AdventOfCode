from dataclasses import dataclass

import numpy as np
a = np.array([1, 2, 3])


@dataclass
class TreeGrid:
    trees: np.array = None

    def build(self, in_file):
        self.trees = np.genfromtxt(in_file, delimiter=1, dtype=np.ubyte)

    def num_visible_from_outside(self):
        i = 0
        for y in range(0, self.trees.shape[1]):
            for x in range(0, self.trees.shape[0]):
                elem = self.trees[y, x]
                up = self.trees[0:y, x]
                down = np.flip(self.trees[y+1:self.trees.shape[1], x])
                left = self.trees[y, 0:x]
                right = np.flip(self.trees[y, x+1:self.trees.shape[0]])
                to_check_arrays = [up, down, left, right]
                for to_check in to_check_arrays:
                    if len(to_check) == 0 or max(tree for tree in to_check) < elem:
                        i += 1
                        break
        return i

    def highest_scenic_score_possible(self):
        best = 0
        for y in range(0, self.trees.shape[1]):
            for x in range(0, self.trees.shape[0]):
                elem = self.trees[y, x]
                up = np.flip(self.trees[0:y, x])
                down = self.trees[y+1:self.trees.shape[1], x]
                left = np.flip(self.trees[y, 0:x])
                right = self.trees[y, x+1:self.trees.shape[0]]
                to_check_arrays = [up, down, left, right]
                scenic_score = 1
                for to_check in to_check_arrays:
                    i = 0
                    for tree in to_check:
                        i += 1
                        if tree >= elem:
                            break
                    scenic_score = scenic_score*i
                best = max(best, scenic_score)
        return best


if __name__ == '__main__':
    grid = TreeGrid()
    with open('input') as file:
        grid.build(file)

    print("first star:" + str(grid.num_visible_from_outside()))
    print("second star:" + str(grid.highest_scenic_score_possible()))
