def item_to_priority(item):
    o = ord(item)
    if ord('a') <= o <= ord('z'):
        return o - ord('a') + 1
    return o - ord('A') + 27


def read_input(filename):
    total = 0
    with open(filename, 'r') as input_file:
        sets = []
        for line in input_file:
            cur_set = set()
            sets.append(cur_set)
            for i in range(0, len(line)-1):
                cur_set.add(line[i])
            if len(sets) == 3:
                (overlap,) = sets[0].intersection(sets[1]).intersection(sets[2])
                total += item_to_priority(overlap)
                sets = []
    print(total)


if __name__ == '__main__':
    read_input('input')
