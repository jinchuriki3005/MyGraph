Max Tran, CSE 373 HW#5, 3/3/17
1)
step 0:
Vertex   In-degree   Removed?
A           0                                 
B           1
C           1
D           2
E           2
F           1
G           0
step 1:
Vertex   In-degree   Removed?
A           0         yes                        
B           0
C           1
D           1
E           2
F           1
G           0
step 2:
Vertex   In-degree   Removed?
A           0         yes                        
B           0         yes
C           1
D           0
E           1
F           1
G           0
step 3:
Vertex   In-degree   Removed?
A           0         yes                        
B           0         yes
C           1
D           0         yes
E           0
F           1
G           0
step 4:
Vertex   In-degree   Removed?
A           0         yes                        
B           0         yes
C           0
D           0         yes
E           0         yes
F           1
G           0
step 5:
Vertex   In-degree   Removed?
A           0         yes                        
B           0         yes
C           0         yes
D           0         yes
E           0         yes
F           1
G           0
step 6:
Vertex   In-degree   Removed?
A           0         yes                        
B           0         yes
C           0         yes
D           0         yes
E           0         yes
F           0
G           0         yes
step 7:
Vertex   In-degree   Removed?
A           0         yes                        
B           0         yes
C           0         yes
D           0         yes
E           0         yes
F           0         yes
G           0         yes
Thus, the order is A, B, D, E, C, G, F

2)
a) Kruskal's algorithm
Step 1:
1: (B,E), (D,G), (C,G)
2: (A,B), (E,F), (D,C)
3: (A,E), (E,G), (B,F)
4: (A,D), (F,G)
Ouput:
Step 2:
1: (D,G), (C,G)
2: (A,B), (E,F), (D,C)
3: (A,E), (E,G), (B,F)
4: (A,D), (F,G)
Ouput: (B,E)
Step 3:
1: (C,G)
2: (A,B), (E,F), (D,C)
3: (A,E), (E,G), (B,F)
4: (A,D), (F,G)
Ouput: (B,E), (D,G)
Step 4:
1:
2: (A,B), (E,F), (D,C)
3: (A,E), (E,G), (B,F)
4: (A,D), (F,G)
Ouput: (B,E), (D,G), (C,G)
Step 5:
1: 
2: (E,F), (D,C)
3: (A,E), (E,G), (B,F)
4: (A,D), (F,G)
Ouput: (B,E), (D,G), (C,G), (A,B)
Step 6:
1: 
2: (D,C)
3: (A,E), (E,G), (B,F)
4: (A,D), (F,G)
Ouput: (B,E), (D,G), (C,G), (A,B), (E,F)
Step 7:
1: 
2: 
3: (A,E), (E,G), (B,F)
4: (A,D), (F,G)
Ouput: (B,E), (D,G), (C,G), (A,B), (E,F)
Step 8:
1: 
2: 
3: (E,G), (B,F)
4: (A,D), (F,G)
Ouput: (B,E), (D,G), (C,G), (A,B), (E,F)
Step 9:
1: 
2: 
3: (B,F)
4: (A,D), (F,G)
Ouput: (B,E), (D,G), (C,G), (A,B), (E,F), (E,G)
Step 10:
1: 
2: 
3: 
4: (A,D), (F,G)
Ouput: (B,E), (D,G), (C,G), (A,B), (E,F), (E,G)
Step 11:
1: 
2: 
3: 
4: (F,G)
Ouput: (B,E), (D,G), (C,G), (A,B), (E,F), (E,G)
Step 12:
1: 
2: 
3: 
4: 
Ouput: (B,E), (D,G), (C,G), (A,B), (E,F), (E,G)
Thus, the output is (B,E), (D,G), (C,G), (A,B), (E,F), (E,G)
b) Prim's algorithm
step 1:
Vertex   known?   cost   prev
A        y        0
B                 2      A
C                 inf
D                 4      A
E                 3      A
F                 inf
G                 inf
step 2:
Vertex   known?   cost   prev
A        y        0
B        y        2      A
C                 inf
D                 4      A
E                 1      B
F                 3      B
G                 inf
step 3:
Vertex   known?   cost   prev
A        y        0
B        y        2      A
C                 inf
D                 4      A
E        y        1      B
F                 2      E
G                 3      E
step 4:
Vertex   known?   cost   prev
A        y        0
B        y        2      A
C                 inf
D                 4      A
E        y        1      B
F        y        2      E
G                 3      E
step 5:
Vertex   known?   cost   prev
A        y        0
B        y        2      A
C                 1      G
D                 1      G
E        y        1      B
F        y        2      E
G        y        3      E
step 6:
Vertex   known?   cost   prev
A        y        0
B        y        2      A
C        y        1      G
D        y        1      G
E        y        1      B
F        y        2      E
G        y        3      E
so the output is (A,B) (C,G) (D,G) (E,B) (F,E), (G,E)
3)
a) Map newGraph = new Map();
for(each vertex v in graph.keySet()){
   for(each vertex endV in graph.get(v){
      if(the new graph doesnt map endV to v){
         newGraph.put(endV, v);
      }
   }
}
b) 
Collection<Vertex> nextAdjVertexSet = new ArrayList<Vertex>();
Collection<Vertex> adjVertexSet = adjacentVertices(graph, source);
for(each vertex v in adjVertexSet){
   for(each vertex nextV in adjacentVertices(graph, v)){
      if(adjVertexSet doesn't contain nextV){
         nextAdjVertexSet.add(nextV);
      }
   }
}
c) If the graph is fully connected, then the worst case asymptotic 
tight bound runtime for the pseudocode I've written in parts (a) and (b)
is O(N^2) because you will have to traverse all the adjacent vertices
(which is every vertex in the graph) of each vertex that is adjacent to
the vertex source and since the graph is fully connected, you will have 
to look at every vertex.
4)
a)
We can combine Dijkstra’s algorithm and Krusal's algorithm. We will process
this like Dijkstra's algorithm except I will have a column that contains the
total number of edges of the path that connect vertex a to b. We know that
Dijkstra’s algorithm allows us to look at the neighbor of the vertex in every
direction. Thus while doing that, I could keep track of the minimum number of 
edges that connect from vertex a to current vertex using Krusal's algorithm
We will then return the path has both the loIst cost and smallest number of edges
b)
Consider a simple graph
S -> A (Iight 7)
S -> B (Iight 6)
A -> B (Iight -4)
Using Dijkstra’s algorithm will return the shortest distance from S to B as 6
instead of 3 because the algorithm visits B immediately after S is visited first
The correct shortest path from S to B should be 3 because S -> A -> B
Thus Dijkstra’s algorithm gives the wrong ansIr in the presence of a negative 
cost edge but no negative-cost cycles.
c) First, this will fail because adding a Iight to every edge adds more Iight
to high-number-of-edges paths than small-number-of-edges paths. Thus, the distance
is then dependent on the number of edges in a path. 
Consider 2 paths
a -(-3)-> b -(5)-> c          : path cost = 2
a -(-3)-> e -(1)-> d -(3)-> c : path cost = 1
Thus, the second path is the shortest path; hoIver, if you add 3 to make every edges
Iights positive in order to run Dijkstra's algorithm. You will get:
a -(0)-> b -(8)-> c           : path cost = 8
a -(0)-> e -(4)-> d -(6)-> c  : path cost = 10
After adding constant 3, the shortest path is now the first path, which is wrong.
This happens because the second path has more edges to reach from a to c. Thus I 
basically add 3*(3) cost to the second path whereas I only add 2*(3) cost to first 
path. Number of edges in the first path is less and hence that path gets the loIr 
path cost. Thus if I add constant k to every edge to make it nonnegative in order 
to run Dijkstra's algorithm, I will then make the distance to become dependent on 
the number of edges in a path. 
5) 
a)
I tested shortestPath by ensuring illegal arguments throw an Exception and all 
returns Ire valid and correct. I try to test this step by step because this method
is a lot more complex than the other methods. First, I test the vertWrapperInfo class
to make sure that the fields are consistent and maintained correctly. Then I try to
verify that the Priority Queue was successfully built with a sample test of small amount 
of nodes then I let it test with a bigger sample larger text file. Then I test the
overall output of shortestPath() with a few cases. I want to make sure that the method
run succesfully with or without illegalArgument.
b)
I implement my myGraph with a priorityQueue. Using Dijkstra's algorithm, I are 
required to look at the loIst cost next available node in order to run. Thus, using
priorityQueue will significantly allow us to reduce the runtime of picking the next 
vertex to visit and thus the runtime will be reduced from O(N) to O(1) because it will
help find the vertex with the smallest Iight instead of comparing every vertex with 
each other
