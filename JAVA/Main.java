import java.io.*;
import java.util.*;

public class Main {
    static FastReader fr;
    static FastWriter pw;
    static int mod = (int) 1e9 + 7;

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

    static int gcd(int x , int y){
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

    private static void start() {
        int n = fr.nextInt();
        String s = fr.nextLine();
        ArrayList<int[]> adj = new ArrayList<>();
        adj.add(new int[2]);
        boolean leaf[] = new boolean[n+1];
        for(int i = 0 ; i < n ; i++){
            int edg[] = new int[2];
            edg[0] = fr.nextInt();
            edg[1] = fr.nextInt();
            if(edg[0]==0&&edg[1]==0)leaf[i+1]=true;
            else leaf[i+1]=false;
            adj.add(edg);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return Integer.compare(a[1],b[1]);
        });

        pq.add(new int[]{1,0});
        while(!pq.isEmpty()){
            int cur[] = pq.remove();
            if(leaf[cur[0]]){
                pw.println(cur[1]);
                return;
            }
            char c = s.charAt(cur[0]-1);
            if(adj.get(cur[0])[0]!=0){
                pq.add(new int[]{adj.get(cur[0])[0] , cur[1]+(c=='L'?0:1)});
            }
            if(adj.get(cur[0])[1]!=0){
                pq.add(new int[]{adj.get(cur[0])[1] , cur[1]+(c=='R'?0:1)});
            }
        }
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