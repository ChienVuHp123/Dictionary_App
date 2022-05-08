package dictionary.dictionarypj;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ControllerDictionary implements Initializable {

    public String voice;
    @FXML
    private TextField textFieldSearch;

    @FXML
    private TextArea textArea;

    @FXML
    private ListView<String> listView;

    @FXML
    private MenuItem AddDelItem;

    @FXML
    private MenuItem logItem;

    @FXML
    private Button api;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryManagement.insertFromFile();
        DictionaryManagement.sortList();
        textFieldSearch.setText("");
        ObservableList<String> wordsList = FXCollections.observableArrayList();
        for (Word word :DictionaryManagement.dictionary.wordList) {
                wordsList.add(word.getWord_target());
        }
        listView.setItems(wordsList);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String t1) {
                String currentWord = listView.getSelectionModel().getSelectedItem();
                textArea.setText(DictionaryManagement.Lookup(currentWord));
                voice = currentWord;
            }
        });

    }

    public void searchAPI() throws Exception {
        String w = textFieldSearch.getText();
        textArea.setText(Translate.callUrlAndParseResult("en", "vi", w));
    }

    public void Speechs() {

        TextSpeech.Speech(voice);

    }
    public void findWordSearch() {
        String findWord = textFieldSearch.getText();
        textArea.setText(DictionaryManagement.Lookup(findWord));

    }

    public void soundAPI() {
        Audio audio = new Audio();
        try {
            audio.play(audio.getAudio(voice, "en"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void suggestedWord() throws Exception{
        String findWord = textFieldSearch.getText();
        voice = findWord;
        ObservableList<String> wordsList = FXCollections.observableArrayList();
        for (Word word :DictionaryManagement.dictionary.wordList) {
            if (word.getWord_target().indexOf(findWord) == 0) {
                wordsList.add(word.getWord_target());
            }
        }
        listView.setItems(wordsList);

    }
    public void changeScene(ActionEvent event) {

        Stage stage = new Stage();
        try {
            Parent tree = FXMLLoader.load(this.getClass().getResource("add.fxml"));
            Scene scene = new Scene(tree);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
