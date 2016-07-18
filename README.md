# SortInversions

Problem statement: Given an array of n elements, find the total number of inversions needed to sort the array. 

Solution:
Following a Brute Force method, the answer to the problem can be found in O(n^2) which is a quadratic function of time. This solution would be correct but not optimal. To optimize, we can piggy back on the Merge Sort technique to utilize the Divide-and-Conquer methodology. 
This results in a much more linear approach as compared to the quadratoc approach of Brute Force, and this is especially important when considering a very large input size. 

In this code we solve the problem for 100,000 records. The problem is broken down into trivial sub-arrays with the Merge Sort approach. With this approach, since we're breaking down the array into left and right subarrays, Inversions become inherently of 3 types - left inversion, right inversion and split inversions. Left and right inversions are inversions within the individual left and right subarrays respectively, whereas split inversions are inversions needed between elements that are separated between the left and right subarrays. 

Left and right inversions are calculated individually in exactly the same manner in their respective recursive calls. Whenever two element positions need to be swapped, the inversion count is incremented by 1. Split inversions on the other hand, are a special case that is solved only during the Merge process. If an element from the sorted right subarray needs to be swapped with an element from the sorted left subarray, the inversion count is incremented by the number of elements left in the sorted left subarray, since they are obviously greater in value than the number being swapped, and hence we count one inversion per element. 
