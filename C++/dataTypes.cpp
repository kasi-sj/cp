#include <iostream>
#include <limits>

using namespace std;

int main(){

    // minimum value and maximum value of int
    int intmin = numeric_limits<int>::min();
    int intmax = numeric_limits<int>::max();
    cout << "intmin = " << intmin << endl;
    cout << "intmax = " << intmax << endl;
    // minimum value and maximum value of long
    long longmin = numeric_limits<long>::min();
    long longmax = numeric_limits<long>::max();
    cout << "longmin = " << longmin << endl;
    cout << "longmax = " << longmax << endl;
    // minimum value and maximum value of long long
    long long longlongmin = numeric_limits<long long>::min();
    long long longlongmax = numeric_limits<long long>::max();
    cout << "longlongmin = " << longlongmin << endl;
    cout << "longlongmax = " << longlongmax << endl;
    // minimum value and maximum value of float
    float floatmin = numeric_limits<float>::min();
    float floatmax = numeric_limits<float>::max();
    cout << "floatmin = " << floatmin << endl;
    cout << "floatmax = " << floatmax << endl;
    // minimum value and maximum value of double
    double doublemin = numeric_limits<double>::min();
    double doublemax = numeric_limits<double>::max();
    cout << "doublemin = " << doublemin << endl;
    cout << "doublemax = " << doublemax << endl;
    // minimum value and maximum value of long double
    long double longdoublemin = numeric_limits<long double>::min();
    long double longdoublemax = numeric_limits<long double>::max();
    cout << "longdoublemin = " << longdoublemin << endl;
    cout << "longdoublemax = " << longdoublemax << endl;
    // minimum value and maximum value of char
    char charmin = numeric_limits<char>::min();
    char charmax = numeric_limits<char>::max();
    cout << "charmin = " << charmin << endl;
    cout << "charmax = " << charmax << endl;

    return 0;
}