class FastWriter:
    def __init__(self, output_file=None):
        if output_file and os.path.exists(output_file):
            self.file = open(output_file, 'w')
        else:
            self.file = sys.stdout

    def spread_array(self, arr):
        self.file.write(' '.join(map(str, arr)) + '\n')

    def spread_list(self, lst):
        self.file.write(' '.join(map(str, lst)) + '\n')

    def println(self, value):
        self.file.write(str(value) + '\n')

    def print(self, value):
        self.file.write(str(value))

    def printf(self, format_string, *args):
        self.file.write(format_string % args)

    def close(self):
        if self.file != sys.stdout:
            self.file.close()


class FastReader:
    def __init__(self, input_file=None):
        if input_file and os.path.exists(input_file):
            self.file = open(input_file, 'r')
        else:
            self.file = sys.stdin
        self.tokens = []

    def next(self):
        while not self.tokens:
            if self.file:
                self.tokens = self.file.readline().split()
            else:
                self.tokens = input().split()
        return self.tokens.pop(0)

    def next_int(self):
        return int(self.next())

    def next_long(self):
        return int(self.next())

    def next_double(self):
        return float(self.next())

    def next_line(self):
        if self.file:
            return self.file.readline().strip()
        else:
            return input().strip()