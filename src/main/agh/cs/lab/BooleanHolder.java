package agh.cs.lab;

public class BooleanHolder {

    private boolean value;

    public BooleanHolder(){
        value = false;
    }

    public boolean getValue(){
        return value;
    }

    public void switchValue(){
        value = !value;
    }

}
