
struct Report {
    levels: Vec<u32>,
}

impl Report {
    fn is_safe(&self) -> bool {
        let mut all_increasing = true;
        let mut all_decreasing = true;
        let mut iter = self.levels.iter();
        let mut last_num = iter.next().unwrap();
        for level in iter {
            all_increasing = all_increasing && (level > last_num);
            all_decreasing = all_decreasing && (level < last_num);
            if !(all_increasing || all_decreasing) || level.abs_diff(*last_num) > 3 {
                return false;
            }
            last_num = level;
        }
        true
    }
}

fn read_lines(input : &str) -> Vec<Report> {
    let mut reports = Vec::new();
    for line in input.lines() {
        let levels = line.split_ascii_whitespace().map(|x| x.parse::<u32>().expect("parse error")).collect::<Vec<u32>>();
        reports.push(Report { levels });
    }
    reports
}

fn main() {
    let my_str = include_str!("input");
    let reports = read_lines(&my_str);
    let mut num_safe : u64 = 0;
    let mut num_safe_with_remove : u64 = 0;
    for report in reports {
        if report.is_safe() {
            num_safe += 1;
            num_safe_with_remove += 1;
        } else {
            for i in 0..report.levels.len() {
                let mut with_remove = report.levels.clone();
                with_remove.remove(i);
                if(Report{levels: with_remove }.is_safe()) {
                    num_safe_with_remove += 1;
                    break;
                }
            }
        };
    }
    println!("star 1 {}", num_safe);
    println!("star 2 {}", num_safe_with_remove);
}
