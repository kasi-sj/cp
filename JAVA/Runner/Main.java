import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int mod = (int) 998244353;
    static int rr[] = new int[]{-1,0,1,0};
    static int cc[] = new int[]{0,1,0,-1};

    // static int mod = (int) 1e9 + 7;
    static HashMap<String,Boolean> hm = new HashMap<>();

    public static void main(String[] args) {
        int t = io.nextInt();
        while (t-- > 0) {
            start();
        }
        io.close();
    }


    private static void start(){
        int n = io.nextInt();
        int arr[][] = new int[n][n];
        int suf[][] = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                arr[i][j] = io.nextInt();
            }
        }
        for(int i = n-1 ; i >= 0 ; i--){
            for(int j = 0 ; j < n ; j++){
                suf[i][j] = arr[i][j];
                if(i+1 < n)
                suf[i][j] += suf[i+1][j];
            }
        }
        int vis[][] = new int[n][n];
        int row[] = new int[n];
        int col[] = new int[n];
        Arrays.fill(row,-1);
        Arrays.fill(col,-1);
        for(int a[] : vis)Arrays.fill(a,-1);
        int p = 1;
        while(true){
            int gone[][] = new int[n][n];
            if(can(row,col,vis,gone,suf,p)){
                p++;
            }else{
                break;
            }
        }
        io.println(p);
    }

    private static boolean can(int row[] , int col[] , int vis[][] , int gon[][] , int suf[][] ,int ele){
        for(int i = 0 ; i < gon.length ; i++){
            for(int j = 0 ; j < gon[0].length ; j++){
                if(suf[i][j] == ele && gon[i][j] == 0){
                    gon[i][j] = 1;
                    if(row[i] == -1 && col[j] == -1){
                        row[i] = ele;
                        col[j] = ele;
                        return true;
                    }else if(row[i] == -1){
                        if(can(row,col,vis,gon,suf,col[j])){
                            row[i] = ele;
                            col[j] = ele;
                            return true;
                        }
                    }else if(col[j] == -1){
                        if(can(row,col,vis,gon,suf,row[i])){
                            col[j] = ele;
                            row[i] = ele;
                            return true;
                        }
                    }else{
                        if(can(row,col,vis,gon,suf,row[i]) && can(row,col,vis,gon,suf,col[j])){
                            row[i] = ele;
                            col[j] = ele;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static int shortestPart(String arr[] , int[][] pathVisited){
        Queue<int[]> qu = new LinkedList<>();
        int n = arr.length;
        int m = arr[0].length();
        qu.add(new int[]{0,0});
        int vis[][] = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(pathVisited[i][j] == 1)vis[i][j] = 1;
            }
        }
        vis[n-1][m-1] = 0;
        vis[0][0] = 1;
        int par[][][] = new int[n][m][2];
        int time = 0;
        boolean flag = false;
        while(!qu.isEmpty()){
            int s = qu.size();
            while(s-->0){
                int cur[] = qu.remove();
                int r = cur[0];
                int c = cur[1];
                if(r==n-1 && c==m-1){
                    flag = true;
                    break;
                }
                for(int d = 0 ; d < 4 ; d++){
                    int nr = r + rr[d];
                    int nc = c + cc[d];
                    if(nr >= 0 && nc >= 0 && nr < n && nc < m && vis[nr][nc] == 0 && arr[nr].charAt(nc)=='.'){
                        par[nr][nc] = new int[]{r , c};
                        vis[nr][nc] = 1;
                        qu.add(new int[]{nr,nc});
                    }
                }
            }
            if(flag)break;
            time++;
        }
        qu.clear();
        if(vis[n-1][m-1] == 0)return -1;
        else{
            qu.add(new int[]{n-1 , m-1});
            while(!qu.isEmpty()){
                int cur[] = qu.remove();
                int r = cur[0];
                int c = cur[1];
                int pr = par[r][c][0];
                int pc = par[r][c][1];
                qu.add(new int[]{pr,pc});

                pathVisited[r][c] = 1;
                if(r == 0 && c == 0){
                    break;
                }
            }
            return time;
        }
    }
}

class io {
    static PrintWriter pw;
    static BufferedReader br;
    static StringTokenizer st;

    static {
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

    static {
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

    static String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }

    static long nextLong() {
        return Long.parseLong(next());
    }

    static double nextDouble() {
        return Double.parseDouble(next());
    }

    static String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    static void spreadArray(int[] arr, int s) {
        for (int i = s; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    static void spreadArray(long[] arr, int s) {
        for (int i = s; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    static void spreadArray(double[] arr, int s) {
        for (int i = s; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    static void spreadList(List<?> list, int s) {
        for (int i = s; i < list.size(); i++) {
            print(list.get(i) + " ");
        }
        pw.println();
    }

    static void println(Object o) {
        pw.println(o);
    }

    static void print(Object o) {
        pw.print(o);
    }

    static void close() {
        pw.close();
    }
}