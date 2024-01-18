#include<bits/stdc++.h>
using namespace std;

struct comp{
    bool operator()(pair<int,int> &a,pair<int,int> &b){
        // this comparator will sort pair in decreasing order
        if(a.first==b.first){
            return a.second>b.second;
        }
        return a.first>b.first;
    }
};

void explainPair(){

    cout<<"pair "<<endl<<endl;
    cout<<"it store a pair of values "<<endl;
    pair<int,int> p = {1,3};
    p.first = 2;
    cout<<p.first<<" "<<p.second<<endl;
    pair<int,pair<int,int>> p2 = {1,{3,4}};
    cout<<p2.first<<" "<<p2.second.first<<" "<<p2.second.second<<endl;
    pair<int,int> arr[] = {{1,2},{2,3},{3,4}};
    for(int i=0;i<3;i++){
        cout<<arr[i].first<<" "<<arr[i].second<<endl;
    }
}

void print(vector<int> &v){
    for(int i=0;i<v.size();i++){
        cout<<v[i]<<" ";
    }
    cout<<endl;
    // v[0] = 2;
}

void print(list<int> &l){
    for(int i:l){
        cout<<i<<" ";
    }
    cout<<endl;
}

void print(deque<int> &d){
    for(int i:d){
        cout<<i<<" ";
    }
    cout<<endl;

}

void explainVector(){
    cout<<"vector "<<endl<<endl;
    cout<<"it is a dynamic array "<<endl;
    vector<int> v;
    v.push_back(1);
    v.push_back(2);
    v.push_back(3);
    cout<<"adding elements "<<endl;
    for(int i=0;i<v.size();i++){
        cout<<v[i]<<" ";
    }
    cout<<endl;
    v.pop_back();
    cout<<"removing elements "<<endl;
    for(int i=0;i<v.size();i++){
        cout<<v[i]<<" ";
    }
    cout<<endl;
    cout<<"iterators "<<endl;
    for(int i=0;i<v.size();i++){
        cout<<v[i]<<" ";
    }
    cout<<endl;  
    vector<int>::iterator it ;
    for(it=v.begin();it!=v.end();it++){
        cout<<*it<<" ";
    }
    cout<<endl;
    vector<int> v3(v);
    for(int it:v3){
        cout<<it<<" ";
    }
    cout<<endl;
    cout<<"auto keyword "<<endl;
    auto s = "hello";
    cout<<s<<endl;
    v.push_back(4);
    v.push_back(5);
    v.push_back(6);
    print(v);
    v.erase(v.begin()+1);
    cout<<"erase "<<endl;
    print(v);
    print(v);
    v.erase(v.begin()+1,v.begin()+3);
    cout<<"erase a sub array"<<endl;
    print(v);
    v.insert(v.begin(),3);
    cout<<"insert "<<endl;
    print(v);
    v.insert(v.begin()+1,2,4);
    cout<<"insert 2 4's "<<endl;
    print(v);
    vector<int> v2 = {10,11};
    v.insert(v.begin(),v2.begin(),v2.end());
    cout<<"insert a vector "<<endl;
    print(v);
    cout<<"size "<<v.size()<<endl;

    cout << "before swap" << endl;
    print(v);
    print(v2);
    // swap(v2,v);
    cout<<"after swap "<<endl;
    print(v);
    print(v2);

    
    cout<<"reverse "<<endl;
    reverse(v.begin(),v.end());
    print(v);

    cout<<"sort "<<endl;
    sort(v.begin(),v.end());
    print(v);

    cout<<"max "<<endl;
    cout<<*max_element(v.begin(),v.end())<<endl;
    
    cout<<"min "<<endl;
    cout<<*min_element(v.begin(),v.end())<<endl;

    cout<<"front "<<endl;
    cout<<v.front()<<endl;

    cout<<"back "<<endl;
    cout<<v.back()<<endl;

    cout<<"sum "<<endl;
    cout<<accumulate(v.begin(),v.end(),0)<<endl;

    cout<<"count 4 :"<<endl;
    cout<<count(v.begin(),v.end(),4)<<endl;

    cout<<"find 4:"<<endl;
    cout<<find(v.begin(),v.end(),4)-v.begin()<<endl;

    print(v);
    cout<<"binary search "<<endl;
    cout<<binary_search(v.begin(),v.end(),10)<<endl; // it will return true or false

    cout<<"index of 4 "<<endl;
    cout<<lower_bound(v.begin(),v.end(),4)-v.begin()<<endl; 

    cout<<"lower bound "<<endl;
    cout<<lower_bound(v.begin(),v.end(),4)-v.begin()<<endl;

    cout<<"upper bound "<<endl;
    cout<<upper_bound(v.begin(),v.end(),4)-v.begin()<<endl;

    vector v4(v);
    v4[0]++;
    cout<<"v4 "<<endl;
    print(v4);
    cout<<"v "<<endl;
    print(v);
    
    // sort based on comparator
    sort(v.begin(),v.end(),greater<int>());
    cout<<"sort based on comparator "<<endl;
    print(v);
    // custom comparator
    sort(v.begin(),v.end(),[](int a,int b){
        return a>b;
    });
    // we can also define a comparator using struct
    // we cannot use the lambda comparator in other data structures
    cout<<"sort based on custom comparator "<<endl;
    print(v);

    cout<<"two d vector "<<endl;
    vector<vector<int>> v5 = {{1,2},{3,4}};

    // create a 2d vector of size 3*4
    vector<vector<int>> v6(3,vector<int>(4,0));
}

void explainList(){
    cout<<"list "<<endl<<endl;
    cout<<"it is a doubly linked list "<<endl;
    list<int> l = {1,2,3,4,5};

    cout<<"front "<<endl;
    cout<<l.front()<<endl;
    cout<<"back "<<endl;
    cout<<l.back()<<endl;
    cout<<"push front "<<endl;
    l.push_front(0);
    print(l);
    cout<<"pop front "<<endl;
    l.pop_front();
    print(l);
    cout<<"push back "<<endl;
    l.push_back(6);
    print(l);
    cout<<"pop back "<<endl;
    l.pop_back();
    print(l);
    cout<<"insert "<<endl;
    l.insert(l.begin(),2,3); // insert 2 3's
    print(l);
}

void explainDequeue(){
    cout<<"dequeue "<<endl<<endl;
    cout<<"it is a doubly ended queue "<<endl;
    deque<int> dq = {1,2,3,4,5};
    cout<<"push front "<<endl;
    dq.push_front(0);
    print(dq);
    cout<<"pop front "<<endl;
    dq.pop_front();
    print(dq);
    cout<<"push back "<<endl;
    dq.push_back(6);
    print(dq);
    cout<<"pop back "<<endl;
    dq.pop_back();
    print(dq);

}

void explainQueue(){
    cout<<" explain queue "<<endl<<endl;
    cout<<"it is a FIFO data structure "<<endl;
    queue<int> q;   
    q.push(1);
    q.push(2);
    q.push(3);
    cout<<"push "<<endl;
    cout<<q.front()<<endl;
    q.pop();
    cout<<"pop "<<endl;
    cout<<q.front()<<endl;
    cout<<"size "<<endl;
    cout<<q.size()<<endl;
    cout<<"empty "<<endl;
    cout<<q.empty()<<endl;

}

void explainStack(){
    cout<<"stack "<<endl<<endl;
    cout<<"it is a LIFO data structure "<<endl;
    stack<int> s;
    s.push(1);
    s.push(2);
    s.push(3);
    cout<<"push "<<endl;
    cout<<s.top()<<endl;
    s.pop();
    cout<<"pop "<<endl;
    cout<<s.top()<<endl;
    cout<<"size "<<endl;
    cout<<s.size()<<endl;
    cout<<"empty "<<endl;
    cout<<s.empty()<<endl;

}

void explainMath(){
    cout<<"math "<<endl<<endl;
    int a = -10;
    int b = 20;
    cout<<"a "<<a<<endl;
    cout<<"b "<<b<<endl;
    swap(a,b);
    cout<<"swap "<<a<<" "<<b<<endl;
    cout<<"min "<<min(a,b)<<endl;
    cout<<"max "<<max(a,b)<<endl;
    cout<<"abs "<<abs(a)<<endl;
    cout<<"pow "<<pow(2,3)<<endl;
    cout<<"sqrt "<<sqrt(25)<<endl;
    cout<<"log "<<log(2)<<endl;
    cout<<"log10 "<<log10(2)<<endl;
    cout<<"ceil "<<ceil(2.3)<<endl;
    cout<<"floor "<<floor(2.3)<<endl;
    cout<<"round "<<round(2.3)<<endl;
    cout<<"round "<<round(2.5)<<endl;
    cout<<"round "<<round(2.6)<<endl;
}

void explainPriorityQueue(){
    cout<<"priority queue "<<endl<<endl;
    cout<<"it is a data structure which is similar to queue but it is sorted "<<endl;
    priority_queue<int> pq; // it store data in decreasing order
    pq.push(1);
    pq.push(2);
    pq.push(3);
    cout<<"push "<<endl;
    cout<<pq.top()<<endl;
    pq.pop();
    cout<<"pop "<<endl;
    cout<<pq.top()<<endl;
    cout<<"size "<<endl;
    cout<<pq.size()<<endl;
    cout<<"empty "<<endl;
    cout<<pq.empty()<<endl;
    // it store data in increasing order 
    priority_queue<int,vector<int>,greater<int>> pq2; // it defines a int value vector and it store data in increasing order

    // to order a pair in priority queue we have to define a comparator
    priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> pq3;
    pq3.push({1,2});
    pq3.push({2,3});
    pq3.push({3,4}); // first it will compare first element of pair and if it is equal then it will compare second element of pair
    cout<<"push "<<endl;
    cout<<pq3.top().first<<" "<<pq3.top().second<<endl;
    pq3.pop();
    cout<<"pop "<<endl;
    cout<<pq3.top().first<<" "<<pq3.top().second<<endl;

    // to define a comparator we have to define a struct
    

    priority_queue<pair<int,int>,vector<pair<int,int>>,comp> pq4;
    pq4.push({1,2});
    pq4.push({2,3});
    pq4.push({3,4}); // first it will compare first element of pair and if it is equal then it will compare second element of pair
    cout<<"push "<<endl;
    cout<<pq4.top().first<<" "<<pq4.top().second<<endl;

    // lambda comparator
    auto comp = [](pair<int,int> &a,pair<int,int> &b){
        if(a.first==b.first){
            return a.second>b.second;
        }
        return a.first>b.first;
    };
    priority_queue<pair<int,int>,vector<pair<int,int>>, decltype(comp)> pq5(comp);
    pq5.push({1,2});
    pq5.push({2,3});

    cout<<"push "<<endl;
    cout<<pq5.top().first<<" "<<pq5.top().second<<endl;

}

void explainSet() {
    cout << "set" << endl << endl;
    cout << "it is a data structure which store unique elements in sorted order" << endl;
    set<int> s;
    s.insert(1);
    s.insert(2);
    s.insert(3);
    cout << "insert" << endl;
    for (int i : s) {
        cout << i << " ";
    }
    cout << endl;
    cout << "size" << endl;
    cout << s.size() << endl;
    cout << "empty" << endl;
    cout << s.empty() << endl;
    cout << "find" << endl;
    cout << *s.find(2) << endl;
    cout << "erase" << endl;
    s.erase(2);
    for (int i : s) {
        cout << i << " ";
    }
    cout << endl;
    cout << "lower bound" << endl;
    cout << *s.lower_bound(2) << endl;

    cout << "upper bound" << endl;
    cout << *s.upper_bound(2) << endl;

    cout << "set with comparator" << endl;
    
    // greater comparator
    set<int , greater<int>> s2;
    // lower comparator
    set<int , less<int>> s3;
    s2.insert(1);
    s3.insert(2);
    
    for (int i : s2) {
        cout << i << " ";
    } 

    // lambda comparator
    auto comp = [](int a,int b){
        return a>b;
    };
    set<int , decltype(comp)> s4(comp);
    s4.insert(1);
    s4.insert(2);
    for (int i : s4) {
        cout << i << " ";
    }
}


void explainMultiSet() {
    cout << "multiset" << endl << endl;
    cout << "it is a data structure which store multiple elements in sorted order" << endl;
    multiset<int> s;
    s.insert(1);
    s.insert(2);
    s.insert(2);
    s.insert(3);
    cout << "insert" << endl;
    for (int i : s) {
        cout << i << " ";
    }
    cout << endl;
    cout << "size" << endl;
    cout << s.size() << endl;
    cout << "empty" << endl;
    cout << s.empty() << endl;
    cout << "find" << endl;
    cout << *s.find(2) << endl;
    cout << "erase" << endl;
    s.erase(2); // it will erase all 2's
    for (int i : s) {
        cout << i << " ";
    }
    cout << endl;
    s.insert(1);
    s.insert(2);
    for(int i:s){
        cout<<i<<" ";
    }
    cout<<endl;
    s.erase(s.find(1)); // it will erase only one 1
    for(int i:s){
        cout<<i<<" ";
    }
    cout << endl;

    cout << "count" << endl;
    cout << s.count(2) << endl;

    cout << "lower bound" << endl;
    cout << *s.lower_bound(2) << endl;

    cout << "upper bound" << endl;
    cout << *s.upper_bound(2) << endl;

    cout << "multiset with comparator" << endl;
    
    // greater comparator
    // multiset<int , greater<int>> s2;
    // lower comparator
    // multiset<int , less<int>> s2;
    // s2.insert(1);
    // s2.insert(2);
    // s2.insert(2);
    
    // for (int i : s2) {
    //     cout << i << " ";
    // }
}

void explainUnorderedSet(){
    cout<<"unordered set "<<endl<<endl;
    cout<<"it is a data structure which store unique elements in unsorted order" << endl;
    unordered_set<int> s;
    s.insert(1);
    s.insert(2);
    s.insert(3);
    cout << "insert" << endl;
    for (int i : s) {
        cout << i << " ";
    }
    cout << endl;
    cout << "size" << endl;
    cout << s.size() << endl;
    cout << "empty" << endl;
    cout << s.empty() << endl;
    cout << "find" << endl;
    cout << *s.find(2) << endl;
    cout << "erase" << endl;
    s.erase(2);
    for (int i : s) {
        cout << i << " ";
    }
    cout << endl;
    // to know wether a element is present in set or not we can use count function
}

void explainMap(){
    cout<<"map "<<endl<<endl;
    cout<<"it is a data structure which store key value pairs in sorted order" << endl;
    map<int,int> m;
    m[1] = 2;
    m[2] = 3;
    m[3] = 4;
    cout<<"insert "<<endl;
    for(auto i:m){
        cout<<i.first<<" "<<i.second<<endl;
    }
    cout<<"size "<<endl;
    cout<<m.size()<<endl;
    cout<<"empty "<<endl;
    cout<<m.empty()<<endl;
    cout<<"find "<<endl;
    cout<<m.find(2)->second<<endl;
    cout<<"erase "<<endl;
    m.erase(2);
    for(auto i:m){
        cout<<i.first<<" "<<i.second<<endl;
    }
    // to know wether 2 is in
    if(m[2]){
        cout<<"yes"<<endl;
    }
    else{
        cout<<"no"<<endl;
    }
    if(m[1]){
        cout<<"yes"<<endl;
    }
    else{
        cout<<"no"<<endl;
    }

    cout<<"map with comparator "<<endl;
    // greater comparator
    map<int,int,greater<int>> m2;
    // lower comparator
    map<int,int,less<int>> m3;
    m2[1] = 2;
    m2[2] = 3;
    m2[3] = 4;
    cout<<"insert "<<endl;
    for(auto i:m2){
        cout<<i.first<<" "<<i.second<<endl;
    }

    // lambda comparator
    auto comp = [](int a,int b){
        return a>b;
    };
    map<int,int,decltype(comp)> m4(comp);
    m4[1] = 2;
    m4[2] = 3;

    // lower bound
    cout<<"lower bound "<<endl;
    cout<<m4.lower_bound(2)->first<<" "<<m4.lower_bound(2)->second<<endl;

    // upper bound
    cout<<"upper bound "<<endl;
    cout << m4.upper_bound(2)->first << " " << m4.upper_bound(2)->second << endl;
    
}

void explainUnorderedMap(){
    cout<<"unordered map "<<endl<<endl;
    cout<<"it is a data structure which store key value pairs in unsorted order" << endl;
    unordered_map<int,int> m;
    m[1] = 2;
    m[2] = 3;
    m[3] = 4;
    cout<<"insert "<<endl;
    for(auto i:m){
        cout<<i.first<<" "<<i.second<<endl;
    }
    cout<<"size "<<endl;
    cout<<m.size()<<endl;
    cout<<"empty "<<endl;
    cout<<m.empty()<<endl;
    cout<<"find "<<endl;
    cout<<m.find(2)->second<<endl;
    cout<<"erase "<<endl;
    m.erase(2);
    for(auto i:m){
        cout<<i.first<<" "<<i.second<<endl;
    }
    // to know wether 2 is in
    if(m[2]){
        cout<<"yes"<<endl;
    }
    else{
        cout<<"no"<<endl;
    }
    if(m[1]){
        cout<<"yes"<<endl;
    }
    else{
        cout<<"no"<<endl;
    }
}

void explainExtra(){
    vector<int> v = {1,2,3,4};
    sort(v.begin(),v.end(),[](int a , int b){return a>b;});
    print(v);
    // binarystring of a number
    int n = 5;
    string s = bitset<8>(n).to_string();
    cout<<s<<endl;

    long long int a = 1000000000000000000;
    string s2 = bitset<64>(a).to_string();
    cout<<s2<<endl;

    // to convert a binary string to a number
    string s3 = "1000000";
    int n2 = stoi(s3,0,2); // the arguments are string , index from where we want to convert , base
    cout<<n2<<endl;

    // bit count
    int n3 = 5;
    cout<<__builtin_popcount(n3)<<endl;

    // max element
    cout<<"max element "<<endl;
    int max = *max_element(v2.begin(),v2.end());
    cout<<max<<endl;
    
    // min element
    cout<<"min element "<<endl;
    int min = *min_element(v2.begin(),v2.end());
    cout<<min<<endl;

    // sum
    cout<<"sum "<<endl;
    int sum = accumulate(v2.begin(),v2.end(),0);
    cout<<sum<<endl;

    // reverse
    cout<<"reverse "<<endl;
    reverse(v2.begin(),v2.end());
    print(v2);

    // sort string
    string s4 = "hello";
    sort(s4.begin(),s4.end());
    cout<<s4<<endl;

    // all permutations
    string s5 = "abc";
    sort(s5.begin(),s5.end());
    do{
        cout<<s5<<endl;
    }while(next_permutation(s5.begin(),s5.end()));
}

void fastio(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
}

int main(){
    fastio();
    // explainPair();
    // explainVector();
    // explainMath();
    // explainList();
    // explainDequeue();
    // explainQueue();
    // explainStack();
    // explainPriorityQueue();
    // explainSet();
    // explainMultiSet();
    // explainUnorderedSet();
    // explainMap();
    // explainUnorderedMap();
    // explainExtra();
    return 0;
}