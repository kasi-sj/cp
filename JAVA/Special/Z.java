class Z {
    static int[] calculateZ(String s){
        int z[] = new int[s.length()];
        int l = 0;
        int r = 0;
        for(int i = 1 ; i < s.length() ; i++){
            if(i > r){
                l = r = i;
                while(r < s.length() && s.charAt(r) == s.charAt(r-l)){
                    r++;
                }
                z[i] = r-l;
                r--;
            } else {
              int i1 = i - l;
              if(z[i1] < r - i + 1){
                  z[i] = z[i1];
              }else{
                  l = i;
                  while(r < s.length() && s.charAt(r) == s.charAt(r-l)){
                      r++;
                  }
                  z[i] = r-l;
                  r--;
              }
            }
        }
        return z;
    }
}