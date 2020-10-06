package com.example.demo.utils.classificador;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Stopwords;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Classificador {

    private Instances instance;
    private NaiveBayesMultinomialText nb;

    public Classificador() {

    }

    public void carregaDataSet() throws Exception {
        nb = new NaiveBayesMultinomialText();
        DataSource dataSource = new DataSource("src/main/resources/datasets/Output.arff");
        this.instance = dataSource.getDataSet();
        this.instance.setClassIndex(1);
        this.nb.buildClassifier(instance);
    }

    public boolean classificar(String mensagem) throws Exception {
        carregaDataSet();
        String mensagemProcessada = removerStopWords(mensagem);

        Instance novo = new DenseInstance(2);
        novo.setDataset(this.instance);

        novo.setValue(0, mensagemProcessada);
        double[] classificacao = nb.distributionForInstance(novo);

        return classificacao[2] > classificacao[0];
    }

    private String removerStopWords(String mensagem) throws Exception {
        // Remover stopwords
        Stopwords stopwords = new Stopwords();
        stopwords.read("src/main/resources/datasets/englishST.txt");
        String sw = stopwords.toString();
        List<String> words = new ArrayList<>(Arrays.asList(mensagem.split(" ")));
        List<String> mensagemProcessada = new ArrayList<>();

        for (String i : words) {
            if (!sw.contains(i)) {
                mensagemProcessada.add(i);
            }
        }

        return mensagemProcessada.toString();
    }
}
