package view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.util.AnchorConstraints;

public class MonthPickerControl extends AnchorPane {
	private Label monthValue;
	private ImageView calendarIcon;
	private ImageView chevronDownIcon;
	
	public MonthPickerControl(String value) {
		initiComponents(value);
		addComponents();
		stylingConteiner();
		markAsUnselected();
	}
	
	public void setMonthValue(String value) {
		this.monthValue.setText(value);
	}
	
	private void initiComponents(String value) {
		this.monthValue = new Label(value);
		this.monthValue.setAlignment(Pos.CENTER);
		
		this.calendarIcon = createImageIcon("calendarIcon.png");
		this.chevronDownIcon = createImageIcon("chevron-down-dark.png");
		
		AnchorConstraints.addLeft(this.calendarIcon,11,8);
		AnchorConstraints.addRight(this.chevronDownIcon,15,15);
		AnchorConstraints.add(this.monthValue,9,1,5,1);	
	}
	
	private void addComponents() {
		this.getChildren().add(this.monthValue);
		this.getChildren().add(this.calendarIcon);
		this.getChildren().add(this.chevronDownIcon);
	}
	
	private void stylingConteiner() {
		String style = getClass().getResource("pickerStyle.css").toExternalForm();
		this.getStylesheets().add(style);
	}
	
	private ImageView createImageIcon(String path) {
		return new ImageView(new Image(getClass().getResourceAsStream(path)));
	}
	
	public void markAsUnselected() {
		this.getStyleClass().clear();
		this.getStyleClass().add("anchor-pane");
		this.getStyleClass().add("month-control");
	}
	
	public void markAsSelected() {
		this.getStyleClass().clear();
		this.getStyleClass().add("anchor-pane");
		this.getStyleClass().add("month-control-selected");
	}

}
