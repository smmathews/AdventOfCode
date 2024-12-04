
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
    for report in reports {
        num_safe += if report.is_safe() {1} else {0};
    }
    println!("star 1 {}", num_safe);
}
