import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class BMath{


    static int mod = (int)1e9+7;

    static int maxp = (int)1e7;
    static int ft[] = new int[(int)1e7];

    static {
        fact();
    }
    static long lcm(long x , long y){
        return (x*y)/gcd(x,y);
    }
    static long gcd(long x , long y){
        if(y==0)return x;
        return gcd(y,x%y);
        
    }

    static double log2(double x){
        return log(x)/log(2);
    }

    static double log10(double x){
        return log(x)/log(10);
    }

    static double log(double x){
        return Math.log(x);
    }

    static boolean isPrime(int x){

        if(x<=1)return false;
        if(x==2)return true;
        for(int i = 2 ; i*i <= x ; i++){
            if(x%i==0)return false;
        }
        return true;
    }

    static HashSet<Integer> primeFactor(int x){
        HashSet<Integer> hs = new HashSet<>();
        while(x%2==0){
            hs.add(2);
            x/=2;
        }

        for(int i = 3 ; i*i <= x ; i++){
            while (x%i==0){
                hs.add(i);
                x/=i;
            }
        }

        if(x>2)
            hs.add(x);

        return hs;
    }

    static int power(int a , int b){
        int ans = 1;
        while(b>0){
            if((b&1)!=0){
                ans = (int)((ans*(long)a)%mod);
            }
            a = (int)((a*(long)a)%mod);
            b>>=1;
        }
        return ans;
    }


    static void fact(){
        if(ft[0]==1)return ;
        ft[0]=1;
        for(int i = 1 ; i < ft.length ; i++){
            ft[i]=(int)((i*(long)ft[i-1])%mod);
        }
    }
    static int ncr(int n , int r){
        int ans = 1;
        int den = 1;
        for(int i = 0 ; i < r ; i++){
            ans = (int)((ans*(long)n--)%mod);
            den = (int)((den*(long)(i+1))%mod);
        }
        return (int)((ans*(long)power(den,mod-2))%mod);
    }

    static int npr(int n , int r){
        fact();
        int nem = ft[n];
        int den = ft[n-r];
        return (int)((nem*(long)power(den,mod-2))%mod);
    }
}