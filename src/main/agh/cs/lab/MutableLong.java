package agh.cs.lab;

public class MutableLong {

    private long value;

    public MutableLong(long value) {
        this.value = value;
    }

    public MutableLong(){
        this(0);
    }

    public void increment(){
        value++;
    }

    public void decrement(){
        value--;
    }

    public void change(int newValue){
        value = newValue;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

}
