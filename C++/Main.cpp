#include<bits/stdc++.h>
using namespace std;
#define ll long long int
 
#define MOD 1000000007
#define LOG 21
#define MIN -1000000000
#define MAX 1000000000

void start() {
    int n;
    cin >> n;
    vector<pair<int,pair<int,int>>> v;

    for(int i = 0; i < n; i++) {
        int fr, to;
        cin >> fr >> to ;
        v.push_back({i,{fr,to}});
    }

    sort(v.begin(), v.end(), [](pair<int,pair<int,int>> a, pair<int,pair<int,int>> b) {
        if(a.second.second == b.second.second){
            return b.second.first < a.second.first;
        }
        return a.second.second < b.second.second;
    });
    set<int ,less<int>> s;
    int ans1[n];


    for(auto a : v){
        int ind = a.first;
        int fr = a.second.first;
        int to = a.second.second;
        auto ub = s.lower_bound(fr);//ceil
        if(ub!=s.end()){
            ans1[ind]=1;
        }else{
            ans1[ind]=0;
        }
        s.insert(fr);
    }
    int ans2[n];
    sort(v.begin(), v.end(), [](pair<int,pair<int,int>> a, pair<int,pair<int,int>> b) {
        if(a.second.first == b.second.first){
            return a.second.second > b.second.second;
        }
        return a.second.first < b.second.first;
    });

    s = set<int>();
    for(auto a : v){
        int ind = a.first;
        int fr = a.second.first;
        int to = a.second.second;
        auto ub = s.lower_bound(to);//ceil
        if(ub!=s.end()){
            ans2[ind]=1;
        }else{
            ans2[ind]=0;
        }
        s.insert(to);
    }
    for(int i : ans1){
        cout << i << " " ;
    }
    cout << endl;
    
    for(int i : ans2){
        cout << i << " " ;
    }
    cout << endl;
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