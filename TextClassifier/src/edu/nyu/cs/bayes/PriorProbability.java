package edu.nyu.cs.bayes;

import edu.nyu.cs.training.DataManager;

public class PriorProbability {

	private static DataManager tdm = DataManager.getInstance();

	public static float calculatePc(String c) {
		float Nc = tdm.getTrainingFileCountOfClassification(c);
		float N = tdm.getTrainingFileCount();
		float result = Nc / N;
		return result;
	}
}
