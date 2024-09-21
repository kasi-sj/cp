class segment {
    int seg[];
    int n;

    segment(int n) {
        this.n = n;
        this.seg = new int[4 * n];
        construct(0, 0, n - 1);
    }

    private void construct(int ind, int lo, int hi) {
        if (lo == hi) {
            seg[ind] = 0;
            return;
        }
        int mid = (lo + hi) / 2;
        construct(2 * ind + 1, lo, mid);
        construct(2 * ind + 2, mid + 1, hi);
        seg[ind] = Math.max(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    public void set(int ind, int ele) {
        add(0, 0, n-1, ind, ele);
    }

    public int val(int l, int r) {
        return val(0, 0, n-1, l, r);
    }

    public void add(int ind, int lo, int hi, int in, int val) {
        int mid = (lo + hi) / 2;
        if (lo == hi) {
            seg[ind] = val;
            return;
        }
        if (in >= lo && in <= mid)
            add(2 * ind + 1, lo, mid, in, val);
        else
            add(2 * ind + 2, mid + 1, hi, in, val);
        seg[ind] = Math.max(seg[2 * ind + 1], seg[2 * ind + 2]);
    }

    public int val(int ind, int lo, int hi, int l, int r) {
        int mid = (lo + hi) / 2;
        if (lo >= l && hi <= r)
            return seg[ind];
        else if (lo > r || hi < l)
            return 0;
        int va = 0;
        va = Math.max(va, val(2 * ind + 1, lo, mid, l, r));
        va = Math.max(va, val(2 * ind + 2, mid + 1, hi, l, r));
        return va;
    }
}
