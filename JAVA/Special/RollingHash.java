class RollingHash {
    String s;
    int n;
    long power[];
    long hash[];
    long mod = (long)1e9+7;
    long prime = 31;
    RollingHash(String s){
        this.s = s;
        this.n = s.length();
        this.power = new long[n+1];
        this.hash = new long[n+1];
        power[0] = 1;
        for(int i = 1 ; i <= n ; i++){
            power[i] = (power[i-1]*prime)%mod;
        }
        hash[0] = s.charAt(0);
        for(int i = 1 ; i < n ; i++){
            hash[i] = (hash[i-1]*prime + s.charAt(i))%mod;
        }
    }

    long getHash(int l , int r){
        if(l==0)return hash[r];
        else{
            return (mod + hash[r]-(hash[l-1]*power[r-l+1]+mod)%mod)%mod;
        }
    }
}