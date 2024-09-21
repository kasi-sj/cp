class LazySegment{
    int seg[];
    int laz[];
    int n;
    LazySegment(int n){
        this.n=n;
        this.seg=new int[4*n];
        this.laz=new int[4*n];
    }

    LazySegment(int arr[]){
        this.n=arr.length;
        this.seg=new int[4*n];
        this.laz=new int[4*n];
        construct(0,0,n-1 , arr);
    }
    private void construct(int ind , int lo , int hi , int[] arr){
        if(lo==hi){
            seg[ind]=arr[lo];
            return ;
        }
        int mid = (lo+hi)/2;
        construct(2*ind+1,lo,mid,arr);
        construct(2*ind+2,mid+1,hi,arr);
        seg[ind]=Math.max(seg[2*ind+1],seg[2*ind+2]);
    }
    
    private int val(int ind , int lo , int hi , int l , int r){
        
        // System.out.println(ind+" "+lo+" "+hi+" "+l+" "+r+" "+laz[ind]);
        int mid = (lo+hi)/2;
        if(laz[ind]!=0){
            seg[ind]=Math.max(laz[ind],seg[ind]);
            if(lo!=hi) {
                laz[2 * ind + 1] = laz[ind];
                laz[2 * ind + 2] = laz[ind];
            }
            laz[ind] = 0;
        }
        if(lo>r||hi<l||lo>hi)return 0;
        if(lo>=l&&hi<=r)return seg[ind];
        int va = 0;
        va=Math.max(va,val(2*ind+1,lo,mid,l,r));
        va=Math.max(va,val(2*ind+2,mid+1,hi,l,r));
        return va;
    }

    private int addRange(int ind , int lo , int hi , int l , int r , int val){
        // System.out.println(ind+" "+lo+" "+hi+" "+l+" "+r+" "+laz[ind]+" --"+val);
        int mid = (lo+hi)/2;
        if(laz[ind]!=0){
            seg[ind]=Math.max(laz[ind],seg[ind]);
            if(lo!=hi) {
                laz[2 * ind + 1] = laz[ind];
                laz[2 * ind + 2] = laz[ind];
            }
            laz[ind] = 0;
        }
        
        if(lo>r||hi<l||lo>hi)return 0;
        if(lo>=l&&hi<=r){
            seg[ind]=Math.max(val,seg[ind]);
            if(lo!=hi){
                laz[2*ind+1]=val;
                laz[2*ind+2]=val;
            }
            return seg[ind];
        }
        int va = seg[ind];
        va=Math.max(va,addRange(2*ind+1,lo,mid,l,r,val));
        va=Math.max(va,addRange(2*ind+2,mid+1,hi,l,r,val));
        seg[ind]=va;
        return va;
    }
    
    public int val(int lo , int hi){
        return val(0,0,n,lo,hi);
    }
    
    public int val(int index){
        return val(0,0,n,index,index);
    }

    public void update(int index ,int val){
        addRange(0,0,n,index,index,val);
    }
    
    public void updateRange(int lo , int hi , int val){
        addRange(0,0,n,lo,hi,val);
    }
}