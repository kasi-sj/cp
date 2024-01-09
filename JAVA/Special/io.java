import java.io.*;
import java.util.*;

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

    static void spreadArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    static void spreadArray(long[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    static void spreadArray(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        pw.println();
    }

    static void spreadList(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
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

    static void printf(String format, Object... args) {
        pw.printf(format, args);
    }

    static void close() {
        pw.close();
    }
}