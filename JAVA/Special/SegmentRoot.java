package Special;

class Root{
    int val;
    int left;
    int right;

    int update;

    boolean leaf;
    Root lroot;
    Root rroot;
    Root(int val,int left,int right){
        this.val=val;
        this.left=left;
        this.right=right;
        this.lroot=null;
        this.rroot=null;
        this.leaf = true;
        this.update = -1;
    }
}

class SegmentRoot{
    int lo ;
    int hi ;

    Root node;
    SegmentRoot(int lo , int hi){
        this.lo = lo;
        this.hi = hi;
        this.node = new Root(0,lo,hi);
    }

    public void add(int l ,int r ,int val){
        addNo(node,lo,hi,l,r,val);
    }

    public int val(int l , int r){
        return value(node,lo,hi,l,r);
    }
    public void setElement(int ind,int val){
        add(ind,ind,val);
    }

    public void setRange(int fr,int to,int val){
        add(fr,to,val);
    }

    public int getRange(int from , int to){
        return val(from,to);
    }

    public int getElement(int ind){
        return val(ind,ind);
    }
    void addNo(Root node , int lo , int hi , int l , int r , int val){
        if(node.leaf)add(node,lo,hi);
        if(node.update!=-1){
            node.val=node.update;
            node.lroot.update=node.update;
            node.rroot.update=node.update;
            node.update=-1;
        }
        if(lo>=l&&hi<=r){
            node.val = Math.max(val,node.val);
            node.lroot.update=node.val;
            node.rroot.update=node.val;
            return;
        }
        if(lo>r||hi<l)return;
        int mid = (lo+hi)/2;
        addNo(node.lroot,lo,mid,l,r,val);
        addNo(node.rroot,mid+1,hi,l,r,val);
        node.val=Math.max(node.lroot.val,  node.rroot.val);
    }

    int value(Root node , int lo , int hi , int l , int r){
        if(node.leaf)add(node,lo,hi);
        if(node.update!=-1){
            node.val=node.update;
            node.lroot.update=node.update;
            node.rroot.update=node.update;
            node.update=-1;
        }
        if(lo>=l&&hi<=r){
            return node.val;
        }

        int mid = (lo+hi)/2;
        int ans = 0;
        if(mid>=l)
            ans=Math.max(ans,value(node.lroot,lo,mid,l,r));
        if(mid+1<=r)
            ans=Math.max(ans,value(node.rroot,mid+1,hi,l,r));
        return ans;
    }
    void add(Root node , int lo , int hi){
        int mid = (lo+hi)/2;
        node.rroot = new Root(0,lo,mid);
        node.lroot = new Root(0,mid+1,hi);
        node.leaf = false;
    }
}