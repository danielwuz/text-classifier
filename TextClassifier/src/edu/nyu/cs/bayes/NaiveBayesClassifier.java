package edu.nyu.cs.bayes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.nyu.cs.split.ChineseSpliter;
import edu.nyu.cs.split.TextProcessor;
import edu.nyu.cs.training.Classifier;
import edu.nyu.cs.training.DataManager;

public class NaiveBayesClassifier implements Classifier {

	/**
	 * Calculate the probability of given text in the given category
	 * 
	 * @param terms
	 *            terms of given text
	 * @param category
	 *            category
	 * @return probability to be classified into given category
	 */
	public ClassifiedResult calculateProbability(String[] terms, String category) {
		double probability = 1.0D;

		for (String keyWord : terms) {
			Double prob = ConditionalProbability.instance().calculate(keyWord,
					category);
			probability *= -Math.log10(prob);
		}
		probability *= PriorProbability.calculatePc(category);

		return new ClassifiedResult(category, probability);
	}

	public String classify(String text) {
		TextProcessor processor = new TextProcessor(new ChineseSpliter());
		String[] terms = processor.process(text);

		String[] classes = DataManager.getInstance().getClassifications();
		List<ClassifiedResult> result = new ArrayList<ClassifiedResult>();
		for (String category : classes) {
			ClassifiedResult classified = calculateProbability(terms, category);
			result.add(classified);
			// System.out.println("In progress ...");
			// System.out.println(classified);
		}
		Collections.sort(result);

		return result.get(0).getCategory();
	}

}
