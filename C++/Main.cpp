#include<bits/stdc++.h>
using namespace std;
#define ll long long int
 
#define MOD 1000000007
#define LOG 21
#define MIN -1000000000
#define MAX 1000000000

void start() {
    int n , m;
    cin >> n >> m;
    vector<pair<int , pair<int , int>>> v;
    for(int i = 0 ; i < m ; i++){
        int fr , to , cs;
        cin >> fr >> to >> cs;
        v.push_back({cs,{fr,to}});
    }
    sort(v.begin(),v.end());
    DisjointSet ds(n);
    long ans = 0;
    int p = 0;
    while(n>1&&p<v.size()){
        int p1 = v[p].second.first;
        int p2 = v[p].second.second;
        if(ds.insert(p1,p2)){
            n--;
            ans+=v[p].first;
        }
        p++;
    }
    if(n==1){
        cout << ans << endl;
    }else{
        cout << "IMPOSSIBLE" << endl;
    }
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