Homework 3: Readme.txt
DFS Spanning Tree

Introduction: The following Java program represents a Depth First Search(DFS) Spanning tree of a given graph. A DFS spanning tree is a result of traversing through a given graph or tree using Depth-First Search algorithm. 

Depth-First Search:
DFS-recursive(G, s)
	mark s as visited
	for all neighbours w of s in graph GL
	if w is not visited:
		DFS-recursive(G, w)


Files included in Project:
	Buffer.java
	Message.java
	Processor.java
	Main.java

Project Design:
	Starting with the skeleton code given by Professor Tanjua we initially created a graph, and populated it with processors and a list (EX: p0.unexplored;) for each processor in the graph with it's neighbours (EX: p0.unexplored = p0neighbors;). Using this list of neighbour relation we designed our DFS algorithm to traverse the graph. 
		
	Description of the DFS Spanning Tree for a specified root:
	
		1. Each node (Processor) has a set of unexplored neighbouring nodes (unexplored = new ArrayList<>()) and a set of nodes that will be its children (children = new ArrayList<>()) in the spanning tree. 
	
		2. Initially the root sends a message <M> to one of its neighbours, and removes that neighbour off the unexplored list. 
		
		3. When a node p_i receives <M> for the first time from some node p_j, p_i marks p_j as it's parent node in the spanning tree. Then p_i chooses one of the nodes in it's unexplored list and forwards a message <M> to it. If p_i does not receive <M> for the first time, it sends a message of type <ALREADY> to p_j and removes p_j from unexplored. Id unexplored is empty, p_i sends a message of type <PARENT> to it's parent node.
		
		4. When a node p_i receives a message of type <PARENT> or <ALREADY>, it sends <M> to one of the nodes in unexplored. If p_i has received <M> or a message of type <PARENT> or <ALREADY> from all of it's neighbours, p_i terminates.
	
	When sending messages to a node our design uses the method sendMessageToMyBuffer using arguments Message and Processor. The messages is one of three: <M>, <ALREADY>, and <PARENT>. The Processor is the processor/node which is sending the message. This choice to add an extra argument to the given method was so that we could keep track of which processor was sending the message. We decided to add two extra variables to the Processor class to make keeping track of the processor and the parent.  
	
	We choose to use switch statements to deal with the three different cases of messages: <M>, <ALREADY>, and <PARENT> in the update method. 
	
	In the explore method we implemented how the tree would be spanned. Based on the order of the unexplored list the node will choose which neighbour node to send a message to. We choose to list the neighbours in chronological order.   

	
	We used interfaces instead of concrete classes as instructed. The benefits of using interfaces is that we can write another implementation later on without having to change the code. We used the "List" interface which has numerous implementations like ArrayList, LinkedList, Stack, and Vector. By using list we have the option to using many different implementations of List. 

Algorithm analysis:
	
	The complexity of the DFS algorithm is O(V + E). V being the vertices and E being the edges. 
		
Tester Cases: 

	We choose to test multiple different nodes as the initial root. With each different root the resulting DFS Spanning tree was different each time. Verifying what the program outputs to the actual traversal of the algorithm of the tree confirmed that our program was executing the DFS algorithm correctly.  
	

