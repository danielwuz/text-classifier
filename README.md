# Naive Bayes based Text Classification

## Methodology

* Develop environment

Our project will be written in Java, using lucene for text analysis, log4j for logging statistic information, so that we can focus on the implementation of different text classification algorithms. 

* How does this software work?

Our software will take a batch of files as training data, as well as user specified test files. The effectiveness and correctness running algorithm under such data will be written into log files. After well trained, this software can be used to classify new text files by appending label to file names or moving them to different directories, depending on user choice.

* What algorithms will be employed?
Naïve Bayes, Decision Tree(C4.5), 

* How to get training data? 

For English training set, we employ a collection of over 20,000 messages which from 20 different newsgroups from following URL:
http://www.cs.cmu.edu/afs/cs.cmu.edu/project/theo-20/www/data/news20.html

For Chinese training data, we use Sogou lab data with approximate 10,000 news documents from following URL provided by Sogou, one of the greatest Internet companies in China.
http://www.sogou.com/labs/dl/c.html

* What's the input? What's the output? How to evaluate the accuracy of output?

Input contains two parts: training data files and test data files. Both of them should be in text formation with encoding UTF-8. The file directory indicates its category, partitioned by .[DOT]. For example, data files concerning about newsgroups of baseball records are under directory “rec.sport.baseball”. 

Output contains two parts: classified text files and a report of evaluation information. There are three options for test data output: evaluation, labeling and classifying. With “evaluation” option, our software do nothing with the test data except merely using it to evaluate the effectiveness and correctness of the algorithms. And “classifying” option, which has more practical usage, represents that the test data files will be partitioned into different category directories, while “labeling” option provides a much faster performance by appending  category label to file names to reduce I/O operations.

In order to evaluate the effectiveness and accuracy of one particular algorithm, we provide 4 options: use the training set, supplied data set, cross-validation, percentage split. The results from different approaches are compared and will be included in the final report.