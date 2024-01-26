#include<bits/stdc++.h>
using namespace std;
#define ll long long int
 
#define MOD 1000000007
#define LOG 21
#define MIN -1000000000
#define MAX 1000000000

using namespace std;

int vis[501];
int par[501];
int adj[501][501];
int sadj[501][501];

bool reachable(int n){
    memset(vis, 0, sizeof(vis));
    queue<int> q;
    q.emplace(1);
    par[1]=0;
    vis[1]=1;
    while(!q.empty()){
        int cur = q.front();
        q.pop();
        for(int i = 1 ; i <= n ; i++){
            if(adj[cur][i]!=0&&vis[i]==0){
                vis[i] = 1;
                q.emplace(i);
                par[i] = cur;
            }
        }
    }
    return vis[n]==1;
}

void start() {
    int n , m;
    cin >> n >> m;
    for(int i = 0 ; i < m ; i++){
        int fr , to;
        cin >> fr >>to;
        adj[fr][to]++;
        adj[to][fr]++;
        sadj[to][fr]++;
        sadj[fr][to]++;
    }
    int cnt = 0;
    while(reachable(n)){
        for(int st = n ; par[st] != 0 ; st = par[st]){
            adj[par[st]][st]--;
            adj[st][par[st]]++;
        }
        cnt++;
    }
    cout << cnt << endl;
    for(int i = 1 ; i <= n ; i++){
        for(int j = 1 ; j <= n ; j++){
            if(vis[i]&&!vis[j]&&sadj[i][j]){
                cout << i << " " << j << endl;
            }
        }
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