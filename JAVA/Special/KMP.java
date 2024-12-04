
import java.util.ArrayList;

class KMP{

    public static int[] computeLPS(String pat){
        int n = pat.length();
        int lps[] = new int[n];
        for(int i = 1 ; i < n ; i++){
            int j = lps[i-1];
            while(j>0 && pat.charAt(i) != pat.charAt(j)) j = lps[j-1];
            if(pat.charAt(i) == pat.charAt(j)) j++;
            lps[i] = j;
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