package Special;

import java.util.*;

class SegmentF{
    int arr[];
    HashMap<Integer,Integer> seg[];
    int n;

    SegmentF(int arr[]){
        this.arr = arr;
        this.n = arr.length;
        this.seg = new HashMap[4*n];
        this.n = arr.length;
        query(0,0,n-1);
    }

    void query(int ind , int lo , int hi){
        if(hi==lo){
            seg[ind]=new HashMap<Integer,Integer>();
            seg[ind].merge(arr[lo],1,Integer::sum);
            return ;
        }
        int mid = (lo+hi)/2;
        query(2*ind+1,lo,mid);
        query(2*ind+2,mid+1,hi);
        seg[ind]=new HashMap<Integer,Integer>();
        for(Map.Entry<Integer,Integer> en : seg[2*ind+1].entrySet())
            seg[ind].merge(en.getKey(),en.getValue(),Integer::sum);
        for(Map.Entry<Integer,Integer> en : seg[2*ind+2].entrySet())
            seg[ind].merge(en.getKey(),en.getValue(),Integer::sum);
    }

    int range(int node , int left , int right){
        return frequency(0,0,n-1,left,right,node);
    }

    int frequency(int ind , int lo , int hi , int left, int right , int node){
        if(left<=lo&&hi<=right)
            return seg[ind].getOrDefault(node,0);
        if(left>hi||right<lo)
            return 0;
        int sum = 0;
        int mid = (lo+hi)/2;
        sum+=frequency(2*ind+1,lo,mid,left,right,node);
        sum+=frequency(2*ind+2,mid+1,hi,left,right,node);
        return sum;
    }
}