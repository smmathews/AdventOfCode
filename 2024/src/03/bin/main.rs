use regex::Regex;

struct MulOperation {
    left: u32,
    right: u32,
    disabled: bool,
}

impl MulOperation {
    fn operate(&self, respect_disabled: bool) -> u64 {
        (self.left as u64) * (self.right as u64) * (if respect_disabled && self.disabled {0} else {1})
    }
}

fn read_line(input : &str) -> Vec<MulOperation> {
    let mul_regex = Regex::new(r"mul\((\d+),(\d+)\)").unwrap();
    let do_regex = Regex::new(r"do\(\)").unwrap();
    let do_not_regex = Regex::new(r"don't\(\)").unwrap();

    let dos : Vec<usize> = do_regex.find_iter(input).map(|m| m.start()).collect();
    let donts : Vec<usize> = do_not_regex.find_iter(input).map(|m| m.start()).collect();

    let mut dos_iter = dos.iter().peekable();
    let mut donts_iter = donts.iter().peekable();
    let mut last_do : isize = 0;
    let mut last_dont = isize::MIN;
    let mut disabled = false;
    let mut muls : Vec<MulOperation> = Vec::new();
    for found_match in mul_regex.find_iter(input) {
        /// update disabled from both iterators
        let cur_start = found_match.start();
        while(dos_iter.peek().is_some_and(|x| **x <= cur_start)) {
            last_do = *dos_iter.next().unwrap() as isize;
            disabled = false;
        }
        while(donts_iter.peek().is_some_and(|x| **x <= cur_start)) {
            last_dont = *donts_iter.next().unwrap() as isize;
            if last_dont > last_do {
                disabled = true;
            }
        }
        for (_, [left, right]) in mul_regex.captures_iter(found_match.as_str()).map(|c| c. extract()) {
            muls.push(MulOperation{left: left.parse::<u32>().unwrap(), right: right.parse::<u32>().unwrap(), disabled});
        }
    }
    muls
}

fn main() {
    let my_str = include_str!("input");
    let muls = read_line(&my_str);
    let mut added_muls : u64 = 0;
    let mut added_muls_respect_disabled : u64 = 0;
    for mul in muls {
        added_muls += mul.operate(false);
        added_muls_respect_disabled += mul.operate(true);
    }
    println!("star 1 {}", added_muls);
    println!("star 2 {}", added_muls_respect_disabled);
}
