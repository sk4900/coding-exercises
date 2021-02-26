import java.io.*;
import java.util.*;

class Word {
    String word;
    Word   parent;

    public Word(String word) {
        this.word = word;
    }

}

public class hw1 {

    private void printLadder(Word wordLadder) {
        if(wordLadder == null) {
            System.out.println();
            return;
        }
        printLadder(wordLadder.parent);
        System.out.print(wordLadder.word +"\n");

    }

    private Word findLadder(String source, String dest, List <String> dictionary) {
        if (null == source || null == dest || source.trim().length() == 0 || dest.trim().length() == 0 || null == dictionary || dictionary.size() == 0) {
            return null;
        }
        if (source.length() != dest.length()) {
            return null;
        }

        Queue < Word > queue = new LinkedList <>();
        Word start = new Word(source);
        queue.add(start);
        while (!queue.isEmpty()) {
            Word current = queue.poll();

            char[] sChars = current.word.toCharArray();
            for (int j = 0; j < sChars.length; j++){
                char original=sChars[j];
                for (char c='a'; c <= 'z'; c++){
                    if (c==sChars[j]){
                        continue;
                    }
                    sChars[j]=c;
                    String tempStr=String.copyValueOf(sChars);
                    Word newWord = new Word(tempStr);
                    newWord.parent = current;
                    if (tempStr.equals(dest)){
                        return newWord;
                    }

                    if (dictionary.contains(tempStr)){
                        queue.add(newWord);
                        dictionary.remove(tempStr);
                    }
                }
                sChars[j]=original;
            }
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        hw1 ladder = new hw1();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter dictionary file name, start word, and end word");

        String input = scanner.nextLine();  // Read user input
        String[] parsed = input.split(" ");
        String dict = parsed[0];
        String start = parsed[1];
        String end = parsed[2];
        File file = new File(dict);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> wordlist = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            wordlist.add(line);
            line = reader.readLine();
        }
        reader.close();
        Word wordladder = ladder.findLadder(start, end, wordlist);
        ladder.printLadder(wordladder);
    }
}
