package view.util;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FullSizeOnStack {
	
	public static void extend(AnchorPane conteiner) {
		StackPane s = (StackPane)conteiner.getParent();
		double width = s.getWidth();
		double heignt = s.getHeight();
		
		conteiner.setPrefWidth(width);
		conteiner.setPrefHeight(heignt);
	}
	
	public static void extend(Pane conteiner) {
		StackPane s = (StackPane)conteiner.getParent();
		double width = s.getWidth();
		double heignt = s.getHeight();
		
		conteiner.setPrefWidth(width);
		conteiner.setPrefHeight(heignt);
	}
	
	public static void extendeWidth(AnchorPane conteiner) {
		StackPane s = (StackPane)conteiner.getParent();
		double width = s.getWidth();
		conteiner.setPrefWidth(width);
	}
}