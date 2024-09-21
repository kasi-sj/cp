import java.io.*;
import java.util.*;

public class Main {
    // static int mod = (int) 998244353;

    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        int t = io.nextInt();
        while (t-- > 0) {
            start();
        }
        io.close();
    }
    static int ans[][] = new int[3][2];
    private static void start(){
        int n = io.nextInt();
        long m = io.nextLong();
        long arr[] = new long[n];
        long qrr[] = new long[n];
        for(int i = 0 ; i < n ; i++)arr[i] = io.nextLong();
        for(int i = 0 ; i < n ; i++)qrr[i] = io.nextLong();
        ArrayList<long[]> al = new ArrayList<>();
        for(int i = 0 ; i < arr.length ; i++){
            al.add(new long[]{arr[i],qrr[i]});
        }
        Collections.sort(al,(a,b)->Long.compare(a[0],b[0]));
        long ans = 0;

        long cnt1 = al.get(0)[0]*1l*al.get(0)[1];
        if(cnt1 <= m){
            ans = Math.max(ans , cnt1);
        }else{
            long t = m/al.get(0)[0];
            t = Math.min(t, al.get(0)[1]);
            ans = Math.max(ans , t*al.get(0)[0]);
        }
        for(int i = 0 ; i < n-1 ; i++){
            cnt1 = al.get(i)[0]*1l*al.get(i)[1];
            long cnt2 = al.get(i+1)[0]*1l*al.get(i+1)[1];
            if(cnt1 <= m){
                ans = Math.max(ans , cnt1);
            }else{
                long t = m/al.get(i)[0];
                t = Math.min(t, al.get(i)[1]);
                ans = Math.max(ans , t*al.get(i)[0]);
            }
            if(cnt2 <= m){
                ans = Math.max(ans , cnt2);
            }else{
                long t = m/al.get(i+1)[0];
                t = Math.min(t, al.get(i+1)[1]);
                ans = Math.max(ans , t*al.get(i+1)[0]);
            }
            if(al.get(i+1)[0] - al.get(i)[0] != 1)continue;
            long nm = m;
            if(nm <= cnt1){
                long t = nm/al.get(i)[0];
                t = Math.min(t, al.get(i)[1]);
                nm -= t*al.get(i)[0];
                long an = al.get(i+1)[1];
                long min = Math.min(t,an);
                nm -= Math.min(nm , min);
                ans = Math.max(ans , m-nm);
            }else {
                nm -= cnt1;
                long t = nm/al.get(i+1)[0];
                t = Math.min(t, al.get(i+1)[1]);
                nm -= t*al.get(i+1)[0];
                long an = al.get(i)[1];
                long min = Math.min(al.get(i+1)[1] - t , an);
                nm -= Math.min(nm , min);
                ans = Math.max(ans , m-nm);
            }
            // io.println(ans);
        }
        io.println(ans);
    }
    private static long sum(int l , int r , long[] pre){
        if(l==0)return pre[r];
        return pre[r]-pre[l-1];
    }

    private static boolean f(int a[][] , int vis , int i , long req ,   int dp[][]){
        if((vis&(1<<0))!=0&&
            (vis&(1<<1))!=0&&
            (vis&(1<<2))!=0)
        return true;
        if(i>=a[0].length)return false;
        if(dp[i][vis]==1)return false;
        for(int t = 0 ; t < 3 ; t++){
            if((vis&(1<<t))==0){
                long sum = 0;
                for(int ind = i ; ind < a[0].length ; ind++){
                    sum+=a[t][ind];
                    if(sum >= req){
                        if(f(a,vis|(1<<t) , ind+1 , req , dp)){
                            ans[t] = new int[]{i+1,ind+1};
                            return true;
                        }else{
                            break;
                        }
                    }
                }
            }
        }
        dp[i][vis] = 1;
        return false;
    }

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        int j = n - 1;
        while (nums[j] <= nums[i]) {
            j--;
        }
        swap(nums, i, j);
        reverse(nums, i + 1, n - 1);
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
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