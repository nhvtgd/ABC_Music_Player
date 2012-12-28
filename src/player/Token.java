package player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A token is a lexical item that the parser uses.
 * 
 * Token Types:
 * C - For the name of the composer (matches C: to end of line)
 * K - The key (matches K: and a key defined by a letter and optional m)
 * L - Default length of note (matches L: num/num)
 * M - Meter (matches M: num/num)
 * Q - Tempo (matches Q: num)
 * T - Title (matches T: to end of line
 * X - Index number (matches X: num)
 * V - Voice (matches V: to end of line)
 * NOTE - Matches a note, including accidentals, octave marks, timing notation
 * REST - Matches a rest, including timing notation
 * REPEAT_NUM - matches [1, [2, etc. for a repeated section only a given time
 * REPEAT_START - matches |:, the start of a repeated section
 * REPEAT_END - matches :|, the end of a repeated section
 * MAJOR_SECTION - matches || or |], end of a major section
 * MEASURE - matches |, the end of a measure
 * START_TUPLET - matches (1, (2, or (3, the marks for a tuplet
 * START_CHORD - matches [, the start of a chord
 * END_CHORD - matches ], the end of a chord
 * COMMENT - % to end of line
 */
public class Token {
    String value;
    Type type;
    /**
     * All the types of tokens that can be made and the regex pattern that corresponds to them.
     */
    public static enum Type { 
        //Must go from most specific to least specific
        C ("^[C]:.*"),
        K ("^[K]:[ \\t]*[a-gA-G]m{0,1}[ \\t]*$"), //$ is EOL for MULTILINE flag
        L ("^[L]:[ \\t]*[0-9]+/[0-9]+[ \\t]*$"),
        M ("^[M]:[ \\t]*[0-9]+/[0-9]+[ \\t]*$"),
        Q ("^[Q]:[ \\t]*[0-9]+[ \\t]*$"),
        T ("^[T]:.*"),
        X ("^[X]:[ \\t]*[0-9]+[ \\t]*$"),
        V ("^[V]:.*"),
        NOTE ("(\\^{0,2}|_{0,2}|={0,1})[a-gA-G][,']*[0-9/]*"),
        REST ("z[0-9/]*"),
        REPEAT_NUM("\\[[0-9]+"),
        REPEAT_START("\\|:"),
        REPEAT_END(":\\|"),
        MAJOR_SECTION("(\\|\\|)|(\\|\\])"),
        MEASURE("\\|"),
        START_TUPLET("\\([2-4]"),
        START_CHORD ("\\["),
        END_CHORD("]"),
        COMMENT("%.*$");
        
        //Constructor
        public final String pattern; //public so Lexer can access to build overall pattern to match
        Type(String pattern){
            this.pattern = pattern;
        }
        
    }
    
    /**
     * Constructor method of a token
     * @param string - the value of the token
     * @throws IllegalArgumentException if a token is improperly formatted
     */
    public Token(String string) throws IllegalArgumentException{
        //Assign this.value to the string value of the token
        this.value = string;
        //check for token type and assign appropriately
        for(Type t: Type.values()){
            Matcher checkType = Pattern.compile(t.pattern).matcher(string); 
            if(checkType.matches()){
                this.type = t;
                break;
            }
        }
        if(this.type == null)
            throw new IllegalArgumentException("Invalid token found: '"+string+"'!");
          

    }
    
}