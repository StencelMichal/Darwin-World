package agh.cs.lab;

import java.util.Arrays;
import java.util.Random;

public class Genotype {

    private final int[] genes = new int[32];

    private final Random generator = new Random();

    public Genotype() {
         for( int i=0; i<8; i++){
             genes[i] = i;
         }

         for (int i=8; i<32; i++){
             genes[i] = generator.nextInt(8);
         }
    }

    public int[] getGenes() {
        return genes;
    }

    public Genotype(Genotype g1, Genotype g2){
        int[] parentGenes1 = g1.getGenes();
        int[] parentGenes2 = g2.getGenes();
        int cut1 = generator.nextInt(30) + 1;
        int cut2 = generator.nextInt(32 - cut1 - 1) + 1 + cut1;
        System.arraycopy(parentGenes1, 0, this.genes, 0, cut1);
        if (cut2 - cut1 >= 0) System.arraycopy(parentGenes2, cut1, this.genes, cut1, cut2 - cut1);
        if (32 - cut2 >= 0) System.arraycopy(parentGenes1, cut2, this.genes, cut2, 32 - cut2);
        Arrays.sort(this.genes);
        boolean[] ifGenePresent = new boolean[8];
        for (int gene : genes) {
            ifGenePresent[gene] = true;
        }
        while(!areAllGenesPresent(ifGenePresent)){
            //trzeba poprawić - czasem szuka w nieskończoność
            for (int i=0; i<8; i++){
                if(!ifGenePresent[i]){
                    genes[generator.nextInt(32)] = i;
                }
            }
        }
    }

    private boolean areAllGenesPresent(boolean[] ifGenePresent) {
        for (boolean gene : ifGenePresent) {
            if (!gene) {
                return false;
            }
        }
        return true;
    }

    public int randomGene(){
        return genes[generator.nextInt(32)];
    }
}
