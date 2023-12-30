package Runner;
import java.io.*;
import java.util.*;



class P{
    long x , y;
    P(long x , long y){
        this.x = x;
        this.y = y;
    }

    static P add(P a , P b){
        return new P(a.x+b.x,a.y+b.y);
    }

    static P sub(P a , P b){
        return new P(a.x-b.x,a.y-b.y);
    }

    static long cross(P a , P b){
        return a.x*b.y-a.y*b.x;
    }
}


public class Main {
    static FastReader fr;
    static FastWriter pw;
    static int mod = (int) 998244353;
    // static int mod = (int) 1e9+7;

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

    private static void start() {
        P p1 , p2 , p3;
        p1 = new P(fr.nextInt(),fr.nextInt());
        p2 = new P(fr.nextInt(),fr.nextInt());
        p3 = new P(fr.nextInt(),fr.nextInt());
        p2 = P.sub(p2,p1);
        p3 = P.sub(p3,p1);
        long cross = P.cross(p2,p3);
        if(cross==0){
            pw.println("TOUCH");
        }else if(cross>0){
            pw.println("LEFT");
        }else{
            pw.println("RIGHT");
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