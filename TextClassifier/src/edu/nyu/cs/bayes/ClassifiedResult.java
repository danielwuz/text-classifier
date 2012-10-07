package edu.nyu.cs.bayes;

public class ClassifiedResult implements Comparable<ClassifiedResult> {

	public double probability = 0;

	public String category;

	public ClassifiedResult() {
		this.probability = 0;
		this.category = null;
	}

	public ClassifiedResult(String category, Double probability) {
		this.probability = probability;
		this.category = category;
	}

	@Override
	public int compareTo(ClassifiedResult o) {
		return this.probability > o.probability ? 1 : -1;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "class " + category + " with probability " + probability;
	}

}
