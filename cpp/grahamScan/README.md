# Graham Scan Algorithm

## Overview

The Graham Scan algorithm is a method for computing the convex hull of a finite set of points in the plane. The convex hull is the smallest convex polygon that contains all the given points.

## How It Works

1. **Find the lowest point** - Start by finding the point with the lowest y-coordinate (and lowest x-coordinate in case of ties)
2. **Sort points by polar angle** - Sort all other points by their polar angle relative to the lowest point
3. **Build the hull** - Process each point, keeping only those that make left turns (counter-clockwise)

## Time Complexity

- **O(n log n)** - where n is the number of points (dominated by the sorting step)

## Files

- `GrahamAlgorithm.cpp` - Main implementation with the convex hull algorithm
- `randomNumbers.cpp` - Helper functions for generating random test points
- `randomNumbers.h` - Header file for random number generation

## Usage

```cpp
ga algorithm(50);  // Create instance with 50 random points
algorithm.generatePoints();  // Generate random points
std::vector<point> hull = algorithm.convexHull();  // Compute convex hull
```

## Example Output

<p align="center">
  <img src="https://github.com/Kepeczsz/GrahamScan/blob/main/5000pkt.png" width="150">
  <img src="https://github.com/Kepeczsz/GrahamScan/blob/main/Co_5000pkt.PNG" width="150">
  <img src="https://github.com/Kepeczsz/GrahamScan/blob/main/50pkt.png" width="150">
  <img src="https://github.com/Kepeczsz/GrahamScan/blob/main/Co_50pkt.PNG" width="150">
</p>

*Left to right: 5000 points with hull, 5000 points console output, 50 points with hull, 50 points console output*
