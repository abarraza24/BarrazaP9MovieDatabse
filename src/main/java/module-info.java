//ALexis Barraza abarraza24@cnm.edu
//Using DataBase in JAVA Intellij Idea
//BarrazaP9 Fav Movie DataBase.
module cnm.barrazap6.java1.barrazap9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires java.desktop;


    opens cnm.barrazap6.java1.barrazap9 to javafx.fxml;
    exports cnm.barrazap6.java1.barrazap9;
}