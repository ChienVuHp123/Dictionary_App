package dictionary.dictionarypj;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryManagement  {

    public DictionaryManagement() {
    }

    public static Dictionary dictionary = new Dictionary();


    /**.
     *them Word tu commandLine
     */
    public static void insertFromCommandline() {
        Scanner input = new Scanner(System.in);
        String word_target = input.next();
        String word_explain = input.next();
        Word word = new Word(word_target, word_explain);
        dictionary.wordList.add(word);
    }

    /**.
     *Them Word tu file
     * voi duong vao la bien final FILE_INPUT
     */
    public static void insertFromFile() {
        Scanner input = null;
        try {
            input = new Scanner(new File(dictionary.FILE_INPUT));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String word_target;
        String word_explain;
        String word_readed;
        while (input.hasNext()) {
            word_readed = input.nextLine();
            word_target = word_readed.substring(0, word_readed.indexOf("|"));
            word_explain = word_readed.substring(word_readed.indexOf("|") + 1, word_readed.length());
            Word word = new Word(word_target, word_explain);
            dictionary.wordList.add(word);
        }
        System.out.println("----da nhap xong tu file :" + dictionary.FILE_INPUT + "---------");
    }

    /**.
     *VD: home -> nha
     */
    public static void dictionaryLookup() {
        Scanner input = new Scanner(System.in);
        boolean trap = false;
        System.out.print("Tu can tim :");
        String find_word = input.next();
        System.out.println(Lookup(find_word));
    }


    /**.
     *VD : tra -> transaction , transalate ,...
     */
    public static void dictionarySearcher() {
        Scanner input = new Scanner(System.in);
        boolean trap = false;
        System.out.print("Tu can tim :");
        String find_word = input.next();
        for (Word word :dictionary.wordList) {
            if (word.getWord_target().indexOf(find_word) == 0) {
                System.out.print(" - " + word.getWord_target());
                trap = true;
            }
        }

        if (trap == false) {
            System.out.print("Khong tim thay");
        }
        System.out.println();
    }

    public static String Search(String findWord) {
        for (Word word :dictionary.wordList) {
            if (word.getWord_target().indexOf(findWord) == 0) {
               return word.getWord_explain();
            }
        }
        return "not found";
    }


    public static String Lookup(String findWord) {
        int index = Collections.binarySearch(dictionary.wordList, new Word(findWord), new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
              return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
        if (index >=0 ) {
            return dictionary.wordList.get(index).getWord_explain();
        } else {
            return "không tồn tại" + findWord;
        }
        //return dictionary.wordList.get(index).getWord_explain();
    }

    public static void fixWord(Word word, Word fix) {
        try {
            int index = Collections.binarySearch(dictionary.wordList, word, new Comparator<Word>() {
                @Override
                public int compare(Word o1, Word o2) {
                    return o1.getWord_target().compareTo(o2.getWord_target());
                }
            });
            dictionary.wordList.get(index).set(fix);
            sortList();
            FileWriter fileWriter = new FileWriter(dictionary.FILE_INPUT);
            for (Word word1 :dictionary.wordList) {
                fileWriter.write(word1.getWord_target() + "|" + word1.getWord_explain() + "\n");
            }
            fileWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void delWord(Word word) {
        try {
            int index = Collections.binarySearch(dictionary.wordList, word, new Comparator<Word>() {
                @Override
                public int compare(Word o1, Word o2) {
                    return o1.getWord_target().compareTo(o2.getWord_target());
                }
            });
            int end = dictionary.wordList.size() - 1;
            dictionary.wordList.get(index).set(dictionary.wordList.get(end));
            dictionary.wordList.remove(dictionary.wordList.get(end));
            sortList();
            FileWriter fileWriter = new FileWriter(dictionary.FILE_INPUT);
            for (Word word1 :dictionary.wordList) {
                fileWriter.write(word1.getWord_target() + "|" + word1.getWord_explain() + "\n");
            }
            fileWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addWord(Word word) {
        try {
            dictionary.wordList.add(word);
            sortList();
            FileWriter fileWriter = new FileWriter(dictionary.FILE_INPUT);
            for (Word word1 :dictionary.wordList) {
                fileWriter.write(word1.getWord_target() + "|" + word1.getWord_explain() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Word> sortList() {
        Collections.sort(dictionary.wordList, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
        return dictionary.wordList;
    }

    public static void outFile() {
        try {
            FileWriter fileWriter = new FileWriter(dictionary.FILE_INPUT);
            for (Word word1 :dictionary.wordList) {
                fileWriter.write(word1.getWord_target() + "|" + word1.getWord_explain() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean check(Word find_word) {
        if (Lookup(find_word.getWord_target()).equals("không tồn tại" + find_word.getWord_target())) {
            return false;
        } else
        {
            return true;
        }
    }


}
