#include<bits/stdc++.h>
using namespace std;
#define ll long long int
 
#define MOD 1000000007
#define LOG 21
#define MIN -1000000000
#define MAX 1000000000
 
void start() {
    string s;
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