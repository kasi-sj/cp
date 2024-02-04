struct node {
    node* link[26];
    int val;  // Add a member to store the value associated with the node
 
    node() : val(0) {
        for (int i = 0; i < 26; ++i) {
            link[i] = nullptr;
        }
    }
 
    node* get(char c) {
        return link[c - 'a'];
    }
 
    bool has(char c) {
        return link[c - 'a'] != nullptr;
    }
 
    void set(char c) {
        link[c - 'a'] = new node();
    }
};
 
struct Trie {
    node* root;
 
    Trie() : root(new node()) {}  // Initialize the root in the constructor
 
    int insert(string s) {
        int size = s.size();
        node* temp = root;
        for (int i = 0; i < size; i++) {
            if (!temp->has(s[i])) {
                temp->set(s[i]);
            }
            temp = temp->get(s[i]);
        }
        temp->val++;
        return temp->val;
    }
 
    bool find(string s) {
        int size = s.size();
        node* temp = root;
        for (int i = 0; i < size; i++) {
            if (!temp->has(s[i]))
                return false;
            temp = temp->get(s[i]);
        }
        return temp->val > 0;  // Return true if the value is greater than 0
    }
 
    int noof(string s , int i , vector<int> &dp){
        int h = i;
        int size = s.size();
        if(i==size)return 1;
        if(dp[h]!=-1)return dp[h];
        node* temp = root;
        int ans = 0;
        while(i<size){
            if(!temp->has(s[i])){
                return dp[h] = ans;
            }
            temp = temp->get(s[i]);
            if(temp->val!=0)ans += noof(s,i+1,dp);
            ans %= MOD;
            i++;
        }
        return dp[h] = ans;
    }
};