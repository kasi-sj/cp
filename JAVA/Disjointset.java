import java.util.Arrays;

class DisjointSet{
    int size[];
    int pare[];
    int edge[];
    DisjointSet(int n){
        this.size = new int[n+1];
        this.pare = new int[n+1];
        this.edge = new int[n+1];
        for(int i = 0 ; i < n+1 ; i++)
            pare[i]=i;
        Arrays.fill(size,1);
    }

    int getparent(int n){
        if(pare[n]==n)return n;
        return pare[n]=getparent(pare[n]);
    }

    boolean insert(int i , int j){
        int pai = getparent(i);
        int paj = getparent(j);
        if(pai==paj){
            edge[pai]++;
            return false;
        }

        if(size[pai]>size[paj]){
            pare[paj] = pai;
            size[pai]+=size[paj];
            edge[pai]+=edge[paj];
            edge[pai]++;
        }else{
            pare[pai] = paj;
            size[paj]+=size[pai];
            edge[paj]+=edge[pai];
            edge[paj]++;
        }
        return true;
    }

}