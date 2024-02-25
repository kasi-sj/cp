#include<bits/stdc++.h>
using namespace std;
#define ll long long int
#define MOD 1000000007
#define LOG 21
#define MIN -1000000000
#define MAX 1000000000
#define PRIME 31

struct segment{
    int n;
    int *seg;
    int *lazy;
    int *arr;

    void build(int ind , int low , int high) {
        if(low==high){
            seg[ind] = arr[low];
            lazy[ind] = INT_MIN;
            return;
        }
        int mid = (low+high)/2;
        build(ind*2+1,low,mid);
        build(ind*2+2,mid+1,high);
        seg[ind] = max(seg[2*ind+1] , seg[2*ind+2]);
    }

    segment(int n , int ar[]) : n(n) {
        seg = new int[4*n];
        lazy = new int[4*n];
        arr = ar;
        build(0,0,n-1);
    }

    int query(int ind , int low , int high , int l , int r){
        if(lazy[ind]!=0){
            seg[ind] = max(seg[ind] , lazy[ind]);
            if(low!=high){
                lazy[2*ind+1] = max(lazy[2*ind+1] , lazy[ind]);
                lazy[2*ind+2] = max(lazy[2*ind+2],lazy[ind]);
            }
            lazy[ind] = INT_MIN:
        }
        if(low>=l && high<=r){
            return seg[ind];
        }
        if(high < l || low>r || low>high) return INT_MIN;
        int mid = (low+high)/2;
        int left = query(2*ind+1,low,mid,l,r);
        int right = query(2*ind+2,mid+1,high,l,r);
        return max(left,right);
    }

    void update(int ind , int low , int high , int l , int r, int val){
        if(lazy[ind]!=INT_MIN){
            seg[ind] = max(lazy[ind],seg[ind]);
            if(low!=high){
                lazy[2*ind+1] = max(lazy[2*ind+1],lazy[ind]);
                lazy[2*ind+2] = max(lazy[2*ind+2],lazy[ind]);
            }
            lazy[ind] = INT_MIN;
        }
        if(r<low || l>high || low>high)return;
        if(low>=l && high<=r){
            seg[ind] = max(seg[ind] , val);
            if(low!=high){
                lazy[2*ind+1] = max(lazy[2*ind+1],val);
                lazy[2*ind+2] = max(lazy[2*ind+2],val);
            }
            return;
        }
        int mid = (low+high)/2;
        update(2*ind+1,low,mid,l,r,val);
        update(2*ind+2,mid+1,high,l,r,val);
        seg[ind] = max(seg[2*ind+1] , seg[2*ind+2]);
    }

    void update(int ind , int low , int high , int node , int val){
        update(ind, low , high , node, node , val);
    }
};