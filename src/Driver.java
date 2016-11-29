import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.TreeSet;

public class Driver {

    private static List<Word> wordList;
    private static List<Document> docList;

    private static void readWords(String sentFile) {
        wordList = new ArrayList<Word>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(sentFile));
            String nextWord = br.readLine();
            while (nextWord != null) {
                wordList.add(new Word(nextWord));
                nextWord = br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error reading words.txt!");
        }
    }

    private static void readDocs(String sentFile) {
        docList = new ArrayList<Document>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(sentFile));
            String nextLine = br.readLine();
            while (nextLine != null) {
                String[] docIdAndWords = nextLine.split(" ");
                Document tempDoc = new Document(docIdAndWords[0]);
                //Adds all word indexes to the new Document.
                for (int i=1; i < docIdAndWords.length; i++) {
                    tempDoc.addWord(wordList.get(Integer.parseInt(docIdAndWords[i]) - 1));
                }
                docList.add(tempDoc);
                nextLine = br.readLine();
            }
        } catch (IOException e) {
            System.out.print("Error reading documents.txt!");
        }
    }

    public static void main(String[] args) {

        //Need to take string inputs to sent to read methods.
        //....
        //....

        //Runs methods to turn sent files into Object Lists.
        readWords("words.txt");
        readDocs("documents.txt");

        //Prints current full word list.
        System.out.println(wordList.toString() + "\n");
        System.out.println(docList.toString() + "\n");

        //Prints each document, followed by it's word list.
        for (int i=0; i<docList.size(); i++) {
            Document tempDoc = docList.get(i);
            System.out.println("Document: " + tempDoc.getId());
            if (tempDoc.getWordList().size() == 0) {
                System.out.println("- No words assigned.");
            } else {
                System.out.println("Wordlist:");
                for (int j = 0; j < tempDoc.getWordList().size(); j++) {
                    System.out.println(tempDoc.getWordList().get(j).toString());
                }
            }
            System.out.println();
        }

        //Prints each word, followed by it's doc list.
        for (int i=0; i<wordList.size(); i++) {
            System.out.println("Word: " + wordList.get(i).getWord());
            List<Document> tempDocs = wordList.get(i).getDocList();
            if (tempDocs.size() == 0) {
                System.out.println("- No documents assigned.");
            } else {
                System.out.println("Doclist:");
                for (int j = 0; j < tempDocs.size(); j++) {
                    System.out.println(tempDocs.get(j).getId());
                }
            }
            System.out.println();
        }


        //Adds all words to the tree that are assigned to documents.
        TreeSet<Word> myTreeSet = new TreeSet<Word>();
        for (int i=0; i<wordList.size(); i++) {
            if(wordList.get(i).getDocList().size() > 0) {
                myTreeSet.add(wordList.get(i));
            }
        }

        System.out.println(myTreeSet.toString());


        //create binary search tree for words that have documents referencing them.
        //not all words will be in the binary search tree.

    }
}
