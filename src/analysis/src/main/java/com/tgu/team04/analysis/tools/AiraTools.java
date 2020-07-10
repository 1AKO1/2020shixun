package com.tgu.team04.analysis.tools;

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.tgu.team04.analysis.tools.TestUtility;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLOutput;

import static java.lang.Integer.parseInt;

public class AiraTools {
    /**
     * 中文情感挖掘语料-ChnSentiCorp 谭松波
     */
    public static final String CORPUS_FOLDER = TestUtility.ensureTestData("kaguyasama", "http://file.hankcs.com/corpus/ChnSentiCorp.zip");

    public static int MyClassifier(String text) throws IOException {
        IClassifier classifier = new NaiveBayesClassifier(); // 创建分类器，更高级的功能请参考IClassifier的接口定义
        classifier.train(CORPUS_FOLDER);                     // 训练后的模型支持持久化，下次就不必训练了

        return parseInt(classifier.classify(text));
    }

    private static void predict(IClassifier classifier, String text)
    {
        System.out.printf("《%s》 情感极性是 【%s】\n", text, classifier.classify(text));
        System.out.println(isNumeric(classifier.classify(text)));
    }

    static
    {
        File corpusFolder = new File(CORPUS_FOLDER);
        if (!corpusFolder.exists() || !corpusFolder.isDirectory())
        {
            System.err.println("没有文本分类语料，请阅读IClassifier.train(java.lang.String)中定义的语料格式、准备语料");
            System.exit(1);
        }
    }

    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }
}
