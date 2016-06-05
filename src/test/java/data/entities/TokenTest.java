package data.entities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import data.entities.Token;
import data.entities.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "u@gmail.com", "p", "p", "name", null);
        Token token = new Token(user);
        assertTrue(token.getValue().length() > 20);
    }
    
}
