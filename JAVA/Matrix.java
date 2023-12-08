class Matrix{

    int n;
    int mat[][];

    static int mod = (int)1e9+7;
    Matrix(int  arr[][]){
        this.mat = arr;
        this.n = arr.length;
    }

    int[][] pow(long n){
        return power(n);
    }

    int [][] power(long n){
        if(n==1){
            int te[][] = new int[this.n][this.n];
            for(int i = 0 ; i < this.n ; i++)
                for(int j = 0 ; j < this.n ; j++)
                    te[i][j]=mat[i][j];
            return te;
        }
        int [][] temp = power(n/2);
        temp = mul(temp , temp);
        if(n%2==1){
            temp = mul(temp,mat);
        }
        return temp;
    }

    static int [][] mul(int [][]m2 , int [][]m1){
        int n = m1.length;
        int arr[][] = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                for(int k = 0 ; k < n ; k++){
                    arr[i][j]+=(int)((m1[i][k]*(long)m2[k][j])%mod);
                    arr[i][j]%=mod;
                }
            }
        }
        return arr;
    }
}