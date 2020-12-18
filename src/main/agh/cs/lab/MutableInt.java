package agh.cs.lab;

public class MutableInt {

    private int value = 0;

    public void increment(){
        value++;
    }

    public void decrement(){
        value--;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
