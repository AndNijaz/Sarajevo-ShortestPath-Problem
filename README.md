# Sarajevo Shortest Path Problem

[Repository Link](https://github.com/AndNijaz/Sarajevo-ShortestPath-Problem)

## Project Description
The Sarajevo Shortest Path Problem is a program designed to solve the issue of finding optimal travel routes between different locations in Sarajevo. The application utilizes graph theory to represent the city and calculates the shortest path between points while considering various constraints, including probabilities of obstacles. This project was developed as part of an academic exercise to address real-world logistical challenges in urban transportation.

---

## Assignment Instructions

### Background
Traveling from point A to B in Sarajevo has become increasingly complex due to growing congestion and limited transportation options. Traditional modes like walking, trams, cars, and electric scooters all have drawbacks. A futuristic solution involving quantum mechanics proposes transporting people in glass capsules via cannons between 20 strategic locations in the city.

### Locations
The 20 locations include:
- A: Alipašino
- B: Baščaršija
- C: Ciglane
- D: Doglodi
- E: Energoinvest Stup
- F: Ferhadija
- G: Grdonj
- H: Hrid
- I: Ilidža
- J: Jarčedole
- K: Krivoglavci
- L: Lužani
- M: Marijin Dvor
- N: Novi Grad
- O: Otoka

### Tasks

#### Task 1: Directed Weighted Graph Representation
Create a program to represent the city as a directed weighted graph. The input format is as follows:
```
A B 10
```
Here, A is the starting point, B is the destination, and 10 is the travel time in seconds between the points.

#### Task 2: Shortest Path Algorithm
Develop a class to solve the shortest path algorithm for any two nodes in the graph. Include unit tests to validate the algorithm with various graph configurations and weights.

#### Task 3: Optimal Travel Times
For any given input file, calculate the travel times between all mentioned places. Ensure that all locations in the file are represented in the graph. Example input:
```
A B 10
B C 20
C A 5
```
Output the optimal travel times for all routes (e.g., A→B, A→C, B→A, etc.).

#### Task 4: Probability Weights
Support the previous tasks by introducing constraints with probability weights for specific routes. Input format example:
```
A, B, high construction, 0.2
```
Here, 0.2 represents the probability of encountering high construction between A and B, impacting the travel time.

---

## Implementation Details

### Technologies Used
- **Java**: Core programming language for graph processing and algorithms.
- **Unit Testing Framework**: To ensure correctness of shortest path calculations.
- **Data Structures**: Directed weighted graphs using adjacency lists or matrices.

### Input Files
The project supports the ingestion of text files formatted as described in the tasks. Example files include:
- `simple.txt`: Basic graph configurations.
- `complex.txt`: Larger graph with complex route constraints.

### Algorithms
- **Shortest Path**: Implement algorithms like Dijkstra's or Floyd-Warshall to compute optimal paths.
- **Constraint Handling**: Incorporate probability adjustments to modify weights dynamically based on given conditions.

---

## Usage

### Running the Program
1. Prepare an input file representing the graph.
2. Execute the program with the input file.
3. View the calculated shortest paths and travel times for all routes.

### Example Command
```bash
python shortest_path_solver.py input.txt
```

### Output Format
The program outputs the shortest travel times for all routes in the graph, considering probability constraints where applicable.

---

## Future Enhancements
- Visualize the graph and routes using libraries like Matplotlib or NetworkX.
- Integrate real-time data for probability weights, such as weather or traffic updates.
- Expand the system to support additional cities and more complex transportation networks.

---

