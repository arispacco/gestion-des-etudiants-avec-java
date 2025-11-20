package com.projetjava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testMain() {
        // This test will check if the main method runs without throwing any exceptions
        assertDoesNotThrow(() -> App.main(new String[] {}));
    }

    // Additional tests can be added here to test other functionalities of the application
}