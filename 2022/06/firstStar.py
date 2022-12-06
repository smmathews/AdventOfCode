from queue import Queue


def read_input(filename, window_length):
    line = open(filename).readline()
    q = Queue()
    d = {}
    for i in range(0, len(line)):
        char = line[i]
        if q.qsize() > 3:
            d[q.get()] -= 1
        if char in d:
            d[char] += 1
        else:
            d[char] = 1
        q.put(char)
        if q.qsize() == 4 and not any(v > 1 for v in d.values()):
            return i+1


if __name__ == '__main__':
    print(read_input('input', 4))
