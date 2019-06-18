module controle.financeiro.javafx
{
    requires javafx.controls;
    requires javafx.fxml;

    opens controller;
    opens main;
    opens view.util;
    opens  view.css;
    opens view.fonts;
    opens view.fxml;
    opens view.img;
}