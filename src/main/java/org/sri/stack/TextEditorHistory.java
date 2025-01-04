package org.sri.stack;

import java.util.ArrayList;
import java.util.List;

public class TextEditorHistory {
    private List<String> actions;
    private static final int MAX_HISTORY = 10;

    public TextEditorHistory() {
        actions = new ArrayList<>();
    }

    public void addAction(String action) {
        if (actions.size() >= MAX_HISTORY) {
            actions.remove(0);  // Remove oldest action if we've reached capacity
        }
        actions.add(action);
    }

    public String undo() {
        if (!actions.isEmpty()) {
            return actions.remove(actions.size() - 1);
        }
        return null;  // No actions to undo
    }

    public int getHistorySize() {
        return actions.size();
    }
}
