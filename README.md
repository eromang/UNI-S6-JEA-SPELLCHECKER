# UNI-S6-JEA-SPELLCHECKER

UNI S6 JEA Exercise 2 Spell checker

## Project requirements 

Your task in this exercise is the development of a web application, where the user can enter a multi-line text in (British) English into a "textarea" for spell checking. The entered text is then checked for spelling errors, and found errors are shown to the user, possible together with the wrong text fragment or included as overlay in the text (try to find a nice and user-friendly output format). Usage of Ajax (for example for direct checking during the user input) is a plus.

The application should consist of one or two JSF pages (text input / output of spelling errors), corresponding named beans, and at least one EJB that implements the spell checking. Fortunately, you are not expected to write the spell checking from scratch, but you are allowed to use the existing library "LanguageTool". Find more information at dev.languagetool.org/java-api.

Upload the application with all sources and an appropriately defined Maven project definition (do not include external jar files in your archive - especially not the rather large language tool jar archive), but reference these external resources as dependency in your Maven pom.xml file), such that I can deploy your application directly with "mvn clean package wildfly:deploy" - use "Exercise2" as context path for your application (such that the access URL is given as "https://localhost:8080/Exercise2/").

## Project description

- context-root: /spellchecker
- groupId: lu.uni.jea.exercises
- artifactId: spellchecker
- name: spellchecker
- Depedencies:
    - org.languagetool
    - org.primefaces
- URL: http://0.0.0.0:8080/spellchecker/
