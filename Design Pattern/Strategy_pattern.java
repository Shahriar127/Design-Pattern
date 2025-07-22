// Strategy Interface
interface SpellCheckerStrategy {
    void check(String text);
}

// Concrete Strategy 1
class EnglishSpellChecker implements SpellCheckerStrategy {
    public void check(String text) {
        System.out.println("Checking spelling in English: " + text);
    }
}

// Concrete Strategy 2
class SpanishSpellChecker implements SpellCheckerStrategy {
    public void check(String text) {
        System.out.println("Checking spelling in Spanish: " + text);
    }
}

// Context
class TextEditor {
    private SpellCheckerStrategy spellChecker;

    public TextEditor(SpellCheckerStrategy spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void setSpellChecker(SpellCheckerStrategy spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void checkText(String text) {
        spellChecker.check(text);
    }
}

// Usage
public class Strategy_pattern {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(new EnglishSpellChecker());
        editor.checkText("Hello world!");

        editor.setSpellChecker(new SpanishSpellChecker());
        editor.checkText("Hola mundo!");
    }
}
