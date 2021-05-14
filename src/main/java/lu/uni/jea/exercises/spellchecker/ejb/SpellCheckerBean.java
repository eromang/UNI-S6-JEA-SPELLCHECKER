package lu.uni.jea.exercises.spellchecker.ejb;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.languagetool.AnalyzedSentence;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

@Stateless
public class SpellCheckerBean implements SpellCheckerBeanLocal {

    private static final Logger logger = Logger.getLogger("SpellCheckerBean");

    static BritishEnglish lang = null;
    private JLanguageTool langTool = null;
    private StringBuilder sb;
    private String matchingWord;
    private String lastSuggestedReplacement; // todo to delete
    private char[] buffer;
    private int idx = 0;
    private int nbrSuggestedCorrection;
    private List<String> suggestions;

    @Override
    public String spellCheck(String inputString) throws IOException {

        logger.info("Start to check with JLanguageTool");

        // Init
        int stringIndex = 0;
        int nbrSuggestedCorrection = 0;
        setNbrSuggestedCorrection(nbrSuggestedCorrection);
        setLastSuggestedReplacement("None"); // todo to delete

        // Initiate JLanguageTool
        lang = new BritishEnglish();
        langTool = new JLanguageTool(lang);

        // Init list of suggestions
        suggestions = new ArrayList<>();

        // Initiate a string builder to reconstruct the submitted phrase
        StringBuilder sb = new StringBuilder();

        // Convert the string to an array of char
        buffer = inputString.toCharArray();

        List<RuleMatch> matches = langTool.check(inputString);

        for (RuleMatch match : matches) {

            // Get the position of the suggested correction(s)
            int matchFrom = match.getFromPos();
            int matchTo = match.getToPos();

            logger.info("matchFrom = " + matchFrom + " stringIndex = " + stringIndex);

            sb.append(buffer, stringIndex, (matchFrom - stringIndex));

            logger.info("Potential error at characters " +
                            matchFrom + "-" + matchTo + ": " + match.getMessage());

            logger.info("Suggested correction(s): " + match.getSuggestedReplacements());

            // No suggested replacements
            if(match.getSuggestedReplacements().isEmpty()) {
                logger.info("No suggested replacements");
                matchingWord = inputString.substring(matchFrom, matchTo);

            } else {
                // Suggested remplacements exists

                nbrSuggestedCorrection++;
                setNbrSuggestedCorrection(nbrSuggestedCorrection);

                logger.info("Nbr of suggested correction is : " + nbrSuggestedCorrection);
                logger.info("getSuggestedReplacements is not empty");

                // Debug
                AnalyzedSentence originalWord;
                originalWord = match.getSentence();
                logger.info("Original word is : " + originalWord);

                matchingWord = inputString.substring(matchFrom, matchTo);

                String errorMessage = String.format("Got these suggestions: %s, for %s ",
                        match.getSuggestedReplacements(), matchingWord);
                logger.info(errorMessage);
                suggestions.add(errorMessage);

                // Get the first suggestion
                matchingWord = match.getSuggestedReplacements().get(0);
                setLastSuggestedReplacement(matchingWord); // todo to delete

            }

            sb.append(matchingWord.toCharArray(), 0, (matchingWord.length()));
            stringIndex = matchTo;
        }

        sb.append(buffer, stringIndex, (buffer.length - stringIndex));
        return sb.toString();
    }

    @Override
    public int getNbrSuggestedCorrection() {
        return nbrSuggestedCorrection;
    }

    @Override
    public void setNbrSuggestedCorrection(int nbrSuggestedCorrection) {
        this.nbrSuggestedCorrection = nbrSuggestedCorrection;
    }

    @Override // todo to delete
    public String getLastSuggestedReplacement() {
        return lastSuggestedReplacement;
    }

    @Override // todo to delete
    public void setLastSuggestedReplacement(String lastSuggestedReplacement) {
        this.lastSuggestedReplacement = lastSuggestedReplacement;
    }

    @Override
    public List<String> getSuggestions() {
        return suggestions;
    }


}
