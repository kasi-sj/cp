import java.util.HashSet;

class BMath {

    static int mod = (int) 1e9 + 7;

    static int maxp = (int) 1e7;
    static int ft[] = new int[(int) 1e7];

    static {
        fact();
    }

    static long lcm(long x, long y) {
        return (x * y) / gcd(x, y);
    }

    static long gcd(long x, long y) {
        if (y == 0)
            return x;
        return gcd(y, x % y);
    }

    static double log2(double x) {
        return log(x) / log(2);
    }

    static double log10(double x) {
        return log(x) / log(10);
    }

    static double log(double x) {
        return Math.log(x);
    }

    static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    static HashSet<Integer> primeFactor(int x) {
        HashSet<Integer> hs = new HashSet<>();
        while (x % 2 == 0) {
            hs.add(2);
            x /= 2;
        }

        for (int i = 3; i * i <= x; i++) {
            while (x % i == 0) {
                hs.add(i);
                x /= i;
            }
        }

        if (x > 2)
            hs.add(x);

        return hs;
    }

    static int power(int a, int b) {
        int ans = 1;
        while (b > 0) {
            if ((b & 1) != 0) {
                ans = (int) ((ans * (long) a) % mod);
            }
            a = (int) ((a * (long) a) % mod);
            b >>= 1;
        }
        return ans;
    }

    static int ncr(int n, int r) {
        int ans = 1;
        int den = 1;
        for (int i = 0; i < r; i++) {
            ans = (int) ((ans * (long) n--) % mod);
            den = (int) ((den * (long) (i + 1)) % mod);
        }
        return (int) ((ans * (long) power(den, mod - 2)) % mod);
    }
    static void fact() {
        if (ft[0] == 1)
            return;
        ft[0] = 1;
        for (int i = 1; i < ft.length; i++) {
            ft[i] = (int) ((i * (long) ft[i - 1]) % mod);
        }
    }


    static int npr(int n, int r) {
        fact();
        int nem = ft[n];
        int den = ft[n - r];
        return (int) ((nem * (long) power(den, mod - 2)) % mod);
    }

    // used to get the number of ways to partition a set of n objects into k non-empty subsets.
    // eg : n = 3 , k = 2 => 3 [1,2] [3] , [1,3] [2] , [2,3] [1]]
    public static int stirlingSecondKind(int n, int k) {
        int[][] S = new int[n + 1][k + 1];
        S[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                S[i][j] = (int) ((j * (long) (S[i - 1][j] % mod) + (S[i - 1][j - 1] % mod)) % mod);
            }
        }
        return S[n][k];
    }
}