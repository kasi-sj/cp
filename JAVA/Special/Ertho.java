import java.util.*;

class Ertho{
    static boolean erth[];
    static int no = 1299722;
    static ArrayList<Integer> al ;

    static {
        erth = new boolean[no];
        al = new ArrayList<Integer>();
        Arrays.fill(erth,true);
        erth[0]=false;
        erth[1]=false;
        for(int i = 2 ; i*i < no ; i++){
            if(erth[i]){
                for(int j = i*i ; j<no ; j+=i){
                    erth[j]=false;
                }
            }
        }
        for(int i = 0 ; i <no ; i++)
            if(erth[i])
                al.add(i);
    }

    static int nthprime(int k){
        if(k<=al.size())
            return al.get(k-1);
        return -1;
    }
}