import java.io.*;
import java.util.*;

class KMP {

    public static int[] computeLPS(String pat) {
        int n = pat.length();
        int lps[] = new int[n];
        lps[0] = 0;
        int i = 1;
        int pre = 0;
        while (i < n) {
            if (pat.charAt(i) == pat.charAt(pre)) {
                pre++;
                lps[i] = pre;
                i++;
            } else {
                if (pre != 0) {
                    pre = lps[pre - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static int[] search(String txt, String pat) {
        int n = txt.length();
        int m = pat.length();
        int lps[] = computeLPS(pat);
        int i = 1;
        int j = 0;
        int l = 1;
        int dir = 1;
        int c = 0;
        int match = 1;
        ArrayList<Integer> al = new ArrayList<>();
        while (true) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i += dir;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i += dir;
                }
            }
            if (j == m) {
                match++;
                j = lps[j - 1];
            }
            if (i == j && dir == 1)
                break;
            if (i == txt.length()) {
                dir = -1;
                i = txt.length() - 1;
            }
            if (i == 0) {
                dir = 1;
                i = 0;
            }
            l++;
        }
        return new int[] { match, l };
    }

}

public class Main {
    // static int mod = (int) 998244353;

    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        int t = 1;
        while (t-- > 0) {
            start();
        }
        io.close();
    }

    private static void start() {
        int n = io.nextInt();
        int m = io.nextInt();
        int k = io.nextInt();
        int adj[][] = new int[n][m];
        // System.out.println(n+" "+m);
        for (int i = 0; i < k; i++) {
            int fr = io.nextInt() - 1;
            int to = io.nextInt() - 1;
            adj[fr][to] = 1;
        }
        int vis[] = new int[m];
        Arrays.fill(vis, -1);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int gon[] = new int[m];
            if (can(i, vis, adj, gon)) {
                cnt++;
            }
        }
        io.println(cnt);
        for (int i = 0; i < m; i++) {
            if (vis[i] != -1) {
                io.println((vis[i] + 1) + " " + (i + 1));
            }
        }
    }

    private static int maximumBipartateMatching(int adj[][]) {
        int n = adj.length;
        int m = adj[0].length;
        int vis[] = new int[m];
        Arrays.fill(vis, -1);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int gon[] = new int[m];
            if (can(i, vis, adj, gon)) {
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean can(int node, int vis[], int adj[][], int gon[]) {
        for (int j = 0; j < adj[0].length; j++) {
            if (adj[node][j] == 1 && gon[j] == 0) {
                gon[j] = 1;
                if (vis[j] == -1) {
                    vis[j] = node;
                    return true;
                } else if (can(vis[j], vis, adj, gon)) {
                    vis[j] = node;
                    return true;
                }
            }
        }
        return false;
    }

    static int power(int a, int b) {
        int ans = 1;
        while (b > 0) {
            if ((b & 1) != 0) {
                ans = (int) ((ans * (long) a) % mod);
            }
            a = (int) ((a * (long) a) % mod);
            b >>= 1;
        }
        return ans;
    }
}

class Graph {

    /*
     * AdjacencyList
     */
    public static ArrayList<HashMap<Integer, Integer>> getAdj(ArrayList<int[]> edges, int n, int m) {
        ArrayList<HashMap<Integer, Integer>> al = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            al.add(new HashMap<>());
        for (int i = 0; i < m; i++) {
            int fr = edges.get(i)[0];
            int to = edges.get(i)[1];
            int cs = edges.get(i)[2];
            al.get(fr).merge(to, cs, Integer::min);
        }
        return al;
    }

    /*
     * BellmanFord (Negative cycle )
     * running loop for n times to find negative cycle
     */
    public static boolean bellmanFord(ArrayList<int[]> edges, int n, int m, long cst[], int par[], boolean fault[]) {
        for (int t = 0; t < n - 1; t++) {
            for (int i = 0; i < m; i++) {
                int fr = edges.get(i)[0];
                int to = edges.get(i)[1];
                int cs = edges.get(i)[2];
                if (cst[to] > cst[fr] + cs) {
                    cst[to] = cst[fr] + cs;
                    par[to] = fr;
                }
            }
        }
        long temp[] = cst.clone();
        for (int i = 0; i < m; i++) {
            int fr = edges.get(i)[0];
            int to = edges.get(i)[1];
            int cs = edges.get(i)[2];
            if (cst[to] > cst[fr] + cs) {
                cst[to] = cst[fr] + cs;
                par[to] = fr;
            }
        }
        boolean ans = false;
        for (int i = 1; i <= n; i++) {
            if (cst[i] != temp[i]) {
                fault[i] = true;
                ans = true;
            }
        }
        return ans;
    }

    /*
     * point 1 : maximum weight means go for toposort
     * only use toposort if there is no cycle
     * eg : collecting maximum no of coins -> dijistra will take more time
     * point 2 : minumum weight means go for dijkstra
     * eg : shortest path problems
     */

    /*
     * Dijkstra (Shortest Path Algorithm)
     * To find shortest path from source to target
     */
    public static long dijkstra(ArrayList<HashMap<Integer, Integer>> al, int from, int target, long cst[], int par[]) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.add(new long[] { 0, from });
        cst[from] = 0;
        par[from] = -1;
        while (!pq.isEmpty()) {
            long cur[] = pq.remove();
            long cost = cur[0];
            int fr = (int) cur[1];
            if (fr == target)
                return cost;
            if (cst[fr] < cost)
                continue;
            for (Map.Entry<Integer, Integer> en : al.get(fr).entrySet()) {
                long tcst = cost + en.getValue();
                int to = en.getKey();
                if (cst[to] > tcst) {
                    cst[to] = tcst;
                    par[to] = fr;
                    pq.add(new long[] { tcst, to });
                }
            }
        }
        return -1;
    }

    /*
     * khan's algorithm(Topo sort)
     * for finding toposort of a graph
     * return null if not found a valid toposort
     * return list of size n if found a valid topo sort
     */
    public static ArrayList<Integer> TopoSort(ArrayList<int[]> ed, int n, int m) {
        int incomming[] = new int[n + 1];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            adj.get(ed.get(i)[0]).add(ed.get(i)[1]);
            incomming[ed.get(i)[1]]++;
        }
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 1; i <= n; i++)
            if (incomming[i] == 0)
                qu.add(i);
        ArrayList<Integer> ans = new ArrayList<>();
        while (!qu.isEmpty()) {
            int cur = qu.remove();
            for (int e : adj.get(cur)) {
                incomming[e]--;
                if (incomming[e] == 0)
                    qu.add(e);
            }
            ans.add(cur);
        }
        if (ans.size() == n)
            return ans;
        return null;
    }

    /*
     * cycle in directed graph aswell as undirected
     * return -1 if no cycle
     * return -2 if cycle
     * ans is the cycle
     */
    public static int cycle(int node, ArrayList<ArrayList<Integer>> adj, int vis[], int pat[], ArrayList<Integer> ans,
            int par) {
        if (vis[node] == 1 && pat[node] == 1) {
            ans.add(node);
            return node;
        } else if (vis[node] == 1)
            return -1;
        vis[node] = 1;
        pat[node] = 1;
        for (int e : adj.get(node)) {
            // parent can be enabled if need a cycle more then 2 elements

            // if(e==par)continue;
            int from = cycle(e, adj, vis, pat, ans, node);
            if (from == -1)
                continue;
            if (from == -2)
                return -2;
            if (from == node) {
                ans.add(node);
                return -2;
            } else {
                ans.add(node);
                return from;
            }
        }
        pat[node] = 0;
        return -1;
    }

    /*
     * Binary Lifting (for finding Lowest Common Ancestor )
     * store the parents of every node by level of two power
     */
    static class BinaryLifting {
        int n;
        int parent[];
        int table[][];

        BinaryLifting(int n, int parent[]) {
            this.n = n;
            this.parent = parent;
            this.table = new int[n + 1][32];
            for (int i = 1; i <= n; i++) {
                table[i][0] = parent[i];
            }
            for (int i = 1; i <= 31; i++) {
                for (int j = 1; j <= n; j++) {
                    table[j][i] = table[table[j][i - 1]][i - 1];
                }
            }
        }

        int get(int i, int k) {
            int ans = i;
            for (int b = 31; b >= 0; b--) {
                if ((k & (1 << b)) != 0)
                    ans = table[ans][b];
            }
            return ans;
        }
    }

    /*
     * Kosaraju's algorithm (Strongly Connected Components)
     * -> normal adjacency list
     * -> reverse adjacency list
     * 
     * -> do a normal dfs with order of finishing time (post order traversal)
     * -> use the reverse adjacency list to find the strongly connected components
     */
    static class SCC {
        private static void dfs(ArrayList<ArrayList<Integer>> adj, int node, Stack<Integer> st, int vis[]) {
            vis[node] = 1;
            for (int ad : adj.get(node)) {
                if (vis[ad] == 0)
                    dfs(adj, ad, st, vis);
            }
            st.add(node);
        }

        private static void scc(ArrayList<ArrayList<Integer>> radj, int node, int vis[], int par) {
            for (int ad : radj.get(node)) {
                if (vis[ad] == 0) {
                    vis[ad] = par;
                    scc(radj, ad, vis, par);
                }
            }
        }

        public static void start(ArrayList<ArrayList<Integer>> adj, ArrayList<ArrayList<Integer>> radj) {
            int n = adj.size() - 1;
            Stack<Integer> st = new Stack<>();

            int vis[] = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                if (vis[i] == 0)
                    dfs(adj, i, st, vis);
            }
            Arrays.fill(vis, 0);
            int comp = 0;
            // ArrayList<Integer> ans = new ArrayList<>();
            while (!st.isEmpty()) {
                int node = st.pop();
                if (vis[node] == 0) {
                    comp++;
                    vis[node] = comp;
                    scc(radj, node, vis, comp);
                    // ans.add(comp);
                }
            }
            io.println(comp);
            io.spreadArray(vis, 1);
        }
    }

    /*
     * Hierholzer's Algorithm. (EulerTour)
     *
     * Eulerian Circuit
     * undirected :
     * -> all node has even degree
     * directed :
     * -> in-degree == out-degree
     * -> strongly connected components
     * Eulerian Path
     * undirected :
     * -> zero or two vertex with odd degree
     * directed :
     * -> 1) two vertex has
     * -> out_degree - in_degree = 1 (vertex 1)
     * -> in_degree - out_degree = 1 (vertex 2)
     * -> one of them may be start or end
     * -> every other has in_degree == out_degree
     * 
     * -> 2) all vertex has same in_degree == out_degree
     *
     * Hamiltonian Path
     * -> np-complete problem
     * -> no efficient solution
     * -> do recursion
     * Hamiltonian Circuit
     * -> np-complete problem
     * -> no efficient solution
     * -> do recursion
     */
    static class EulerTour {
        private static void EulerianCircuit() {
            int n = io.nextInt();
            int m = io.nextInt();
            ArrayList<LinkedList<int[]>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new LinkedList<>());
            }
            int out[] = new int[n];
            for (int i = 0; i < m; i++) {
                int fr = io.nextInt() - 1;
                int to = io.nextInt() - 1;
                adj.get(fr).add(new int[] { to, i });
                adj.get(to).add(new int[] { fr, i });
                out[fr]++;
                out[to]++;
            }
            for (int i = 0; i < n; i++) {
                if (out[i] % 2 == 1) {
                    // eulerian circuit will not exist if the degree is odd
                    io.println("IMPOSSIBLE");
                    return;
                }
            }
            Stack<Integer> st = new Stack<>();
            int vis[] = new int[m];
            dfs(0, adj, vis, st, out);
            if (st.size() == m + 1) {
                while (!st.isEmpty()) {
                    io.print((st.pop() + 1) + " ");
                }
            } else {
                io.print("IMPOSSIBLE");
            }
        }

        static void dfs(int node, ArrayList<LinkedList<int[]>> adj, int vis[], Stack<Integer> st, int out[]) {
            while (out[node] > 0) {
                out[node]--;
                int cur[] = adj.get(node).removeFirst();
                if (vis[cur[1]] == 1)
                    continue;
                vis[cur[1]] = 1;
                dfs(cur[0], adj, vis, st, out);
            }
            st.add(node);
        }
    }

    /*
     * Dinic's Algorithm
     * TC => N*N*M
     * Maximum Flow Algorithm
     */
    static class Dinic {
        private static int[] reachable(long adj[][], int source, int sink) {
            int n = adj.length;
            long cst[] = new long[n];
            int par[] = new int[n];
            Arrays.fill(par, -1);
            cst[source] = (long) 1e10;
            PriorityQueue<long[]> qu = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
            qu.add(new long[] { (long) 1e10, source });
            while (!qu.isEmpty()) {
                long cur[] = qu.remove();
                int fr = (int) cur[1];
                if (cst[fr] > cur[0])
                    continue;
                if (fr == sink)
                    break;
                for (int to = 0; to < n; to++) {
                    if (adj[fr][to] != 0 && cst[to] < Math.min(adj[fr][to], cst[fr])) {
                        cst[to] = Math.min(adj[fr][to], cst[fr]);
                        par[to] = fr;
                        qu.add(new long[] { cst[to], to });
                    }
                }
            }
            return par;
        }

        private static void maxFlow() {
            int n = io.nextInt();
            int m = io.nextInt();
            long adj[][] = new long[n][n];
            for (int i = 0; i < m; i++) {
                int fr = io.nextInt() - 1;
                int to = io.nextInt() - 1;
                long co = io.nextInt();
                adj[fr][to] += co;
            }
            long maxFlow = 0;
            while (true) {
                int par[] = reachable(adj, 0, n - 1);
                if (par[n - 1] == -1)
                    break;
                long flow = (long) 1e10;
                for (int now = n - 1; par[now] != -1; now = par[now]) {
                    int fr = par[now];
                    int to = now;
                    flow = Math.min(flow, adj[fr][to]);
                }
                maxFlow += flow;
                for (int now = n - 1; par[now] != -1; now = par[now]) {
                    int fr = par[now];
                    int to = now;
                    adj[fr][to] -= flow;
                    adj[to][fr] += flow;
                }
            }
            io.println(maxFlow);
        }
    }

    /*
     * used for finding maximum bibartate matching
     * matching between n*m
     * pair making with maximum count
     */
    static class Bipartate {
        private static int maximumBipartateMatching(int adj[][]) {
            int n = adj.length;
            int m = adj[0].length;
            int vis[] = new int[m];
            Arrays.fill(vis, -1);
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                int gon[] = new int[m];
                if (can(i, vis, adj, gon)) {
                    cnt++;
                }
            }
            return cnt;
        }

        private static boolean can(int node, int vis[], int adj[][], int gon[]) {
            for (int j = 0; j < adj[0].length; j++) {
                if (adj[node][j] == 1 && gon[j] == 0) {
                    gon[j] = 1;
                    if (vis[j] == -1) {
                        vis[j] = node;
                        return true;
                    } else if (can(vis[j], vis, adj, gon)) {
                        vis[j] = node;
                        return true;
                    }
                }
            }
            return false;
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