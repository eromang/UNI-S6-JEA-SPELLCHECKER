package lu.uni.jea.exercises.spellchecker;

import lu.uni.jea.exercises.spellchecker.ejb.SpellCheckerBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 2 - Spell Checker
 *
 */

@Named("spellchecker")
@SessionScoped
public class Speller implements Serializable {

    private static final String CHECK = "spellcheck";
    private static final String BACK = "back"; // not used

    private static final Logger logger = Logger.getLogger("Speller");

    private String text;
    private String correctedText;
    private int nbrSuggestedCorrection;
    private String lastSuggestedRemplacement; // todo to delete
    private List<String> suggestions;

    @EJB
    private SpellCheckerBeanLocal sc;

    public Speller() {
        logger.info("start");
        text = "";
        correctedText = "";
    }

    public String getLastSuggestedReplacement() { // todo to delete
        lastSuggestedRemplacement = sc.getLastSuggestedReplacement();
        logger.info("Last suggested correction : " + lastSuggestedRemplacement);
        return lastSuggestedRemplacement;
    }

    public int getNbrSuggestedCorrection() {
        nbrSuggestedCorrection = sc.getNbrSuggestedCorrection();
        logger.info("Nbr of suggested correction : " + nbrSuggestedCorrection);
        return nbrSuggestedCorrection;
    }

    public String spellCheck() throws Exception {
        logger.info("Clicked on spell check button");
        correctedText = sc.spellCheck(text);
        return CHECK;
    }

    public String back() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        logger.info("Return back");
        return BACK;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectedText() {
        return correctedText;
    }

    public void setCorrectedText(String correctedText) {
        this.correctedText = correctedText;
    }

    public List<String> getSuggestions() {
        suggestions = sc.getSuggestions();
        return suggestions;
    }
}
