package edu.nyu.cs.test;

import java.io.IOException;

import edu.nyu.cs.bayes.NaiveBayesClassifier;
import edu.nyu.cs.training.Classifier;
import edu.nyu.cs.training.DataManager;

/**
 * Testing driver for accuracy test.
 * <p>
 * This test uses cross-validation against training texts
 * 
 * @author Daniel Wu
 * 
 */
public class TestDriver {

	private final DataManager dataManager;

	/**
	 * Class constructor with training corpus path
	 * 
	 * @param filepath
	 */
	public TestDriver(String filePath) {
		// initialize data manager
		dataManager = DataManager.getInstance(filePath);
	}

	/**
	 * Program entry.
	 * 
	 * @param args
	 *            args[0]:training data path
	 */
	public static void main(String[] args) {
		// read in test documents
		new TestDriver(args[0]).test();
		// classify ,compare results
	}

	/**
	 * Starts to do cross-validation
	 */
	public void test() {
		// number of correct classification
		double correctNum = 0;
		// total number of test
		double totalNum = 0;
		// Naive Bayes Classifier
		Classifier classifier = new NaiveBayesClassifier();
		// holds text classes
		String[] classifications = dataManager.getClassifications();
		for (String category : classifications) {
			String[] filePathList = null;
			try {
				// read out all files
				filePathList = dataManager.getFilesPath(category);
				for (String filePath : filePathList) {
					String text = dataManager.getText(filePath);
					// test on each file
					String result = classifier.classify(text);
					if (result.equals(category)) {
						correctNum++;
					}
					totalNum++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// output stats
		System.out.println("total num: " + totalNum + " correct num: "
				+ correctNum + " and accuracy: " + correctNum / totalNum);
	}
}
