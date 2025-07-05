module g3 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	opens application to javafx.graphics, javafx.fxml;
	
	requires ormlite.jdbc;
	requires java.sql;
	opens model.database;
}
