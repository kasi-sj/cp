#include <iostream>
#include <set>

int main() {
    std::set<int> mySet = {1, 3, 5, 10, 14};

    int valueToFind = 4;

    // Find floor
    auto floorIt = mySet.lower_bound(valueToFind);
    if (floorIt != mySet.begin()) {
        std::cout << *floorIt << std::endl;
        --floorIt;
        std::cout << "Floor of " << valueToFind << " is: " << *floorIt << std::endl;
    } else {
        std::cout << "No floor found for " << valueToFind << std::endl;
    }

    // Find ceil
    auto ceilIt = mySet.upper_bound(valueToFind);
    if (ceilIt != mySet.end()) {
        std::cout << *ceilIt << std::endl;
        std::cout << "Ceil of " << valueToFind << " is: " << *ceilIt << std::endl;
    } else {
        std::cout << "No ceil found for " << valueToFind << std::endl;
    }

    return 0;
}
