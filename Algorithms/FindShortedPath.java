// http://www.careercup.com/question?id=20786668

public static void findShortestPath(Graph g, int start, int stop)
	{
		g.getEdges(start);
		Queue <Integer> toVisit = new LinkedList<>();
		Set <Integer> alreadyVisited = new HashSet<>();

		Map <Integer, Integer> parent = new HashMap<>();
		alreadyVisited.add(start);
		toVisit.add(start);
		
		parent.put(start, null);
		while(!toVisit.isEmpty())
		{
			int cur = toVisit.poll();
			//We win
			if(cur == stop)
			{
				Integer at = cur;
				while(at != null)
				{
					System.out.print(at + ", ");
					at = parent.get(at);
				}
				return;
			}
			for(Edge e : g.getEdges(cur))
			{
				if(!alreadyVisited.contains(e.getVertexTo()))
				{
					parent.put(e.getVertexTo(), cur);
					alreadyVisited.add(e.getVertexTo());
					toVisit.offer(e.getVertexTo());
				}
			}
		}
		//We lose
		System.out.println("Couldn't find");
	}
