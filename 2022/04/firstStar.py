from pyroaring import BitMap
import re

line_pattern = re.compile(r"(?P<elf1_low>\d+)-(?P<elf1_high>\d+),(?P<elf2_low>\d+)-(?P<elf2_high>\d+)")


def read_input(filename):
    total_overlap = 0
    with open(filename, 'r') as input_file:
        for line in input_file:
            elf1 = BitMap()
            elf2 = BitMap()
            result = re.match(line_pattern, line)
            elf1.add_range(int(result.groupdict()["elf1_low"]), int(result.groupdict()["elf1_high"])+1)
            elf2.add_range(int(result.groupdict()["elf2_low"]), int(result.groupdict()["elf2_high"])+1)
            intersect_cardinality = elf1.intersection_cardinality(elf2)
            if intersect_cardinality == len(elf1) or intersect_cardinality == len(elf2):
                total_overlap += 1
    print(total_overlap)


if __name__ == '__main__':
    read_input('input')