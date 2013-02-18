package edu.nyu.cs.test;

import edu.nyu.cs.bayes.NaiveBayesClassifier;
import edu.nyu.cs.training.Classifier;
import edu.nyu.cs.training.DataManager;

/**
 * This is a sample class to demonstrate how to classify a given text
 * 
 * @author dawu
 * 
 */
public class Sample {

	private String sampleText = "在政府部门明确表示汽车投资过热、产能过剩的背景下，日前新飞集团却逆流而上：专用汽车工业园在河南新乡市开发区正式开始建设。";

	private String trainingCorpusPath = "/Users/dawu/test/text-classifier/TextClassifier/resouces/SogouC.mini/";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Sample().run();
	}

	private void run() {
		// init training data
		DataManager.getInstance(trainingCorpusPath);
		// create classifier
		Classifier classifier = new NaiveBayesClassifier();
		String category = classifier.classify(sampleText);
		System.out.println("Category: " + category);
	}

}
