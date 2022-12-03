def item_to_priority(item):
    o = ord(item)
    if ord('a') <= o <= ord('z'):
        return o - ord('a') + 1
    return o - ord('A') + 27


def read_input(filename):
    total = 0
    with open(filename, 'r') as input_file:
        for line in input_file:
            compartment_size = len(line)//2
            left_set = set()
            right_set = set()
            sets = [left_set, right_set]
            for i in range(0, 2*compartment_size):
                to_add_to = sets[i//compartment_size]
                to_add_to.add(line[i])
            (overlap,) = left_set.intersection(right_set)
            total += item_to_priority(overlap)
    print(total)

if __name__ == '__main__':
    read_input('input')