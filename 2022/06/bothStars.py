from queue import Queue


def read_input(filename, window_length):
    line = open(filename).readline()
    q = Queue(window_length)
    d = {}
    for i in range(0, len(line)+1):
        if q.full():
            if len(d) == window_length:
                return i
            val = q.get()
            d[val] -= 1
            if d[val] == 0:
                d.pop(val)
        char = line[i]
        if char in d:
            d[char] += 1
        else:
            d[char] = 1
        q.put(char)


if __name__ == '__main__':
    print("first star:" + str(read_input('input', 4)))
    print("second star:" + str(read_input('input', 14)))
