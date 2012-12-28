package player;

import static org.junit.Assert.*;

import org.junit.Test;

public class LexerTest {
    
    @Test
    public void LexerTest1() {
        //String test = "X: 2 \r T: Piece No. 2\r M: 4/4\r L: 1/4 \rQ: 200 \r K: Gm \n [^F/2e/2] [^F/2e/2] z/2 [^F/2e/2] z/2 [^F/2c/2] [^Fe] | [GBg] z G z | c3/2 G z E | E/2 A B _B/2 A | (3Geg a f/2 g/2 | z/2 e c/2 d/2 B3/4 z3/4 ||";
        String test = "^^C B [ABC] %Hi \r [GF_B] \r % own line comment \r";
        for(Token t:(new Lexer(test)).getTokens()){
            System.out.println(t.type+" : "+t.value);
        }
    }

}
