package lu.uni.jea.exercises.spellchecker.ejb;

public interface SpellCheckerBeanLocal {
    public String spellCheck(String txt) throws Exception;
    public int getNbrSuggestedCorrection();
    public void setNbrSuggestedCorrection(int nbrSuggestedCorrection);
    public String getLastSuggestedReplacement();
    public void setLastSuggestedReplacement(String lastSuggestedReplacement);
}
