package agh.cs.lab;

import java.util.Arrays;
import java.util.Random;

public class Genotype {

    private final int[] genes = new int[32];

    private final Random generator = new Random();

    private final boolean[] ifGenePresent = new boolean[8];

    public Genotype() {
         for( int i=0; i<8; i++){
             genes[i] = i;
         }

         for (int i=8; i<32; i++){
             genes[i] = generator.nextInt(8);
         }
    }

    public Genotype(Genotype g1, Genotype g2) {
        int[] parentGenes1 = g1.getGenes();
        int[] parentGenes2 = g2.getGenes();
        int cut1 = generator.nextInt(30) + 1;
        int cut2 = generator.nextInt(32 - cut1 - 1) + 1 + cut1;
        System.arraycopy(parentGenes1, 0, this.genes, 0, cut1);
        System.arraycopy(parentGenes2, cut1, this.genes, cut1, cut2 - cut1);
        System.arraycopy(parentGenes1, cut2, this.genes, cut2, 32 - cut2);
        while (!areAllGenesPresent()) {
            for (int i = 0; i < 8; i++) {
                if (!ifGenePresent[i]) {
                    genes[generator.nextInt(32)] = i;
                }
            }
        }
        Arrays.sort(this.genes);
    }

    public int[] getGenes() {
        return genes.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genotype genotype = (Genotype) o;
        return Arrays.equals(genes, genotype.genes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(genes);
    }

    private boolean areAllGenesPresent() {
        for (int gene : genes) {
            ifGenePresent[gene] = true;
        }

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
