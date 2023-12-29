import java.io.*;
import java.util.*;



public class Main {
    static FastReader fr;
    static FastWriter pw;
    static int mod = (int) 998244353;

    static int ncr(int n , int r){
        int ans = 1;
        int den = 1;
        for(int i = 0 ; i < r ; i++){
            ans = (int)((ans*(long)n--)%mod);
            den = (int)((den*(long)(i+1))%mod);
        }
        return (int)((ans*(long)power(den,mod-2))%mod);
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

    static {
        fr = new FastReader();
        pw = new FastWriter();
    }

    static long gcd(long x , long y){
        if(y==0)return x;
        return gcd(y,x%y);
    }

    public static void main(String[] args) {
        int t = fr.nextInt();
        while (t-- > 0) {
            start();
        }
        pw.close();
    }
    //4->7

    static void primeFactor(int x , HashMap<Integer,Integer> hm){
        while(x%2==0){
            hm.merge(2,1,Integer::sum);
            x/=2;
        }

        for(int i = 3 ; i*i <= x ; i++){
            while (x%i==0){
                hm.merge(i,1,Integer::sum);
                x/=i;
            }
        }

        if(x>2)
            hm.merge(x,1,Integer::sum);

        return ;
    }

    private static void start() {
        int n = fr.nextInt();
        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++)arr[i] = fr.nextInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int p = 0;
        while(p<n&&arr[p]!=min){
            p++;
        }
        if(issorted(arr, p)){
            int ans = 0;
            ans+=p;
            pw.println(ans);
        }else{
            pw.println(-1);
        }
    }

    private  static boolean issorted(int arr[] , int start){
        for(int i = start ; i < arr.length-1 ; i++){
            if(arr[i]>arr[i+1])return false;
        }
        return true;
    }
}
class FastWriter {
    PrintWriter pw;

    public FastWriter() {
        File file = new File("output.txt");
        if (file.exists()) {
            try {
                pw = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            pw = new PrintWriter(System.out);
        }
    }

    void spreadArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    void spreadArray(long[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    void spreadArray(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    void spreadList(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            print(list.get(i) + " ");
        }
        pw.println();
    }

    void println(Object o) {
        pw.println(o);
    }

    void print(Object o) {
        pw.print(o);
    }

    void printf(String format, Object... args) {
        pw.printf(format, args);
    }

    void close() {
        pw.close();
    }
}

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        File file = new File("input.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}


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