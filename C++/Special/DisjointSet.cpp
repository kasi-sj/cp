struct DisjointSet {
    int n;
    int *par;
    int *siz;
    int *edg;
    DisjointSet(int n) : n(n) {
        par = new int[n+1];
        siz = new int[n+1];
        edg = new int[n+1];
        for(int i = 0 ; i < n+1; i++){
            par[i] = i;
            siz[i] = 1;
            edg[i] = 0;
        }
    }

    int getParent(int node){
        if(par[node]==node)return node;
        return par[node] = getParent(par[node]);
    }

    bool insert(int i , int j){
        int p1 = getParent(i);
        int p2 = getParent(j);
        if(p1==p2){
            edg[p1]++;
            return false;
        }
        int s1 = siz[p1];
        int s2 = siz[p2];
        if(s1>s2){
            siz[p1] += siz[p2];
            edg[p1] += 1 + edg[p2];
            par[p2] = p1;
        }else{
            siz[p2] += siz[p1];
            edg[p2] += 1 + edg[p1];
            par[p1] = p2;
        }
        return true;
    }
};