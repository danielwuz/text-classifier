package edu.nyu.cs.training;

/**
 * Classifier interface
 * 
 * @author daniel
 *
 */
public interface Classifier {
	
	/**
	 * Classify the given text
	 * 
	 * @param text text to be classified
	 * @return category of given text
	 */
	String classify(String text);
}
