import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WordFrequencyGameTest {

    private static String getInputStr(String input) {
        //Given
        return input;
    }

    private static String getExpectedResult(String expected) {
        return expected;
    }

    @Test
    void shouldProcessSingleWord() {
        validateInputWordsProcessToExpectedWord(getInputStr("the"), getExpectedResult("the 1"));
    }

    @Test
    void shouldProcessTwoWords() {
        validateInputWordsProcessToExpectedWord(getInputStr("the is"), getExpectedResult("the 1\nis 1"));
    }

    @Test
    void shouldProcessTwoWordsWithSpecialSpaces() {
        validateInputWordsProcessToExpectedWord(getInputStr("the      is"), getExpectedResult("the 1\nis 1"));
    }

    @Test
    void shouldProcessTwoWordsWithSpecialEnter() {
        validateInputWordsProcessToExpectedWord(getInputStr("the   \n   is"), getExpectedResult("the 1\nis 1"));
    }

    @Test
    void shouldProcessTwoSameWordsWithSorted() {
        validateInputWordsProcessToExpectedWord(getInputStr("the the is"), getExpectedResult("the 2\nis 1"));
    }

    @Test
    void shouldProcessSortedWithCountDescending() {
        validateInputWordsProcessToExpectedWord(getInputStr("the is is"), getExpectedResult("is 2\nthe 1"));
    }

    private void validateInputWordsProcessToExpectedWord(String inputStr, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        String result = game.getResult(inputStr);
        assertThat(result).isEqualTo(expectResult);
    }
}
