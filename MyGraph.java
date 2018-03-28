//Max Tran, CSE 373 HW#4, 2/22/17
//This class is used to represent a graph ADT and use it to implement
//Dijkstra's algorithm. The graph abstraction will be protected by 
//leveraging copy-in-copy-out and immutability as we discussed in class
import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
    // you will need some private fields to represent the graph
    // you are also likely to want some private helper methods

    // YOUR CODE HERE
   private static final Integer INFINITY_VALUE = Integer.MAX_VALUE;
   private final Map<Vertex, Set<Edge>> myVertexEdgeGraph;
   private final Map<Vertex, Set<Vertex>> myVertexVertexGraph;
   private final Set<Edge> myEdgeSet;
    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
   public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
      myEdgeSet = new HashSet<>();
    	myVertexEdgeGraph = new HashMap<>();
    	myVertexVertexGraph = new HashMap<>();
    	for (Edge edgeItem : e) {
         Edge copyOfEdge = new Edge(edgeItem.getSource(),edgeItem.getDestination(),edgeItem.getWeight());
         if (copyOfEdge.getWeight() < 0) {
    			throw new IllegalArgumentException("Edge " + copyOfEdge.toString() + 
    		      " has negative weight: " + copyOfEdge.getWeight());
    		} else {
	    		Vertex mySource = new Vertex(copyOfEdge.getSource().getLabel());
	    		Vertex myDestination = new Vertex(copyOfEdge.getDestination().getLabel());
	    		// add to structures if valid, ignore vertices with no edge
	    		if (v.contains(mySource) && v.contains(myDestination)) {
	    			if (!myVertexEdgeGraph.containsKey(mySource)) {
	    				myVertexEdgeGraph.put(mySource, new HashSet<Edge>());
	    			}
	    			if (!myVertexVertexGraph.containsKey(mySource)) {
	    				myVertexVertexGraph.put(mySource, new HashSet<Vertex>());
	    			}
	    			myVertexVertexGraph.get(mySource).add(myDestination);
	    			myVertexEdgeGraph.get(mySource).add(copyOfEdge);
	    			if (myEdgeSet.contains(copyOfEdge)) {
	    				throw new IllegalArgumentException("Edge " + copyOfEdge.toString() +
	    						" is duplicated");
	    			}
	    			myEdgeSet.add(copyOfEdge);
	    		} else {
	    			throw new IllegalArgumentException("Vertices in edge " + copyOfEdge.toString() +
	    					" is not valid");
	    		}
    		}
    	}
   }

    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
   public Collection<Vertex> vertices() {
      Collection<Vertex> copyOfMyVertexVertexGraph = new ArrayList<Vertex>();
      for(Vertex vertex : myVertexVertexGraph.keySet()){
         Vertex copyOfVertex = new Vertex(vertex.getLabel());
         copyOfMyVertexVertexGraph.add(copyOfVertex);
      }
      return copyOfMyVertexVertexGraph;
   }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
   public Collection<Edge> edges() {
      Collection<Edge> copyOfMyEdgeSet = new ArrayList<Edge>();
      for(Edge edge : myEdgeSet){
         Edge copyOfEdge = new Edge(edge.getSource(),edge.getDestination(),edge.getWeight());
         copyOfMyEdgeSet.add(copyOfEdge);
      }
      return copyOfMyEdgeSet;
   }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
   public Collection<Vertex> adjacentVertices(Vertex v) {
      Vertex paramVertex = new Vertex(v.getLabel());
      isExistent(paramVertex);
    	Collection<Vertex> adjVertexSet = new ArrayList<Vertex>();
      for(Vertex vertex : myVertexVertexGraph.get(paramVertex)){
         Vertex copyOfVertex = new Vertex(vertex.getLabel());
         adjVertexSet.add(copyOfVertex);
      }
    	return adjVertexSet;
   }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
   public int edgeCost(Vertex a, Vertex b) {
      isExistent(a);
      isExistent(b);
      int cost = - 1;
      Vertex copyOfA = new Vertex(a.getLabel());
      Vertex copyOfB = new Vertex(b.getLabel());
      Collection<Edge> copyOfMyVertexEdgeGraph = new ArrayList<Edge>();
      copyOfMyVertexEdgeGraph.addAll(myVertexEdgeGraph.get(copyOfA));
      for(Edge edge : copyOfMyVertexEdgeGraph){
    		if (edge.getDestination().equals(copyOfB)) {
            Edge copyOfEdge = new Edge(copyOfA,copyOfB,edge.getWeight());
    			cost = copyOfEdge.getWeight();
    		}
      }
      return cost;
   }
   
   /* A wrapper class used to compare vertices in shortestPath 
      which implements the comparable class*/
   public class vertWrapperInfo implements Comparable<vertWrapperInfo> {
      // Class contains current vertex it's previous vertex and a cost */
      private Vertex currVertex;
      private Vertex prevVertex;
      private int vertexCost;

      // Initializing a onstructor class for vertWrapperInfo */
      public vertWrapperInfo(Vertex currVertex, Vertex prevVertex, int vertexCost) {
          this.currVertex = currVertex;
          this.prevVertex = prevVertex;
          this.vertexCost = vertexCost;
      }
      
      /* Comparing the two vertWrapperInfo class by calculating 
      the difference in their vertexCost*/
      public int compareTo(vertWrapperInfo other) {
          return this.vertexCost - other.getVertexCost();
      }
      
      // Returns the current vertex */
      public Vertex getCurrVertex() {
         return currVertex;
      }

      // Returns the previous vertex 
      public Vertex getPrevVertex() {
          return prevVertex;
      }

      // Returns the vertex cost
      public int getVertexCost() {
          return vertexCost;
      }
   }
   
   /* return the shortest path from a to b, or null if there is no shortest path using
   Dijkstra's algorithm. Assuming all edgeweights are non-negative. 
   @Param a: the starting vertex 
   @Param b: the destination vertex.
   @Throw IllegalArgumentException if vertex a or b doesn't exist*/
   public Path shortestPath(Vertex a, Vertex b){
      isExistent(a);
      isExistent(b);
      Map<Vertex, vertWrapperInfo> vertMap = new HashMap<Vertex, vertWrapperInfo>();
      for (Vertex v : vertices()) {
            vertMap.put(v, new vertWrapperInfo(v, null, INFINITY_VALUE));
      }
      // Create a PriorityQueue for vertWrapperInfo
      PriorityQueue<vertWrapperInfo> vertQueue = new PriorityQueue<vertWrapperInfo>();
      //Copy vertex A and B and create a verTexWrapperInfo for vertex A
      Vertex copyOfA = new Vertex(a.getLabel());
      Vertex copyOfB = new Vertex(b.getLabel());
      vertWrapperInfo vw_a = new vertWrapperInfo(copyOfA, null, 0);
      // add the vertexWrapperInfo of A to a priorityQueue and map vertex A to the its wrapper class
      vertMap.put(a, vw_a);
      vertQueue.add(vw_a);      
      while(!vertQueue.isEmpty()) {
         Vertex curr = vertQueue.poll().getCurrVertex();
         /* look through all the adjacent Vertices the current Vertex and compute the cost from v to 
         current vertex. Remove that adjacent vertices from pq if the cost through current vertex is lower*/
         for (Vertex v : adjacentVertices(curr)) {         
            int vertexCost = vertMap.get(curr).getVertexCost() + edgeCost(curr, v);
            if (vertexCost < vertMap.get(v).getVertexCost()) {
               vertQueue.remove(vertMap.get(v));
               vertWrapperInfo vertwi = new vertWrapperInfo(v, curr, vertexCost);               
               vertQueue.add(vertwi);
               vertMap.put(v,vertwi);
            }
         }
      }
      // create a path using ArrayList
      List<Vertex> vertexPath = new ArrayList<Vertex>();        
      // Add every vertex and it's prevVertex to path until the for loop reaches null
      for (Vertex v = copyOfB; v != null; v = vertMap.get(v).getPrevVertex()) {
         vertexPath.add(v);
      }
      //reverse order of path
      Collections.reverse(vertexPath);
      if(vertexPath.contains(copyOfA)){
         Path pathAToB = new Path(vertexPath, vertMap.get(copyOfB).getVertexCost());
         return pathAToB;
      } else {
         return null;
      }
   }
   
   //test whether the passed-in vertex exists or not and throw 
   //IllegalArgumentException if it does
   private void isExistent(Vertex v) {
      Vertex vertex = new Vertex(v.getLabel());
    	if (!vertices().contains(vertex)) {
     		throw new IllegalArgumentException("There is no vertex " + vertex.toString() + " in graph");
     	}
   }
}
