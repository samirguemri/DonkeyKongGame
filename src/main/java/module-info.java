module org.samir.projects.monkeykong {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.samir.projects.monkeykong to javafx.fxml;
    exports org.samir.projects.monkeykong;
}