public class WordFrequencyInfo {
    private final String word;
    private final int count;

    public WordFrequencyInfo(String w, int i) {
        this.word = w;
        this.count = i;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }
}
