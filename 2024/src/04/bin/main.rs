
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

impl CurSearch {
    fn is_cur_index_valid(&self, grid: &Grid) -> bool {
        self.x >= 0 && self.x < grid.width as isize && self.y>= 0 && self.y < grid.height as isize && grid.chars[(grid.width*self.y as usize) + self.x as usize] == XMAS.chars().nth(self.cur_length-1).unwrap()
    }
    fn is_done(&self) -> bool {
        self.cur_length == XMAS.chars().count()
    }
    fn increment(&mut self) {
        self.x += self.direction_x;
        self.y += self.direction_y;
        self.cur_length += 1;
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

fn increment_and_add(mut cur_search: CurSearch, grid: &Grid, valid: &mut Vec<CurSearch>) {
    cur_search.increment();
    if cur_search.is_cur_index_valid(grid) {
        valid.push(cur_search)
    }
}

fn main() {
    let my_str = include_str!("input");
    let grid = read_lines(&my_str);
    let mut searches: Vec<CurSearch> = Vec::new();
    for y in 0.. grid.height {
        for x in 0.. grid.width {
            let x_as_signed = x as isize;
            let y_as_signed = y as isize;
            let cur_length = 1;
            if (CurSearch{x: x_as_signed, y : y_as_signed, direction_x: 0, direction_y: 0, cur_length}.is_cur_index_valid(&grid)) {
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: -1, direction_y: -1, cur_length }, &grid, &mut searches);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: -1, direction_y: 0, cur_length }, &grid, &mut searches);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: -1, direction_y: 1, cur_length }, &grid, &mut searches);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 0, direction_y: 1, cur_length }, &grid, &mut searches);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 0, direction_y: -1, cur_length }, &grid, &mut searches);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 1, direction_y: -1, cur_length }, &grid, &mut searches);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 1, direction_y: 0, cur_length }, &grid, &mut searches);
                increment_and_add(CurSearch { x: x_as_signed, y: y_as_signed, direction_x: 1, direction_y: 1, cur_length }, &grid, &mut searches);
            }
        }
    }
    let mut completed = 0;
    while !searches.is_empty() {
        let iter = searches.iter();
        let mut new_searches = Vec::new();
        for cur_search in iter {
            if cur_search.is_cur_index_valid(&grid) {
                if cur_search.is_done() {
                   completed += 1;
                } else {
                    increment_and_add(CurSearch{x: cur_search.x, y: cur_search.y, direction_x: cur_search.direction_x, direction_y: cur_search.direction_y, cur_length: cur_search.cur_length}, &grid, &mut new_searches);
                }
            }
        }
        searches = new_searches;
    }
    println!("star 1 {}", completed);
}
