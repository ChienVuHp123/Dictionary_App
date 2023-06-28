package dictionary.dictionarypj;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;

public class ControllerAdd implements Initializable{

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public Word w ;
    @FXML
    public TableView<Word> tbView;

    @FXML
    public TableColumn<Word, String> tcEng;

    @FXML
    public TableColumn<Word, String> tcVn;

    @FXML
    public TextField tfEng;

    @FXML
    public TextField tfVn;


    @FXML
    public Button btAdd = new Button();

    private ObservableList<Word> wordslist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcEng.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));  // Đặt data sourse cho 2 cột tcEng và TcVn
        tcVn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));

        wordslist = FXCollections.observableArrayList(); // mothed cho phép theo dõi những thay đổi
        for (Word word :DictionaryManagement.dictionary.wordList) {
            wordslist.add(word);
        }
        tbView.setItems(wordslist);
        tbView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Word>() {
            @Override
            public void changed(ObservableValue<? extends Word> observableValue, Word word, Word t1) {
                w = tbView.getSelectionModel().getSelectedItem();
            }
        });
    }

    /**
    Mehthod thêm từ mới khi có ButtonAdd
    */
    public void buttonAdd(ActionEvent actionEvent) {
        /*
        Nếu ở textfieldEng hoặc TfVn Null mà ấn Button thì sẽ hiện thông báo 
        */
        if (tfEng.getText().equals(null) || tfVn.getText().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("không có thay doi");
            alert.showAndWait();
        } else {
        tcEng.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        tcVn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        Word word = new Word(tfEng.getText(), tfVn.getText());
            /*
            Kiếm tra nếu đã có từ đó trong List thì ko thêm nữa
            */
        if (DictionaryManagement.check(word)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Đã trùng !");
            alert.showAndWait();
        } else {
            tbView.getItems().add(word);
            DictionaryManagement.addWord(word);
        }
        tbView.setItems(wordslist);
        }

    }
    public void buttonDel() {
        tcEng.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        tcVn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        System.out.println(w.getWord_target()+w.getWord_explain());
        DictionaryManagement.delWord(w);
        wordslist = FXCollections.observableArrayList();
        for (Word word :DictionaryManagement.dictionary.wordList) {
            wordslist.add(word);
        }
        tbView.setItems(wordslist);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("xóa thành công , hãy mở lại tùy chọn !");
        alert.showAndWait();
    }
    public void buttonFix() {
        tcEng.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        tcVn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        System.out.println(w.getWord_target()+w.getWord_explain());
        DictionaryManagement.fixWord(w, new Word(tfEng.getText(), tfVn.getText()));
        wordslist = FXCollections.observableArrayList();
        for (Word word :DictionaryManagement.dictionary.wordList) {
            wordslist.add(word);
        }
        tbView.setItems(wordslist);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("sửa thành công,hãy mở lại tùy chọn");
        alert.showAndWait();
    }
}

