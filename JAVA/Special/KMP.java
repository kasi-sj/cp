
import java.util.ArrayList;

class KMP{

    public static int[] computeLPS(String pat){
        int n = pat.length();
        int lps[] = new int[n];
        lps[0] = 0;
        int i = 1;
        int pre = 0;
        while( i < n){
            if(pat.charAt(i) == pat.charAt(pre)){
                pre++;
                lps[i] = pre;
                i++;
            }else{
                if(pre!=0){
                    pre = lps[pre-1];
                }else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static ArrayList<Integer> search(String txt , String pat){
        int n = txt.length();
        int m = pat.length();
        int lps[] = computeLPS(pat);
        int i = 0;
        int j = 0;
        ArrayList<Integer> al = new ArrayList<>();
        while(i<n){
            if(txt.charAt(i)==pat.charAt(j)){
                i++;
                j++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
            if(j==m){
                al.add(i-m);
                j = lps[j-1];
            }
        }
        return al;
    }
    
}