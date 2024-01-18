#include<bits/stdc++.h>
using namespace std;

class Myclass{
    public:
        int x;
        Myclass(int val){
            x = val;
        }
        int get(){
            return x;
        }
};

class Myclass1 : public Myclass{
    public:
        Myclass1(int val) : Myclass(val){ // this is how you call the constructor of the parent class
            x = val;
        }
        int get(){
            return Myclass::get();
        }
};

class Node{
    public :
        int data;
        Node arr[2];
        Node(){
            data = -1;
            arr[0] = NULL;
            arr[1] = NULL;
        }
        Node get(int i){
            return arr[i];
        }
        void set(int i){
            arr[i] = new Node();
        }
        bool has(int i){
            return arr[i]==;
        }
}

class Trie{
    private:
        Node root;

    public:
        
        void insert(int val){
            Node curr = root;
            for(int i=31;i>=0;i--){
                int bit = (val>>i)&1;
                if(curr.get(bit)==NULL){
                    curr.set(bit);
                }
                curr = curr.get(bit);
            }
        }

        int find(int val){
            Node curr = root;
            int ans = 0;
            for(int i=31;i>=0;i--){
                int bit = (val>>i)&1;
                if()
            }
            return ans;
        }
}

int main(){
    Myclass obj(5);
    cout << obj.get() << endl;
    cout << obj.x << endl;
    cout << &obj << endl;
    cout << &obj.x << endl;
    // cout << &obj.get() << endl;  // error

    Myclass *obj1 = new Myclass(10);
    cout << obj1->get() << endl;
    cout << obj1->x << endl;
    cout << obj1 << endl;
    cout << (*obj1).x << endl;
    cout << &obj1 << endl;

    vector<int> v = {1,2,3,4,5};
}