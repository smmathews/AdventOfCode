from dataclasses import dataclass
import numpy as np


@dataclass
class TreeGrid:
    trees: np.array

    def tree_arrays(self, x, y, from_tree: bool):
        up = self.trees[0:y, x]
        down = self.trees[y + 1:self.trees.shape[1], x]
        left = self.trees[y, 0:x]
        right = self.trees[y, x + 1:self.trees.shape[0]]
        if from_tree:
            return [np.flip(up), down, np.flip(left), right]
        else:  # from edge
            return [up, np.flip(down), left, np.flip(right)]

    def num_visible_from_outside(self):
        i = 0
        for y in range(0, self.trees.shape[1]):
            for x in range(0, self.trees.shape[0]):
                elem = self.trees[y, x]
                for to_check in self.tree_arrays(x, y, False):
                    if len(to_check) == 0 or max(tree for tree in to_check) < elem:
                        i += 1
                        break
        return i

    def highest_scenic_score_possible(self):
        best = 0
        for y in range(0, self.trees.shape[1]):
            for x in range(0, self.trees.shape[0]):
                elem = self.trees[y, x]
                scenic_score = 1
                for to_check in self.tree_arrays(x, y, True):
                    i = 0
                    for tree in to_check:
                        i += 1
                        if tree >= elem:
                            break
                    scenic_score = scenic_score * i
                best = max(best, scenic_score)
        return best


if __name__ == '__main__':
    grid = TreeGrid(np.genfromtxt('input', delimiter=1, dtype=np.ubyte))
    print("first star:" + str(grid.num_visible_from_outside()))
    print("second star:" + str(grid.highest_scenic_score_possible()))
