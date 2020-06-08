package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.*;

public class Label implements Comparable<Label> {
	
	private Node sommetCourant;
	private boolean marque;
	private double cout;
	private Arc pere;
	

	
	public Label(Node sC, boolean m, double c,  Arc p) {
		this.sommetCourant = sC;
		this.marque = m;
		this.cout = c;
		this.pere =p;
	}
	
	public void setPere(Arc p) {
		this.pere = p;
	}
	
	public Arc getPere() {
		return this.pere;
	}
	
	public boolean getMark() {
		return this.marque;
	}
	
	
	
	public boolean isMarque() {
		return this.marque;
	}
	
	
	public double getCost() {
		return this.cout;
	}
	
	public double getTotalCost() {
		return this.cout;
	}
	
	public void setCost(double c) {
		this.cout = c;
	}
	
	
	
	public Node getNode() {
		return this.sommetCourant;
	}
	
	public void marquer() {
		this.marque = true;
	}
	
	public int compareTo(Label other) {
        return Double.compare(getTotalCost(), other.getTotalCost());
    }
	
	
	
}
