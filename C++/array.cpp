#include<bits/stdc++.h>
using namespace std;
int main(){
    int aa[10];
    for(int i=0;i<10;i++){
        aa[i] = i+1;
    }
    for(int i=0;i<10;i++){
        cout<<aa[i]<<" ";
    }
    cout<<endl;
    int bb[3][3] ; 
    int p = 1;
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            bb[i][j] = p++;
        }
    }
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            cout<<bb[i][j]<<" ";
        }
        cout<<endl;
    }

    string s = "hello";
    s[0] = 'H';
    for(int i=0;i<s.size();i++){
        cout<<s[i]<<" ";
    }
}