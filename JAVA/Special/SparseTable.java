class SparseTable{
    int n;
    int arr[];
    int table[][];
    int log;
    int alog[];
    
    SparseTable(int arr[]) {
        
        this.arr = arr;
        this.n = arr.length;
        this.log = (int) Math.ceil(Math.log(n) / Math.log(2))+1;
        this.table = new int[n][log];
        this.alog = new int[n+1];
        alog[1]=0;
        for(int i = 2 ; i <= n ; i++){
            alog[i]=alog[i/2]+1;
        }
        for(int i = 0 ; i < n ; i++){
            table[i][0] = arr[i];
        }
        for(int k = 1 ; k <= log ; k++){
            for(int i = 0 ; i+(1<<k) -1 < n ; i++){
                table[i][k] = Math.min(table[i][k-1],table[i+(1<<(k-1))][k-1]);

            }
        }
    }

    int minimum(int l , int r){
        l--;
        r--;
        int n = r-l+1;
        int k = alog[n];
        return Math.min(table[l][k],table[r-(1<<k)+1][k]);
    }
}


