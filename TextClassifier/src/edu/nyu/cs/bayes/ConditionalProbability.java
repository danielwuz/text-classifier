package edu.nyu.cs.bayes;

import edu.nyu.cs.training.DataManager;

public class ConditionalProbability {

	private static ConditionalProbability instance = null;

	private static DataManager tdm = DataManager.getInstance();

	ProbabilityCache probCache = new ProbabilityCache();

	private final double M = 0f;

	private ConditionalProbability() {
	}

	public static ConditionalProbability instance() {
		if (instance == null) {
			instance = new ConditionalProbability();
		}
		return instance;
	}

	public double calculate(String keyWord, String category) {
		if (probCache.contains(keyWord, category)) {
			return probCache.get(keyWord, category);
		}
		Integer Nxc = tdm.numberOfWord(keyWord, category);
		Integer Nc = tdm.totalNumberOfWords(category);
		Integer V = tdm.getVocabulary();

		Double prob = (Nxc + 1) / (Nc + M + V);
		probCache.add(keyWord, category, prob);

		return prob;
	}
}
