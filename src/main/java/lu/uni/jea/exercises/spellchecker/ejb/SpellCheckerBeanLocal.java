package lu.uni.jea.exercises.spellchecker.ejb;

import java.util.List;

public interface SpellCheckerBeanLocal {
    public String spellCheck(String txt) throws Exception;
    public int getNbrSuggestedCorrection();
    public void setNbrSuggestedCorrection(int nbrSuggestedCorrection);
    public String getLastSuggestedReplacement(); // todo to delete
    public void setLastSuggestedReplacement(String lastSuggestedReplacement); // todo to delete
    public List<String> getSuggestions();
}
