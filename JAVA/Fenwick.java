class Fenwick{
    int n;
    int arr[];
    int org[];
    Fenwick(int n){
        this.n = n+1;
        this.arr = new int[n+1];
        this.org = new int[n+1];
    }

    void add(int i , int e){
        i++;
        while(i<n){
            arr[i]+=e;
            i+=(i&(-i));
        }
    }

    int sum(int i){
        i++;
        int sum = 0;
        while(i>0){
            sum+=arr[i];
            i-=(i&(-i));
        }
        return sum;
    }

    int range(int fr , int to){
        return sum(to)-sum(fr-1);
    }

    void update(int i , int e){
        i++;
        int up = e-org[i];
        org[i]=e;
        add(i-1,up);
    }
}