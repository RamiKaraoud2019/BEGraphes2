package org.insa.graphs.algorithm.shortestpath;



import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.insa.graphs.model.*;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.AbstractSolution.Status;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        ///


        
        Graph g = data.getGraph();
        List<Node> n = g.getNodes();
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        Label[] labels = new Label[g.size()];
        Node o = data.getOrigin();

        
        for (Node node : n ) {
        		labels[node.getId()] = new Label(node,false,Float.POSITIVE_INFINITY,null);
        }
        
        labels[o.getId()].setCost(0);
        tas.insert(labels[o.getId()]);
        
        
        
        notifyOriginProcessed(o);
        
        while( labels[data.getDestination().getId()].isMarque() == false && tas.isEmpty() == false) {
        	Label labelCourrant = tas.deleteMin();
        	labelCourrant.marquer();
        	Node noeudCourant = labelCourrant.getNode();
        	
        	
        	
        	notifyNodeMarked(noeudCourant);
        	
        	
        	
      
        	
        	for (Arc arc : noeudCourant.getSuccessors()) {
        		
        		
        		
		        Node noeudDestination = arc.getDestination();
		        Label labelDestination = labels[noeudDestination.getId()];
		        		
		        double cout = data.getCost(arc) + labelCourrant.getCost();
		        
		        
                if (!data.isAllowed(arc)) {
                    continue;
                }
		        		
		        if (!labelDestination.isMarque() && labelDestination.getCost()>cout) 
		        {
		        	if (labelDestination.getPere()==null) { 
		        		labelDestination.setPere(arc);
		        		labelDestination.setCost(cout);
		        		tas.insert(labelDestination);
			            
		        		
		        		
		        		notifyNodeReached(noeudDestination);
			            
		        		
		        		
			        }	
		        	else {
		        		tas.remove(labelDestination); 
		        		labelDestination.setPere(arc);
		        		labelDestination.setCost(cout);
		        		tas.insert(labelDestination);
			            
		        		
		        		
		        		notifyNodeReached(noeudDestination);
			    
		        	}
		        	
		        }
	        		
  
        	}
        	
        	
        }
        
        Node dest = data.getDestination();
        Label labelDest = labels[dest.getId()];
        
        if (labelDest.getPere()== null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
       
        else {
        
            notifyDestinationReached(dest);
            
            
            
            
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc a = labelDest.getPere();
            
            while (a != null) {
                arcs.add(a);
                labelDest = labels[a.getOrigin().getId()];
                a = labelDest.getPere();
            }

            Collections.reverse(arcs);
           
            Path path= new Path(g, arcs);
            
            
            
            if(path.isValid()) {
            	return new ShortestPathSolution(data, Status.OPTIMAL, new Path(g, arcs));
            	}
            
            	solution = null;
            	
            
        }
        
        
        
        ///
        return solution;
    }

}
