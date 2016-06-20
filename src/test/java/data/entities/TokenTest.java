package data.entities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import data.entities.Token;
import data.entities.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "u@gmail.com", "p");
        Token token = new Token(user);
        assertTrue(token.getValue().length() > 20);
    }
    
    @Test
    public void testTokenAssociation() {
        User association = new User("a", "s", "a@gmail.com", "000000000", "as", "address", "st", "t", "cp","p");
        Token token = new Token(association);
        assertTrue(token.getValue().length() > 20);
    }
    
}
