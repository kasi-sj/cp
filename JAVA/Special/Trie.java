
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
}

class BinaryNode {
    BinaryNode[] link;
    boolean flag;

    BinaryNode() {
        this.flag = false;
        this.link = new BinaryNode[2];
    }

    BinaryNode get(int bit) {
        return link[bit];
    }

    boolean has(int bit) {
        return link[bit] != null;
    }

    void set(int bit) {
        link[bit] = new BinaryNode();
    }
}

class BinaryTrie {
    BinaryNode root;

    BinaryTrie() {
        this.root = new BinaryNode();
    }

    void insert(long binaryNumber) {
        BinaryNode temp = root;
        for (int i = 63; i >= 0; i--) {
            int bit = (int) ((binaryNumber >> i) & 1);
            if (!temp.has(bit)) {
                temp.set(bit);
            }
            temp = temp.get(bit);
        }
        temp.flag = true;
    }

    boolean find(long binaryNumber) {
        BinaryNode temp = root;
        for (int i = 63; i >= 0; i--) {
            int bit = (int) ((binaryNumber >> i) & 1);
            if (!temp.has(bit)) {
                return false;
            }
            temp = temp.get(bit);
        }
        return temp.flag;
    }

    long maxXor(long binaryNumber) {
        BinaryNode temp = root;
        long ans = 0;
        for (int i = 63; i >= 0; i--) {
            int bit = (int) ((binaryNumber >> i) & 1);
            if (temp.has(1 - bit)) {
                ans |= (1L << i);
                temp = temp.get(1 - bit);
            } else {
                temp = temp.get(bit);
            }
        }
        return ans;
    }
}