void computeLPS(string s , int lps[]){
    int n = s.size();
    lps[0] = 0;
    int i = 1;
    int pre = 0;
    while ( i < n ){
        if(s[i]==s[pre]){
            pre++;
            lps[i] = pre;
            i++;
        }else{
            if(pre!=0){
                pre = lps[pre-1];
            }else{
                lps[i] = 0;
                i++;
            }
        }
    }
}

vector<int> search(string s , string p){
    int n = s.size();
    int m = p.size();
    int lps[m];
    computeLPS(p,lps);
    int i = 0;
    int j = 0;
    vector<int> ans;
    while(i<n){
        if(s[i]==p[j]){
            i++;
            j++;
        }else{
            if(j!=0){
                j = lps[j-1];
            }else{
                i++;
            }
        }
        if(j==m){
            ans.push_back(i-m);
            j=lps[j-1];
        }
    }
    return ans;
}