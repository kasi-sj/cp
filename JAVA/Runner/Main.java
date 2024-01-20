import java.io.*;
import java.util.*;

public class Main {
    static int mod = (int) 998244353;
    
    // static int mod = (int) 1e9+7;
    public static void main(String[] args) {
        int t = 1;
        while (t-- > 0) {
            start();
        }
        io.close();
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

    private static void start() {
        int n = io.nextInt();
        int m = io.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> radj = new ArrayList<>();
        for(int i = 0 ; i <= n ; i++){
            adj.add(new ArrayList<>());
            radj.add(new ArrayList<>());
        }
        for(int i = 0 ; i < m ; i++){
            int fr = io.nextInt();
            int to = io.nextInt();
            adj.get(fr).add(to);
            radj.get(to).add(fr);
        }
        Stack<Integer> st = new Stack<>();
        
        int vis[] = new int[n+1];
        int fis[] = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            if(vis[i]==0)
            dfs(adj,i,st,vis);
        }
        Arrays.fill(vis,0); 
        int comp = 0;   
        while(!st.isEmpty()){
            int node = st.pop();
            if(fis[node]==0){
                comp++;
                fis[node] = comp;
                scc(radj,node,fis,comp);
            }
        }
        io.println(comp);
        for(int i = 1 ; i <= n ; i++)io.print(fis[i]+" ");
        io.println("");
    }

    private static  void dfs(ArrayList<ArrayList<Integer>> adj , int node , Stack<Integer> st , int vis[]){
        vis[node] = 1;
        for(int ad : adj.get(node)){
            if(vis[ad]==0)
            dfs(adj,ad,st,vis);
        }
        st.add(node);
    }

    private static void scc(ArrayList<ArrayList<Integer>> radj , int node , int vis[] , int par){
        for(int ad : radj.get(node)){
            if(vis[ad]==0){
                vis[ad] = par;
                scc(radj,ad,vis,par);
            }
        }
    }
}

class Graph {

    
    /*
     *  point 1 : maximum weight means go for toposort
     *            only use toposort if there is no cycle
     *  point 2 : minumum weight means go for dijkstra
     */

    // create a AdjacencyList
    public static ArrayList<HashMap<Integer,Integer>> getAdj(ArrayList<int[]> edges , int n , int m){
        ArrayList<HashMap<Integer,Integer>> al = new ArrayList<>();
        for(int i = 0 ;  i <= n ; i++)al.add(new HashMap<>());
        for(int i = 0 ; i  < m ; i++){
            int fr = edges.get(i)[0];
            int to = edges.get(i)[1];
            int cs = edges.get(i)[2];
            al.get(fr).merge(to,cs,Integer::min);
        }
        return al;
    }

    // running loop for n times to find negative cycle 
    public static boolean bellmanFord(ArrayList<int[]> edges , int n , int m , long cst[] , int par[] , boolean fault[]){
        for(int t = 0 ; t < n-1 ; t++){
            for(int i = 0 ; i < m ; i++){
                int fr = edges.get(i)[0];
                int to = edges.get(i)[1];
                int cs = edges.get(i)[2];
                if(cst[to] > cst[fr]+cs){
                    cst[to] = cst[fr]+cs;
                    par[to] = fr;
                }
            }
        }
        long temp[] = cst.clone();
        for(int i = 0 ; i < m ; i++){
            int fr = edges.get(i)[0];
            int to = edges.get(i)[1];
            int cs = edges.get(i)[2];
            if(cst[to] > cst[fr]+cs){
                cst[to] = cst[fr]+cs;
                par[to] = fr;
            }
        }
        boolean ans = false;
        for(int i = 1 ; i <= n ; i++){
            if(cst[i] != temp[i]){
                fault[i] = true;
                ans = true;
            }
        }
        return ans;
    }

    //dijkstra to find shortest path from source to target
    public static long dijkstra(ArrayList<HashMap<Integer,Integer>> al , int from , int target , long cst[] , int par[]){
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b)->Long.compare(a[0],b[0]));
        pq.add(new long[]{0,from});
        cst[from] = 0;
        par[from] = -1;
        while(!pq.isEmpty()){
            long cur[] = pq.remove();
            long cost = cur[0];
            int fr = (int)cur[1];
            if(fr==target)return cost;
            if(cst[fr]<cost)continue;
            for(Map.Entry<Integer,Integer> en : al.get(fr).entrySet()){
                long tcst = cost+en.getValue();
                int to = en.getKey();
                if(cst[to]>tcst){
                    cst[to] = tcst;
                    par[to] = fr;
                    pq.add(new long[]{tcst,to});
                }
            }
        }
        return -1;
    }

    // cycle in directed graph aswell as undirected
    // return -1 if no cycle
    // return -2 if cycle
    // ans is the cycle
    public static int cycle(int node , ArrayList<ArrayList<Integer>> adj , int vis[] , int pat[] , ArrayList<Integer> ans , int par){
        if(vis[node]==1&&pat[node]==1){
            ans.add(node);
            return node;
        }
        else if(vis[node]==1)return -1;
        vis[node] = 1;
        pat[node] = 1;
        for(int e : adj.get(node)){
            // parent can be  enabled if need a cycle more then 2 elements
            
            // if(e==par)continue; 
            int from = cycle(e,adj,vis,pat,ans,node);
            if(from==-1)continue;
            if(from==-2)return -2;
            if(from==node){
                ans.add(node);
                return -2;
            }else{
                ans.add(node);
                return from;
            }
        }
        pat[node] = 0;
        return -1;
    }

    // khan's algorithm for finding toposort of a graph
    // return null if not found a valid toposort
    // return list of size n if found a valid topo sort
    public static ArrayList<Integer> TopoSort(ArrayList<int[]> ed , int n , int m){
        int incomming[] = new int[n+1];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i <=n ; i++)adj.add(new ArrayList<>());
        for(int i = 0 ; i < m ; i++){
            adj.get(ed.get(i)[0]).add(ed.get(i)[1]);
            incomming[ed.get(i)[1]]++;
        }
        Queue<Integer> qu = new LinkedList<>();
        for(int i = 1 ; i <= n ; i++)if(incomming[i]==0)qu.add(i);
        ArrayList<Integer> ans = new ArrayList<>();
        while(!qu.isEmpty()){
            int cur = qu.remove();
            for(int e : adj.get(cur)){
                incomming[e]--;
                if(incomming[e]==0)qu.add(e);
            }
            ans.add(cur);
        }
        if(ans.size()==n)return ans;
        return null;
    }


    // used for query releated problem
    static class BinaryLifting {
        int n;
        int parent[];
        int table[][];
        BinaryLifting(int n , int parent[]){
            this.n = n;
            this.parent = parent;
            this.table = new int[n+1][32];
            for(int i = 1 ; i <= n ; i++){
                table[i][0] = parent[i];
            }
            for(int i = 1 ; i <= 31 ; i++){
                for(int j = 1 ; j <= n ; j++){
                    table[j][i] = table[table[j][i-1]][i-1];
                }
            }
        }
    
        int get(int i , int k){
            int ans = i;
            for(int b = 31 ; b >= 0 ; b--){
                if((k&(1<<b))!=0)ans = table[ans][b];
            }
            return ans;
        }
    }

    static class SCC {
        private static  void dfs(ArrayList<ArrayList<Integer>> adj , int node , Stack<Integer> st , int vis[]){
            vis[node] = 1;
            for(int ad : adj.get(node)){
                if(vis[ad]==0)
                dfs(adj,ad,st,vis);
            }
            st.add(node);
        }
    
        private static void scc(ArrayList<ArrayList<Integer>> radj , int node , int vis[] , int par){
            for(int ad : radj.get(node)){
                if(vis[ad]==0){
                    vis[ad] = par;
                    scc(radj,ad,vis,par);
                }
            }
        }
    
        public static void start(ArrayList<ArrayList<Integer>> adj , ArrayList<ArrayList<Integer>> radj) {
            int n = adj.size()-1;
            Stack<Integer> st = new Stack<>();
            
            int vis[] = new int[n+1];
            for(int i = 1 ; i <= n ; i++){
                if(vis[i]==0)
                dfs(adj,i,st,vis);
            }
            Arrays.fill(vis,0); 
            int comp = 0;
            // ArrayList<Integer> ans = new ArrayList<>();
            while(!st.isEmpty()){
                int node = st.pop();
                if(vis[node]==0){
                    comp++;
                    vis[node] = comp;
                    scc(radj,node,vis,comp);
                    // ans.add(comp);
                }
            }
            io.println(comp);
            io.spreadArray(vis, 1);
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

    static void spreadArray(int[] arr , int s) {
        for (int i = s; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    static void spreadArray(long[] arr , int s) {
        for (int i = s; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    static void spreadArray(double[] arr , int s) {
        for (int i = s; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    static void spreadList(List<?> list , int s) {
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

class node{
    node[] link;
    boolean flag ;

    int val;
    node(){
        this.flag=false;
        this.link = new node[26];
        this.val = -1;
    }

    node get(char c){
        return link[c-'a'];
    }

    boolean has(char c){
        return link[c-'a']!=null;
    }

    void set(char c){
        link[c-'a']=new node();
    }
}


class Trie{
    node root;

    Trie(){
        this.root=new node();
    }

    void insert(String s){
        node temp = root;
        for(int i = 0 ; i < s.length() ; i++){
            if(!temp.has(s.charAt(i)))temp.set(s.charAt(i));
            temp=temp.get(s.charAt(i));
        }
        temp.flag=true;
    }

    int find(String s){
        node temp = root;
        for(int i = 0 ; i < s.length() ; i++){
            if(!temp.has(s.charAt(i)))return -1;
            temp=temp.get(s.charAt(i));
        }
        return temp.val;
    }

    long noof(String s, int i, long[] dp, long MOD) {
        int h = i;
        int size = s.length();
        if (i >= size) return 1;
        if (dp[h] != -1) return dp[h];
        node temp = root;
        long ans = 0;
        while (i < size) {
            if (!temp.has(s.charAt(i))) {
                return dp[h] = ans;
            }
            temp = temp.get(s.charAt(i));
            if (temp.flag) ans += noof(s, i + 1, dp, MOD) ;
            ans %= MOD;
            i++;
        }
        return dp[h] = ans;
    }
}