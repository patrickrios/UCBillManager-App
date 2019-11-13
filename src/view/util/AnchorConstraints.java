package view.util;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class AnchorConstraints {
	
	public static void add(Node n, double top, double right, double bottom, double left) {
		AnchorPane.setTopAnchor(n,top);
		AnchorPane.setRightAnchor(n,right);
		AnchorPane.setBottomAnchor(n,bottom);
		AnchorPane.setLeftAnchor(n,left);
	}
	
	public static void addLeft(Node n, double top, double left) {
		AnchorPane.setTopAnchor(n,top);
		AnchorPane.setLeftAnchor(n,left);
	}
	
	public static void addRight(Node n, double top, double right) {
		AnchorPane.setTopAnchor(n,top);
		AnchorPane.setRightAnchor(n,right);
	}
	
	public static void addHorizon(Node n, double left, double right, double top) {
		AnchorPane.setLeftAnchor(n,left);
		AnchorPane.setRightAnchor(n,right);
		AnchorPane.setTopAnchor(n,top);
	}

}
