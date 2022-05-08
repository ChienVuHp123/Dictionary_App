package dictionary.dictionarypj;
import java.util.Scanner;

public  class DictionaryCommandline extends DictionaryManagement {

    public DictionaryCommandline() {
    }

    /**.
     *
     */
    public static void showAllWord() {
        int  i = 1;
        System.out.println("English         |VietNamese");
        for (Word d : dictionary.wordList) {
            System.out.println(d.getWord_target() + "|" + d.getWord_explain());
        }
    }

    /**.
     * nhap : nhap tu command , show : hien thi , end : ket thuc
     */
    public static void dictionaryBasic() {
        String val = "read";
        Scanner input = new Scanner(System.in);
        while (val.compareTo("end") != 0) {
            val = input.nextLine();
            if (val.equals("nhap")) {
                insertFromCommandline();
            } else if (val.equals("show")) {
                showAllWord();
            }
        }
    }

    /**.
     * insert : nhap file , lookup goi ham lookup , show : hien , search goi ham searcher
     */
    public static void dictionaryAddvanced() {
        String read = "read";
        Scanner input = new Scanner(System.in);
        while (read.compareTo("end") != 0) {
            System.out.print("nhap tuy chon :");
            read = input.next();
            if (read.equals("insert")) {
                insertFromFile();
            } else if (read.equals("lookup")) {
                dictionaryLookup();
            } else if (read.equals("show")) {
                showAllWord();
            } else if (read.equals("search")) {
                dictionarySearcher();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        insertFromFile();
        delWord(new Word("asasd"));
        showAllWord();
        System.out.println(Translate.callUrlAndParseResult("en", "vi" , "hello"));
    }
}
