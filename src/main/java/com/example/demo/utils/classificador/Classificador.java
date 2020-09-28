package com.example.demo.utils.classificador;

import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Stopwords;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Classificador {

    private final Instances instance;
    private final NaiveBayesMultinomialText nb = new NaiveBayesMultinomialText();

    public Classificador() throws Exception  {
        DataSource dataSource = new DataSource("src/main/resources/datasets/Output.arff");
        this.instance = dataSource.getDataSet();
        this.instance.setClassIndex(1);
        this.nb.buildClassifier(instance);
    }

    public double[] classificar(String mensagem) throws Exception {
        String mensagemProcessada = removerStopWords(mensagem);

        Instance novo = new DenseInstance(2);
        novo.setDataset(this.instance);

        novo.setValue(0, mensagemProcessada);
        return nb.distributionForInstance(novo);
    }

    private String removerStopWords(String mensagem) throws Exception {
        Stopwords stopwords = new Stopwords();
        stopwords.read("src/main/resources/datasets/englishST.txt");
        String sw = stopwords.toString();
        List<String> words = new ArrayList<String>(Arrays.asList(mensagem.split(" ")));
        List<String> mensagemProcessada = new ArrayList<String>();

        for (String i : words) {
            if (!sw.contains(i)) {
                mensagemProcessada.add(i);
            }
        }

        return mensagemProcessada.toString();
    }
}
