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
        #self.visible_trees = np.zeros(self.trees.shape, bool)
        for y in range(0, self.trees.shape[1]):
            for x in range(0, self.trees.shape[0]):
                elem = self.trees[y, x]
                #print(elem)

                up = self.trees[0:y, x]
                #print("up:" + str(up))
                down = np.flip(self.trees[y+1:self.trees.shape[1], x])
                #print("down:" + str(down))
                left = self.trees[y, 0:x]
                #print("left:" + str(left))
                right = np.flip(self.trees[y, x+1:self.trees.shape[0]])
                #print("right:" + str(right))
                to_check_arrays = [up, down, left, right]
                for to_check in to_check_arrays:
                    if len(to_check) == 0 or max(tree for tree in to_check) < elem:
                        i += 1
                        break
        return i

if __name__ == '__main__':
    grid = TreeGrid()
    with open('input') as file:
        grid.build(file)

    print("first star:" + str(grid.num_visible_from_outside()))
