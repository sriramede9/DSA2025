package org.sri.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextEditorHistoryTest {

    private TextEditorHistory history;

    @BeforeEach
    public void setUp() {
        history = new TextEditorHistory();
    }

    @Test
    public void testAddAction() {
        history.addAction("Type 'Hello'");
        history.addAction("Type 'World'");
        assertEquals(2, history.getHistorySize());
    }

    @Test
    public void testUndoAction() {
        history.addAction("Type 'Hello'");
        history.addAction("Type 'World'");
        String lastAction = history.undo();
        assertEquals("Type 'World'", lastAction);
        assertEquals(1, history.getHistorySize());
    }

    @Test
    public void testUndoEmptyHistory() {
        String lastAction = history.undo();
        assertNull(lastAction);
    }

    @Test
    public void testMaxHistoryLimit() {
        for (int i = 1; i <= 11; i++) {
            history.addAction("Action " + i);
        }
        assertEquals(10, history.getHistorySize());
        assertEquals("Action 2", history.undo());
    }
}
