#include<bits/stdc++.h>
using namespace std;
#define ll long long int
#define MOD 1000000007
#define LOG 21
#define MIN -1000000000
#define MAX 1000000000
#define PRIME 31
struct fenwik
{
    int n;
    int *fen;
    fenwik(int n) : n(n + 1)
    {
        fen = new int[n + 1];
        memset(fen, 0, sizeof(int) * (n + 1));
    }

    void update(int i, int add)
    {
        i++;
        while (i < n)
        {
            fen[i] += add;
            i = i + (i & (-i));
        }
    }

    int sum(int i)
    {
        i++;
        int s = 0;
        while (i > 0)
        {
            s += fen[i];
            i = i - (i & (-i));
        }
        return s;
    }

    int rangeSum(int l, int r)
    {
        return sum(r) - sum(l - 1);
    }

    int lowerBound(int val)
    {
        int sum = 0;
        int pos = 0;
        int bitmask = 1;
        while (bitmask <= n)
            bitmask <<= 1;
        bitmask >>= 1;
        for (int i = bitmask; i > 0; i >>= 1)
        {
            if (pos + i < n && sum + fen[pos + i] < val)
            {
                sum += fen[pos + i];
                pos += i;
            }
        }
        return pos + 1;
    }
};