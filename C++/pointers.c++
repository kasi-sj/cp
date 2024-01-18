#include<bits/stdc++.h>
using namespace std;

int main(){
    int a = 10; // a is an integer variable
    int *p; // p is a pointer variable
    p = &a; // p is storing the address of a
    cout<<p<<endl; // address of a
    cout<<*p<<endl; // value of a
    cout<<&a<<endl; // address of a
    cout<<&p<<endl; // address of p
    // cannot do *a as a is not a pointer variable

    // example with class and object
    class Person{
        public:
            int age;
            string name;
    };

    cout << "Example with array" << endl;
    int *arr = new int[3];
    arr[0] = 5;
    arr[1] = 10;
    arr[2] = 15;
    cout<<arr<<endl; // address of arr
    cout<<*arr<<endl; // value of arr
    cout<<&arr<<endl; // address of arr
    cout<<arr[0]<<endl; // value of arr[0]
    cout<<&arr[0]<<endl; // address of arr[0]
    cout<<*(arr+1)<<endl; // value of arr[1]
    cout<<&arr[1]<<endl; // address of arr[1]
    cout<<arr[1]<<endl; // value of arr[1]
    cout<<arr[2]<<endl; // value of arr[2]

    cout << "two dimensional array" << endl;
    int **arr2 = new int*[3];
    for(int i=0;i<3;i++){
        arr2[i] = new int[3];
    }
    arr2[0][0] = 5;
    arr2[0][1] = 10;
    arr2[0][2] = 15;
    arr2[1][0] = 20;
    arr2[1][1] = 25;
    arr2[1][2] = 30;
    arr2[2][0] = 35;
    arr2[2][1] = 40;
    arr2[2][2] = 45;
    cout<<arr2[0][0]<<endl; // value of arr2[0][0]
    cout<<&arr2[0][0]<<endl; // address of arr2[0][0]
    cout<<arr2[0][1]<<endl; // value of arr2[0][1]
    cout<<&arr2[0][1]<<endl; // address of arr2[0][1]
}