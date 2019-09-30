package view.util;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class ExtendedAnchorConst {

	public static void setConstrants(Node n)
	{
		AnchorPane.setBottomAnchor(n, 0.0);
		AnchorPane.setLeftAnchor(n, 0.0);
		AnchorPane.setRightAnchor(n, 0.0);
		AnchorPane.setTopAnchor(n, 0.0);
	}
	

}
