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
                print("going in to " + cd_in_result['dir'])
                self.dirs[cd_in_result['dir']].cd_in(file)
                cur_line = file.readline()
        print("going out")


    def sum_dirs_lte(self, max: int):
        all_dirs_lte_sum = sum(dir.sum_dirs_lte(max) for dir in self.dirs.values())
        if self.calc_size() <= max:
            all_dirs_lte_sum += self.calc_size()
        return all_dirs_lte_sum

    def calc_size(self):
        if not self.calculated_size:
            self.calculated_size = sum(file.size for file in self.files.values())+sum(dir.calc_size() for dir in self.dirs.values())
        return self.calculated_size

if __name__ == '__main__':
    with open('input') as file:
        file.readline()# read 'cd /'
        cur_dir = Dir()
        cur_dir.cd_in(file)
        print("first star:" + str(cur_dir.sum_dirs_lte(100000)))
