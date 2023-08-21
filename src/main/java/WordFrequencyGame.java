import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(SPACE_DELIMITER);
        if (words.length == 1) {
            return inputStr + SPACE_CHAR + "1";
        }
        try {
            List<WordFrequencyInfo> wordFrequencyInfoList = getWorkFrequencyInfoList(words);
            return generatePrintLines(wordFrequencyInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordFrequencyInfo> getWorkFrequencyInfoList(String[] words) {
        Map<String, Long> wordFrequencyMap = Arrays.stream(words)
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingLong(WordFrequencyInfo::getWordCount).reversed())
                .collect(Collectors.toList());
    }

    private String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(word -> word.getWord() + SPACE_CHAR + word.getWordCount())
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }
}
