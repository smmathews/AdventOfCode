
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
    println!("star 1 {}", distance);

    let mut similarity: u64 = 0;

    let mut last_right : u32 = u32::MAX;
    let mut num_last_right = 0;
    let mut right_iter = left_and_right.right.iter().peekable();
    for left_val in left_and_right.left.iter() {
        while right_iter.peek().is_some_and(|&right_val| right_val < left_val) {
            right_iter.next();
        }
        if *left_val == last_right {
            similarity += (left_val * num_last_right) as u64;
        } else if right_iter.peek().is_some_and(|&right_val| right_val == left_val) {
            last_right = *left_val;
            num_last_right = 0;
            while right_iter.peek().is_some_and(|&right_val| right_val == left_val) {
                num_last_right += 1;
                right_iter.next();
            }
            similarity += (left_val * num_last_right) as u64;
        }
    }

    println!("star 2 {}", similarity);
}
