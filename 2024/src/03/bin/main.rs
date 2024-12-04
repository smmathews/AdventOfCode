use regex::Regex;

struct MulOperation {
    left: u32,
    right: u32,
}

impl MulOperation {
    fn operate(&self) -> u64 {
        (self.left as u64) * (self.right as u64)
    }
}

fn read_line(input : &str) -> Vec<MulOperation> {
    let re = Regex::new(r"mul\((\d+),(\d+)\)").unwrap();
    let mut muls : Vec<MulOperation> = Vec::new();
    for (_, [left, right]) in re. captures_iter(input).map(|c| c. extract()) {
        muls.push(MulOperation{left: left.parse::<u32>().unwrap(), right: right.parse::<u32>().unwrap()});
    }
    muls
}

fn main() {
    let my_str = include_str!("input");
    let muls = read_line(&my_str);
    let mut added_muls : u64 = 0;
    for mul in muls {
        added_muls += mul.operate()
    }
    println!("star 1 {}", added_muls);
}
