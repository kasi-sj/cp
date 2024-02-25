#include <bits/stdc++.h>
using namespace std;
#define ll long long int

#define MOD 1000000007
#define LOG 21
#define MIN -1000000000
#define MAX 1000000000
#define PRIME 31

using namespace std;

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


void start()
{
    int n;
    cin >> n;
    int k;
    cin >> k;
    fenwik fn(n);
    for (int i = 0; i < n; i++)
    {
        fn.update(i, 1);
    }
    int p = 0;
    for (int i = 0; i < n; i++)
    {
        p = (p + k) % (n - i);
        int lo = 0;
        int hi = n-1;
        int ans = -1;
        while(lo<=hi){
            int mid = (lo+hi)>>1;
            if(fn.sum(mid)>p+1)hi = mid-1;
            else if(fn.sum(mid)==p+1){
                ans = mid;
                hi = mid-1;
            }
            else lo = mid+1;
        }
        cout << fn.lowerBound(p+1) << " ";
        fn.update(ans,-1);
    }
}


int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    ifstream inputFile("input.txt");
    if (inputFile.is_open())
    {
        cin.rdbuf(inputFile.rdbuf());
    }

    ofstream outputFile("output.txt");
    if (outputFile.is_open())
    {
        cout.rdbuf(outputFile.rdbuf());
    }

    int n = 1;
    // cin >> n;
    while (n-- > 0)
    {
        start();
    }

    inputFile.close();
    outputFile.close();

    return 0;
}