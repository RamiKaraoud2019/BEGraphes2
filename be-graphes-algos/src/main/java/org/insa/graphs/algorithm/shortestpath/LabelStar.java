package org.insa.graphs.algorithm.shortestpath;



import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class LabelStar extends Label{
	
	private double coutEstime;
 

	public LabelStar(Node a, boolean m, double c,  Arc p, Node destination) {
		super(a,m,c,p);
		this.coutEstime = a.getPoint().distanceTo(destination.getPoint()) ;

		
	}
	

		public double getcoutEstime() {
			return this.coutEstime;
		}
	
		public double getcoutTotal() {
			return this.getCost() + this.coutEstime;
		}
		
	    @Override
	    public int compareTo(Label o) {
	        LabelStar other = (LabelStar) o;
	    	if (Double.compare(this.getTotalCost(), other.getTotalCost()) == 0) {
	    		return Double.compare(this.getcoutEstime(), other.getcoutEstime());
	    	}
	    	else {
	    		return Double.compare(this.getTotalCost(), other.getTotalCost());
	    	}
	    }
	
	
}
