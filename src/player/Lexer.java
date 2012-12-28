package player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import player.Token;
import player.Token.Type;

/**
 * A lexer takes a string and splits it into tokens that are meaningful to a
 * parser.
 */
public class Lexer implements Iterable<Token> {

    private ArrayList<Token> tokens = new ArrayList<Token>();
    
    /**
     * Creates the lexer over the passed string.
     * @param string The string to tokenize.
     * @throws IllegalArgumentException on illegal input (including unmatched parentheses)
     */
    public Lexer(String string) throws IllegalArgumentException {
        //System.out.println(string);
        //From tokens, get patterns to match
        //Each pattern, seperated by |
        String pattern = "";
        for(Type t: Token.Type.values()){
            pattern += "|("+t.pattern+")";
        }
        pattern = pattern.substring(1);//remove the leading |
        
        
        //tokenize
        StringBuilder validateString = new StringBuilder(string); //for making sure everything was matched (input validation)

        Matcher tokenMatcher = Pattern.compile(pattern,Pattern.MULTILINE).matcher(string); //match all token types
        while (tokenMatcher.find()) {
          for(int i=tokenMatcher.start();i<tokenMatcher.end();i++)
              validateString.setCharAt(i, ' '); //turn the index positions corresponding to the match to a space in the validate string
          
          String tokenString = tokenMatcher.group(); 

          if (tokenString != "") //check for a blank token or a whitespace token
              this.tokens.add(new Token(tokenString)); //Add the new token, Token initializer will determine its type
        }
        if(!Pattern.compile("\\s*").matcher(validateString).matches())
            throw new IllegalArgumentException("Invalid input found!");
        
    }

    /**
     * Return an iterator instance over tokens
     * @return iterator over tokens
     */
    @Override
    public Iterator<Token> iterator() {
        return this.tokens.iterator();
    }
    
    /**
     * Viewer method to see the contents of the ArrayList tokens
     * @return copy of this instances tokens
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Token> getTokens(){
        return (ArrayList<Token>)this.tokens.clone();
    }
    
}