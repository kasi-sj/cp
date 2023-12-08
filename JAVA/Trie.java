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

