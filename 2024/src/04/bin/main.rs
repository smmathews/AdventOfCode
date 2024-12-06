use std::collections::HashSet;

struct Grid {
    chars: Vec<char>,
    width: usize,
    height: usize,
}

struct CurSearch {
    x : isize,
    y : isize,
    direction_x : isize,
    direction_y : isize,
    cur_length: usize,
}

static XMAS: &'static str = "XMAS";
static MAS: &'static str = "MAS";

impl CurSearch {
    fn is_cur_index_valid(&self, grid: &Grid, string : &str) -> bool {
        self.x >= 0 && self.x < grid.width as isize && self.y>= 0 && self.y < grid.height as isize && grid.chars[self.get_index_at_delta(grid, 0)] == string.chars().nth(self.cur_length-1).unwrap()
    }
    fn is_done(&self, string : &str) -> bool {
        self.cur_length == string.chars().count()
    }
    fn increment(&mut self) {
        self.x += self.direction_x;
        self.y += self.direction_y;
        self.cur_length += 1;
    }

    fn get_index_at_delta(&self, grid: &Grid, delta : isize) -> usize {
        (grid.width*(self.y+(delta*self.direction_y)) as usize) + (self.x+(delta*self.direction_x)) as usize
    }
}

fn read_lines(input : &str) -> Grid {
    let mut chars = Vec::new();
    let mut width: Option<usize> = None;
    for line in input.lines() {
        for c in line.chars() {
            chars.push(c);
        }
        if width.is_none() {
            width = Some(chars.len());
        }
    }

    let height = chars.len() / width.unwrap();
    Grid{chars, width : width.unwrap(), height}
}

fn increment_and_add(mut cur_search: CurSearch, grid: &Grid, valid: &mut Vec<CurSearch>, string: &str) {
    cur_search.increment();
    if cur_search.is_cur_index_valid(grid, &string) {
        valid.push(cur_search)
    }
}

fn main() {
    let my_str = include_str!("input");
    let grid = read_lines(&my_str);
    star_one(&grid);
    star_two(&grid);
}

fn star_one(grid: &Grid) {
    let mut searches: Vec<CurSearch> = Vec::new();
    for y in 0..grid.height {
        for x in 0..grid.width {
            let x_as_signed = x as isize;
            let y_as_signed = y as isize;
            let cur_length = 1;
            if (CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 0, direction_y: 0, cur_length }.is_cur_index_valid(&grid, XMAS)) {
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: -1, direction_y: -1, cur_length }, &grid, &mut searches, XMAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: -1, direction_y: 0, cur_length }, &grid, &mut searches, XMAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: -1, direction_y: 1, cur_length }, &grid, &mut searches, XMAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 0, direction_y: 1, cur_length }, &grid, &mut searches, XMAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 0, direction_y: -1, cur_length }, &grid, &mut searches, XMAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 1, direction_y: -1, cur_length }, &grid, &mut searches, XMAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 1, direction_y: 0, cur_length }, &grid, &mut searches, XMAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 1, direction_y: 1, cur_length }, &grid, &mut searches, XMAS);
            }
        }
    }
    let mut completed = 0;
    while !searches.is_empty() {
        let iter = searches.iter();
        let mut new_searches = Vec::new();
        for cur_search in iter {
            if cur_search.is_cur_index_valid(&grid, XMAS) {
                if cur_search.is_done(XMAS) {
                    completed += 1;
                } else {
                    increment_and_add(CurSearch { x: cur_search.x, y: cur_search.y, direction_x: cur_search.direction_x, direction_y: cur_search.direction_y, cur_length: cur_search.cur_length }, &grid, &mut new_searches, XMAS);
                }
            }
        }
        searches = new_searches;
    }
    println!("star 1 {}", completed);
}

fn star_two(grid: &Grid) {
    let mut searches: Vec<CurSearch> = Vec::new();
    for y in 0..grid.height {
        for x in 0..grid.width {
            let x_as_signed = x as isize;
            let y_as_signed = y as isize;
            let cur_length = 1;
            if (CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 0, direction_y: 0, cur_length }.is_cur_index_valid(&grid, MAS)) {
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: -1, direction_y: -1, cur_length }, &grid, &mut searches, MAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: -1, direction_y: 1, cur_length }, &grid, &mut searches, MAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 1, direction_y: -1, cur_length }, &grid, &mut searches, MAS);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 1, direction_y: 1, cur_length }, &grid, &mut searches, MAS);
            }
        }
    }
    let mut completed: Vec<CurSearch> = Vec::new();
    while !searches.is_empty() {
        let iter = searches.iter();
        let mut new_searches = Vec::new();
        for cur_search in iter {
            if cur_search.is_cur_index_valid(&grid, MAS) {
                if cur_search.is_done(MAS) {
                    completed.push(CurSearch{x: cur_search.x, y: cur_search.y, direction_x: cur_search.direction_x, direction_y: cur_search.direction_y, cur_length: cur_search.cur_length});
                } else {
                    increment_and_add(CurSearch { x: cur_search.x, y: cur_search.y, direction_x: cur_search.direction_x, direction_y: cur_search.direction_y, cur_length: cur_search.cur_length }, &grid, &mut new_searches, MAS);
                }
            }
        }
        searches = new_searches;
    }

    // find overlap
    let mut found_overlap = 0;
    let mut centroid_index_set = HashSet::new();
    for cur_search in &completed {
        found_overlap += if centroid_index_set.insert(cur_search.get_index_at_delta(grid, -1)) {0} else {1}
    }
    println!("star 2 {}", found_overlap);
}

