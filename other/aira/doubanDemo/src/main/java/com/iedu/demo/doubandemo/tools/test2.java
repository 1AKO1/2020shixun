package com.iedu.demo.doubandemo.tools;

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.iedu.demo.doubandemo.tools.TestUtility;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class test2 {
    /**
     * 中文情感挖掘语料-ChnSentiCorp 谭松波
     */
    public static final String CORPUS_FOLDER = TestUtility.ensureTestData("kaguyasama", "http://file.hankcs.com/corpus/ChnSentiCorp.zip");

    public static void main(String[] args) throws IOException
    {
        System.out.println("=======" +CORPUS_FOLDER);
        IClassifier classifier = new NaiveBayesClassifier(); // 创建分类器，更高级的功能请参考IClassifier的接口定义
        classifier.train(CORPUS_FOLDER);                     // 训练后的模型支持持久化，下次就不必训练了

        predict(classifier, "太垃圾了");
        predict(classifier, "这部番我吹爆，四宫辉夜天下第一");
        predict(classifier, "剧情一般，人设一般");
    }

    private static void predict(IClassifier classifier, String text)
    {
        System.out.printf("《%s》 情感极性是 【%s】\n", text, classifier.classify(text));
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
}
