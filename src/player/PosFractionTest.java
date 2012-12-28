package player;

import static org.junit.Assert.*;

import org.junit.Test;

public class PosFractionTest {

    @Test(expected=IllegalArgumentException.class)
    public void PosFractionTestInvalidConstructorInput1() {
        new PosFraction("1.5/6"); //test a decimal
    }
    @Test(expected=IllegalArgumentException.class)
    public void PosFractionTestInvalidConstructorInput2() {
        new PosFraction("1*6"); //test some random character
    }
    @Test
    public void PosFractionTest1() {
        PosFraction fraction = new PosFraction("3/4"); //test normal input
        assertTrue(fraction.getNumerator() == 3);
        assertTrue(fraction.getDenominator() == 4);
    }
    @Test
    public void PosFractionTest2() {
        PosFraction fraction = new PosFraction("/"); //test assumed num and denom
        assertTrue(fraction.getNumerator() == 1);
        assertTrue(fraction.getDenominator() == 2);
    }
    @Test
    public void PosFractionTest3() {
        PosFraction fraction = new PosFraction("/4"); //test assumed numerator
        assertTrue(fraction.getNumerator() == 1);
        assertTrue(fraction.getDenominator() == 4); 
    }
    @Test
    public void PosFractionTest4() {
        PosFraction fraction = new PosFraction("4/"); //test assumed denom
        assertTrue(fraction.getNumerator() == 4);
        assertTrue(fraction.getDenominator() == 2); 
    }
    @Test(expected=IllegalArgumentException.class)
    public void PosFractionTestBlank(){ //test an empty string input
        new PosFraction("");
    }
    @Test(expected=IllegalArgumentException.class)
    public void PosFractionDivideByZero(){
        new PosFraction("7/0");
    }
}
