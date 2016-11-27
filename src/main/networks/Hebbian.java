package main.networks;

import main.io.DataSet;

import java.util.ArrayList;
import java.util.Random;

import static main.Main.LEARNING_RATE;
import static main.io.DataSave.*;

public class Hebbian {

    private double w1,w2,signalSum,outSignal;
    private Random r = new Random();

    public Hebbian() {

        w1=((double)r.nextInt(11))/10.0;
        w2=((double)r.nextInt(11))/10.0;
        signalSum = 0.0;
    }

    public void train(ArrayList<DataSet> records, int attempts) {

        for (int i=0;i<attempts;i++){

            int t = 0;

            System.out.println("|_Proba: "+ i + "_|");

            // t - ilość iteracji ( można ustalić maksymalną ilość iteracji po której sieć albo się nauczy albo nie )

            // Start stoper - time learning for one attempt
            long start_time = System.currentTimeMillis();

            while (t<40) {

                t++;

                for(DataSet d:records) {

                    signalSum = w1*d.getX() + w2*d.getY();
                    outSignal = sigmoidal(signalSum);

                    d.setOut(outSignal);

                    //standardHebbianRule(d);
                    //hebbianRuleWithForgettingCoefficient(d);
                    ojiRule(d);

                    System.out.println("WEIGHTS DURING TRAINING: " + w1 + "  " + w2);
                }

                System.out.println("WEIGHTS AFTER ITERATION: " + w1 + "  " + w2);
            }

            long end_time = System.currentTimeMillis();
            long difference = end_time - start_time; // time in miliseconds

            System.out.println(difference + " miliseconds");

            saveTime("wyniki_3", i, difference);
            saveNumberOfEpochs("wyniki_3", i, t);

            // Zostawiam ostatnią próbę aby testować już na zmodyfikowanych wagach
            if (i != attempts-1){

                Random rand = new Random();
                w1=((double)rand.nextInt(11))/10.0;
                w2=((double)rand.nextInt(11))/10.0;

                System.out.println("LAST ITERATION: " + t);
            }
        }

        saveAverageTime("wyniki_3",attempts);
        saveAverageNumberOfEpochs("wyniki_3",attempts);
    }

    private void standardHebbianRule(DataSet d){

        w1 = w1 + LEARNING_RATE*d.getX()*d.getOut();
        w2 = w2 + LEARNING_RATE*d.getY()*d.getOut();

    }

    private void hebbianRuleWithForgettingCoefficient(DataSet d){

        w1 = LEARNING_RATE*d.getOut()*(d.getX()-d.getOut()*w1);
        w2 = LEARNING_RATE*d.getOut()*(d.getY()-d.getOut()*w2);

    }

    private void ojiRule(DataSet d){

        w1 = (1-d.getOut())*w1 + LEARNING_RATE*d.getX()*d.getOut();
        w2 = (1-d.getOut())*w2 + LEARNING_RATE*d.getY()*d.getOut();

    }

    public double test(double a, double b) {

        signalSum = w1*a + w2*b;
        return sigmoidal(signalSum);
    }

    private int bipolar(double a) {
        if(a>=0) return 1;
        else return -1;
    }

    private double sigmoidal(double a){
        double beta = 1.0;
        return (1.0/(1.0 + Math.pow(Math.E,-(beta*a))));
    }
}
