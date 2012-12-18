package org.reading.deepnets;

import java.util.Random;

public class RBM {
	
	private static Random rand = new Random();

	private int numHidden, numVisible;
	private int[] hidden, visible;
	
	private double[] bias;  // bias.length == visible.length
	private double[][] weights; // weights.length == visible.length, weights[i].length == hidden.length
	
	public RBM(int numHidden, int numVisible) { 
		this.numHidden = numHidden;
		this.numVisible = numVisible;
	}
	
	public void sampleSteps(int n) { 
		for(int k =0; k < n; k++) { sampleStep(); }
	}
	
	public void sampleStep() { 
		sampleHidden();
		sampleVisible();
	}
	
	public void setVisible(int... v) { 
		if(v.length != visible.length) { throw new IllegalArgumentException(String.format("Expecting array of length %d", visible.length)); }
		for(int i = 0; i < v.length; i++) { visible[i] = v[i]; }
	}
	
	public void sampleVisible() {
		for(int i = 0; i < visible.length; i++) {
			double pthresh = (new LogisticFunction(bias[i], weights[i])).evaluate(hidden);
			double psample = rand.nextDouble();
			visible[i] = psample <= pthresh ? 1 : 0;
		}
	}
	
	public void sampleHidden() { 
		for(int j = 0; j < hidden.length; j++) { 
			double pthresh = (new TransposeLogisticFunction(0.0, weights, j)).evaluate(visible);
			double psample = rand.nextDouble();
			hidden[j] = psample <= pthresh ? 1 : 0;
		}
	}
}
