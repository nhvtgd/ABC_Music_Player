package player;

import java.util.ArrayList;
import java.util.regex.Pattern;

//Rep invariant -> numerator >= 0, denominator > 0
public class PosFraction {
    private int numerator;
    private int denominator;
    
    /**
     * Constructor.
     * Requires fracRep has the pattern of [0-9]*(\/?[0-9]*)
     * @param fracRep - string representing a positive fraction. Ex: 4/5, /4, /
     */
    public PosFraction(String fracRep){
        
        //check for invalid pattern and blank input
        if(!Pattern.compile("[0-9]*/?[0-9]*").matcher(fracRep).matches() || Pattern.compile("\\s").matcher(fracRep).matches())
            throw new IllegalArgumentException("Invalid string representation of fraction!");

        //Defaults
        this.numerator = 1;
        this.denominator = 2;
        
        //Check for just a number with no /
        if(!Pattern.compile("/").matcher(fracRep).find()){
            this.numerator = Integer.parseInt(fracRep);
            this.denominator = 1;
        }
        else{
            String[] split = fracRep.split("/"); //split string on '/'
            
            //A '/' as input results in a split length of 0, reverting to defaults
            if(split.length == 1){ // num before, none after
                this.numerator = Integer.parseInt(split[0]);
                this.denominator = 2;
            }
            else if(split.length == 2){
                if(!split[0].equals("")) // default numerator to 1 if none given
                    this.numerator = Integer.parseInt(split[0]);

                if(!split[1].equals("")) // default denominator to 2 if none given
                    this.denominator = Integer.parseInt(split[1]);

                
                if (this.denominator == 0) //denominator can't be 0
                    throw new IllegalArgumentException("Denominator can't be zero!");
            }
        }
    }
    /**
     * Returns the numerator of the fraction
     * @return
     */
    public int getNumerator(){
        return this.numerator;
    }
    /**
     * Returns the denominator of the fraction
     * @return
     */
    public int getDenominator(){
        return this.denominator;
    }
    /**
     * Overrides the toString method
     */
    @Override
    public String toString(){
        return Integer.toString(this.numerator) + "/"+Integer.toString(this.denominator);
    }
    
    /**
     * Compares equality of two PosFraction objects
     * @param other - PosFraction to compare to
     * @return True on equal, false otherwise
     */
    public boolean equals(PosFraction other){
        return this.compareTo(other) == 0;
    }
    
    /**
     * Compares two PosFraction objects
     * @param other - PosFraction to compare to
     * @return 1 if this>other, 0 if this = other, -1 if this<other
     */
    public int compareTo(PosFraction other){
        long firstCross = this.getNumerator() * other.getDenominator();
        long secondCross = other.getNumerator() * this.getDenominator();
        
        int compare = 0;
        if(firstCross > secondCross)
            compare = 1;
        else if(secondCross > firstCross)
            compare = -1;
        
        return compare;
    }
    /**
     * Multiplies this instance of PosFraction by another and returns a new PosFraction
     * @param other - Other PosFraction to multiply by
     * @return
     */
    public PosFraction multiply(PosFraction other){
        throw new RuntimeException("Not implemented!");
    }
    /**
     * Returns a new PosFraction in lowest terms.
     * @param frac -The PosFraction to be simplified
     * @return
     */
    public PosFraction simplify(PosFraction frac){
        throw new RuntimeException("Not implemented!");
    }
    
    /**
     * Returns the least common denominator (LCD) of an ArrayList of PosFraction objects
     * @param fracList - ArrayList of fractions to find LCD of
     * @return
     */
    public int LCD(ArrayList<PosFraction> fracList){
        throw new RuntimeException("Not implemented!");
    }
}
