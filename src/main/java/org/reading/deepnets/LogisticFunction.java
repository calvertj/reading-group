package org.reading.deepnets;

public class LogisticFunction {

	private double b;
	private double[] w;
	
	public LogisticFunction(double b, double[] ws) { 
		this.b = b; 
		this.w = ws;
	}
	
	public double evaluate(int... inputs) { 
		if(inputs.length != w.length) { 
			throw new IllegalArgumentException(String.format("Expecting %d inputs.", w.length));
		}
		
		double sum = 0.0;
		for(int i = 0; i < w.length; i++) { 
			sum += w[i] * inputs[i];
		}
		double denom = 1.0 + Math.exp(-b - sum);
		return 1.0 / denom;
	}
}
