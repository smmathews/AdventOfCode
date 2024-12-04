
struct LeftAndRight {
    left: Vec<u32>,
    right: Vec<u32>,
}

fn read_lines(input : &str) -> LeftAndRight {
    let mut left = Vec::new();
    let mut right = Vec::new();
    for line in input.lines() {
        let mut iter = line.split_ascii_whitespace().map(|x| x.parse::<u32>().expect("parse error")).into_iter();
        left.push(iter.next().unwrap());
        right.push(iter.next().unwrap());
    }
    left.sort_unstable();
    right.sort_unstable();
    LeftAndRight { left, right }
}

fn main() {
    let my_str = include_str!("input");
    let left_and_right = read_lines(&my_str);
    let mut distance : u64 = 0;
    for it in left_and_right.left.iter().zip(left_and_right.right.iter()) {
        distance += it.0.abs_diff(*it.1) as u64;
    }
    println!("{}", distance);
}
