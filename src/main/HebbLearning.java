package main;

import main.hebb.neurons.HebbNeuron;
import main.hebb.networks.HebbianNetwork;

import java.util.ArrayList;

public class HebbLearning {

    public static void main(String[] args) {

        // Uczenie Hebba bez nauczyciela

        double x,y;
        ArrayList<HebbNeuron> records = new ArrayList<>();

        // Uczenie

        records.add(new HebbNeuron(0.47,0.9));
        //records.add(new HebbNeuron(1,-1));
        records.add(new HebbNeuron(0.8,0.2));
        //records.add(new HebbNeuron(-1,-1));
        HebbianNetwork h = new HebbianNetwork();
        h.train(records, 50);

        // Walidacja

        x = 0.5;
        y = 0.78;

        System.out.println("OUTPUT:"+h.test(x,y));
    }
}
