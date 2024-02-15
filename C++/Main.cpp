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
    int n;
    int s;
    cin >> n;
    cin >> s;
    vector<int> v;
    for(int i = 0 ; i < n ; i++){
        int val;
        cin >> val;
        v.push_back(val);
    }
    ll ans  = 0;
    map<ll,int> mp;
    mp[0] = 1;
    ll sum = 0;
    for(int i = 0 ; i < n ; i++){
        sum += v[i];
        if(mp.count(sum-s)!=0){
            ans += mp[sum-s];
        }
        if(mp.count(sum)!=0){
            mp[sum]++;
        }else{
            mp[sum] = 1;
        }
        // for(auto e : mp){
        //     cout << e.first << " "<< e.second << "-";
        // }
        // cout << endl;
        // cout << sum << " " << ans << endl;
    }
    cout << ans << endl;
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