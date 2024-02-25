class Fenwick {
    int n;
    int arr[];
    int org[];

    Fenwick(int n) {
        this.n = n + 1;
        this.arr = new int[n + 1];
        this.org = new int[n + 1];
    }

    void add(int i, int e) {
        i++;
        while (i < n) {
            arr[i] += e;
            i += (i & (-i));
        }
    }

    int sum(int i) {
        i++;
        int sum = 0;
        while (i > 0) {
            sum += arr[i];
            i -= (i & (-i));
        }
        return sum;
    }

    int range(int fr, int to) {
        return sum(to) - sum(fr - 1);
    }

    void update(int i, int e) {
        i++;
        int up = e - org[i];
        org[i] = e;
        add(i - 1, up);
    }

    int lowerBound(int val) {
        int sum = 0;
        int pos = 0;
        int bitmask = 1;
        while (bitmask <= n)
            bitmask <<= 1;
        bitmask >>= 1;
        for (int i = bitmask; i > 0; i >>= 1) {
            if (pos + i < n && sum + arr[pos + i] < val) {
                sum += arr[pos + i];
                pos += i;
            }
        }
        return pos + 1;
    }
}
