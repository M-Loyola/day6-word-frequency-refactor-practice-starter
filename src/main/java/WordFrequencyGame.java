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
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfoList(inputStr);

                return generatePrintLines(wordFrequencyInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordFrequencyInfo> getWordFrequencyInfoList(String inputString) {
        String[] words = inputString.split(SPACE_DELIMITER);
        List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfo(words);
        //get the map for the next step of sizing the same word
        Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);

        List<WordFrequencyInfo> frequencyInfosList = new ArrayList<>();
        for (Map.Entry<String, List<WordFrequencyInfo>> entry : wordFrequencyMap.entrySet()) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
            frequencyInfosList.add(wordFrequencyInfo);
        }
        wordFrequencyInfoList = frequencyInfosList;

        wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return wordFrequencyInfoList;
    }

    private static List<WordFrequencyInfo> getWordFrequencyInfo(String[] words) {
        List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
        for (String word : words) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(word, 1);
            wordFrequencyInfoList.add(wordFrequencyInfo);
        }
        return wordFrequencyInfoList;
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(word -> word.getWord() + SPACE_CHAR + word.getWordCount())
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequencyInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getWord(), arr);
            } else {
                map.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        }
        return map;
    }
}
