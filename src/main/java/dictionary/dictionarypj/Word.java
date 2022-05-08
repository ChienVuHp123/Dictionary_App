package dictionary.dictionarypj;

public class Word {
    private String word_target;
    private String word_explain;
    /**
     *
     */
    Word() {
    }

    Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    Word(String word_target) {
        this.word_target = word_target;
    }
    /**.
     *
     */
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    /**.
     *
     */
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    /**.
     *
     */
    public String getWord_explain() {
        return word_explain;
    }

    /**.
     *
     */
    public String getWord_target() {
        return word_target;
    }

    public boolean Check(Word word) {
        if (this.word_explain.equals(word.getWord_target())
                || this.word_target.equals(word.getWord_target())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean equal(Word word) {
        if (this.word_explain.equals(word.getWord_explain())
                && this.word_target.equals(word.getWord_target())) {
            return true;
        } else {
            return false;
        }
    }

    public void set(Word word) {
        this.word_target = word.getWord_target();
        this.word_explain = word.getWord_explain();
    }
}
