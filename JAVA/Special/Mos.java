import java.util.*;

class Mos {
    /*
     * also known as squareroot decomposition
     */
    public static int[] countOccurrences(int n, int qq, int[] arr, int[][] queries) {
        int ans[] = new int[qq];
        int sqrt = (int)Math.sqrt(n);
        ArrayList<int[]> al = new ArrayList()<>();
        for(int i = 0 ; i < qq ; i++){
            al.add(new int[]{queries[i][0] , queries[i][1] ,queries[i][2], i});
        }
        al.sort((a,b)->{
            if(Integer.compare(a[0]/sqrt,b[0]/sqrt)==0)
            return Integer.compare(a[1],b[1]);
            return Integer.compare(a[0]/sqrt,b[0]/sqrt);
        });
        HashMap<Integer,Integer> hm = new HashMap<>();
        int s = -1;
        int e = -1;
        for(int q[] : al){
            int l = q[0];
            int r = q[1];
            if(s==-1){
                s = l;
                e = l-1;
            }
            while(l<s){
                add(hm,arr[--s]);
            }
            while(s<l){
                remove(hm,arr[s++]);
            }
            while(e<r){
                add(hm,arr[++e]);
            }
            while(e>r){
                remove(hm,arr[e--]);
            }
            ans[q[3]] = hm.getOrDefault(q[2],0);
        }
        return ans;
    }
    private static void add(HashMap<Integer,Integer> hm , int value){
        hm.merge(value,1,Integer::sum);
    }
    private static void remove(HashMap<Integer,Integer> hm , int value){
        hm.merge(value,-1,Integer::sum);
        if(hm.get(value)==0)hm.remove(value);
    }
}