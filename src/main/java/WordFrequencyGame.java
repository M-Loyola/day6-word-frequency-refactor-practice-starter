import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        }
        try {
            List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfoList(inputStr);
            return generatePrintLines(wordFrequencyInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordFrequencyInfo> getWordFrequencyInfoList(String inputString) {
        String[] words = inputString.split(SPACE_DELIMITER);
        Map<String, List<WordFrequencyInfo>> wordFrequencyMap = new HashMap<>();

        for (String word : words) {
            wordFrequencyMap.computeIfAbsent(word, key -> new ArrayList<>())
                    .add(new WordFrequencyInfo(word, 1));
        }

        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequencyInfo(entry.getKey(), entry.getValue().size()))
                .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                .collect(Collectors.toList());
    }

    private String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(word -> word.getWord() + SPACE_CHAR + word.getWordCount())
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }
}
