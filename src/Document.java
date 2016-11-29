import java.util.ArrayList;
import java.util.List;


public class Document {
    private String id;
    private List<Word> wordList;

    public Document(String docId) {
        id = docId;
        wordList = new ArrayList<Word>();
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void addWord(Word tempWord) {
        wordList.add(tempWord);
        tempWord.addDocument(this);
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return id;
    }
}
