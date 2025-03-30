import java.util.TreeMap;

class Rolling {
    TreeMap<Integer, Integer> left ;
    TreeMap<Integer, Integer> right ;
    int lsize ;
    int rsize ;
    int k ;
    long lsum ;
    long rsum ;
    void print(){
        System.out.println(left);
        System.out.println(right);
    }
    Rolling(int k){
        this.left = new TreeMap<>();
        this.right = new TreeMap<>();
        this.lsize = 0;
        this.rsize = 0;
        this.k = k;
        this.lsum = 0;
        this.rsum = 0;
    }

    void balance(){
        if(lsize<k){
            while(rsize>0&&lsize<k){
                int min = right.firstKey();
                right.merge(min,-1,Integer::sum);
                if(right.get(min)==0)right.remove(min);
                left.merge(min,1,Integer::sum);
                rsize--;
                lsize++;
                lsum+=min;
                rsum-=min;
            }
        }else{
            while(lsize>k){
                int max = left.lastKey();
                left.merge(max,-1,Integer::sum);
                if(left.get(max)==0)left.remove(max);
                right.merge(max,1,Integer::sum);
                lsize--;
                rsize++;
                lsum-=max;
                rsum+=max;
            }
        }
        // print();
    }
    void insert(int node){
        if(lsize==0){
            left.merge(node,1,Integer::sum);
            lsize++;
            lsum+=node;
            balance();
            return;
        }
        int max = left.lastKey();
        if(node<=max){
            left.merge(node,1,Integer::sum);
            lsize++;
            lsum+=node;
        }else{
            right.merge(node,1,Integer::sum);
            rsize++;
            rsum+=node;
        }
        balance();
    }
    void remove(int node){
        if(left.containsKey(node)){
            left.merge(node,-1,Integer::sum);
            lsize--;
            lsum-=node;
            if(left.get(node)==0)left.remove(node);
        }else if(right.containsKey(node)){
            right.merge(node,-1,Integer::sum);
            rsize--;
            rsum-=node;
            if(right.get(node)==0)right.remove(node);
        }
        balance();
    }
    int kthMaximum(){
        if(lsize>0)return left.lastKey();
        return -1;
    }
    long ksum(){
        return lsum;
    }
}