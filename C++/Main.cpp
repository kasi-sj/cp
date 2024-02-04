#include<bits/stdc++.h>
using namespace std;
#define ll long long int
 
#define MOD 1000000007
#define LOG 21
#define MIN -1000000000
#define MAX 1000000000
#define PRIME 31

using namespace std;

ll power[(int)2e6+2];

void calculatePower(int n){
    power[0] = 1;
    for(int i = 1 ; i <= n ; i++){
        power[i] = (power[i-1]*PRIME)%MOD;
    }
}

void rollingHash(string &s , ll Hash[]){
    int n = s.size();
    Hash[0] = s[0];
    for(int i = 1 ; i < n ; i++){
        Hash[i] = (Hash[i-1]*PRIME + s[i])%MOD;
    }
    return ;
}

ll getHash(int l , int r , ll Hash[]){
    if(l==0)return Hash[r];
    else{
        return (MOD + Hash[r] - (Hash[l-1]*power[r-l+1])%MOD)%MOD;
    }
}

void start() {
    string s;
    cin >> s;
    int n = s.size();
    string r = s;
    reverse(r.begin(),r.end());
    int j = n-1;
    calculatePower(n);
    ll rh1[n];
    ll rh2[n];
    rollingHash(s,rh1);
    rollingHash(r,rh2);
    int ml = 0;
    int st = -1;
    int e = -1;
    for(int i = 0 ; i < n ; i++,j--){
        int lo = 0;
        int hi = i;
        while(lo<=hi){
            int mid = (lo+hi)/2;
            if(j-mid<0){
                hi = mid-1;
            }else{
                int h1 = getHash(i-mid,i,rh1);
                int h2 = getHash(j-mid,j,rh2);
                if(h1==h2){
                    lo = mid+1;
                }else{
                    hi = mid-1;
                }
            }
        }
        if((i+hi)-(i-hi)+1>ml){
            ml = (i+hi)-(i-hi)+1;
            st = (i-hi);
            e = (i+hi);
        }
        lo = 0;
        hi = i;xx
        while(lo<=hi){
            int mid = (lo+hi)/2;
            if(j-1-mid<0){
                hi = mid-1;
            }else{
                int h1 = getHash(i-mid,i,rh1);
                int h2 = getHash(j-1-mid,j-1,rh2);
                if(h1==h2){
                    lo = mid+1;
                }else{
                    hi = mid-1;
                }
            }
        }
        if((i+hi+1)-(i-hi)+1>ml){
            ml = (i+hi+1)-(i-hi)+1;
            st = (i-hi);
        }
    }
    cout << s.substr(st,ml);
}



int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    ifstream inputFile("input.txt");
    if (inputFile.is_open()) {
        cin.rdbuf(inputFile.rdbuf());
    }

    ofstream outputFile("output.txt");
    if (outputFile.is_open()) {
        cout.rdbuf(outputFile.rdbuf());
    }
 
    int n = 1;
    // cin >> n;
    while (n-- > 0) {
        start();
    }
 
    inputFile.close();
    outputFile.close();
    
    return 0;
}