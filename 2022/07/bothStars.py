from dataclasses import dataclass, field
import re

file_pattern = re.compile(r"(?P<size>\d+) (?P<name>.+)")
dir_pattern = re.compile(r"dir (?P<name>\w+)")
cd_out_pattern = re.compile(r"\$ cd \.\.")
cd_in_pattern = re.compile(r"\$ cd (?P<dir>\w+)")
ls_pattern = re.compile(r"\$ ls")

@dataclass
class File:
    size: int


class Dir:
    pass


@dataclass
class Dir:
    files: dict[str, File] = field(default_factory=lambda: ({}))
    dirs: dict[str, Dir] = field(default_factory=lambda: ({}))
    calculated_size: int = None

    def cd_in(self, file: File):
        cur_line = file.readline()
        while cur_line and not cd_out_pattern.match(cur_line):
            if ls_pattern.match(cur_line):
                cur_line = file.readline()
                while cur_line and not cur_line.startswith('$'):
                    dir_result = dir_pattern.match(cur_line)
                    if dir_result:
                        self.dirs[dir_result.groupdict()['name']] = Dir()
                    else:
                        file_result = file_pattern.match(cur_line).groupdict()
                        self.files[file_result['name']] = File(int(file_result['size']))
                    cur_line = file.readline()
            else:
                cd_in_result = cd_in_pattern.match(cur_line).groupdict()
                self.dirs[cd_in_result['dir']].cd_in(file)
                cur_line = file.readline()


    def sum_dirs_lte(self, max: int):
        all_dirs_lte_sum = sum(dir.sum_dirs_lte(max) for dir in self.dirs.values())
        if self.calc_size() <= max:
            all_dirs_lte_sum += self.calc_size()
        return all_dirs_lte_sum

    def calc_size(self):
        if not self.calculated_size:
            self.calculated_size = sum(file.size for file in self.files.values())+sum(dir.calc_size() for dir in self.dirs.values())
        return self.calculated_size

    def find_smallest_dir_lte(self, min_space_to_free: int):
        if self.calc_size() < min_space_to_free:
            return None
        cur_dir = self
        for dir in self.dirs.values():
            to_test = dir.find_smallest_dir_lte(min_space_to_free)
            if to_test and to_test.calc_size() < cur_dir.calc_size():
                cur_dir = to_test
        return cur_dir

if __name__ == '__main__':
    with open('input') as file:
        file.readline()# read 'cd /'
        cur_dir = Dir()
        cur_dir.cd_in(file)
        print("first star:" + str(cur_dir.sum_dirs_lte(100000)))

        os_space = 70000000
        needed_space = 30000000
        have_space = os_space - cur_dir.calc_size()
        min_space_to_free = needed_space - have_space
        dir_to_delete = cur_dir.find_smallest_dir_lte(min_space_to_free)

        print("second star:" + str(dir_to_delete.calc_size()))
