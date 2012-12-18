package org.reading.deepnets;

public class TransposeLogisticFunction {
	
	private double b;
	private double[][] ws;
	private int j;
	
	public TransposeLogisticFunction(double b, double[][] ws, int i) { 
		this.b = b; 
		this.ws = ws;
		this.j = i;
	}
	
	public double evaluate(int... inputs) { 
		if(inputs.length != ws.length) { 
			throw new IllegalArgumentException(String.format("Expecting %d inputs.", ws.length));
		}
		
		double sum = 0.0;
		for(int i = 0; i < ws.length; i++) { 
			sum += ws[i][j] * inputs[i];
		}
		double denom = 1.0 + Math.exp(-b - sum);
		return 1.0 / denom;
	}
}
