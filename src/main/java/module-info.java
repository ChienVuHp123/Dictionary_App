module dictionary.dictionarypj {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jsapi;
    requires jlayer;
    requires java.json;
    requires java.sql;
    opens dictionary.dictionarypj to javafx.fxml;
    exports dictionary.dictionarypj;
}