package agh.cs.lab;

import org.junit.Assert;
import org.junit.Test;

public class GenotypeTest {

    @Test
    public void allGenesTest() {
        for (int i = 0; i < 10000; i++) {
            Genotype g1 = new Genotype();
            Genotype g2 = new Genotype();
            Genotype g3 = new Genotype(g1, g2);
            int[] genes = g3.getGenes();
            boolean[] ifGenePresent = new boolean[8];
            for (int j = 0; j < 32; j++) {
                ifGenePresent[genes[j]] = true;
            }
            for (int j = 0; j < 8; j++) {
                if (!ifGenePresent[j]) {
                    Assert.fail();
                }
            }
        }
    }
}

