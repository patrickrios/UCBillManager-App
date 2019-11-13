package view.monthpicker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import view.util.AnchorConstraints;

public class MonthPickerCalendar extends AnchorPane{
	private Label labeYear;
	private Button buttonNext;
	private Button buttonPrev;
	private GridPane gridMonths;
	private Button[] buttonsMonths = new Button[12];
	private MonthPicker picker;
	private Button currentSelected;
	
	public MonthPickerCalendar(MonthPicker picker) {
		this.picker = picker;
		styling();
		initiComponents();
		addComponents();
	}
	
	private void initiComponents() {
		initiYearLabel();
		initPrevButton();
		initNextButton();
		initGridButtons();
	}
	
	private void addComponents() {
		this.getChildren().add(this.labeYear);
		this.getChildren().add(this.buttonPrev);
		this.getChildren().add(this.buttonNext);
		this.getChildren().add(this.gridMonths);
	}
	
	private void nextYearAction() {
		this.buttonNext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				picker.monthRef.nextYear();
				labeYear.setText(picker.monthRef.year());
				
			}
		});
	}
	
	private void prevYearAction() {
		this.buttonPrev.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				picker.monthRef.previousYear();
				labeYear.setText(picker.monthRef.year());
				
			}
		});
	}
	
	private void createMonthButtons() {
		String[] names = {"jan","fev","mar","abr","mai","jun","jul","ago","set","out","nov","dez"};
		int monthIndex=0;
		int current = this.picker.monthRef.monthNumber();
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<4; j++) {
				Button newButton = new Button(names[monthIndex]);
				
				if(monthIndex+1==current) {
					this.currentSelected = newButton;
					newButton.getStyleClass().add("month-button-selected");
				}
				else {
					newButton.getStyleClass().add("month-button-unselected");
				}
				
				addSelectionMonthListener(newButton, monthIndex+1);
				this.gridMonths.add(newButton,j,i);
				this.buttonsMonths[monthIndex] = newButton;
				monthIndex++;
			}
		}
	}
	
	private void addSelectionMonthListener(Button b, int index) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				currentSelected.getStyleClass().clear();
				currentSelected.getStyleClass().addAll("button", "month-button-unselected");
				
				b.getStyleClass().clear();
				b.getStyleClass().addAll("button", "month-button-selected");
				
				setSelectedMonth(b);
				picker.monthRef.changeMonth(index);
				picker.updateHeaderValue();
				picker.displayCalendar();
			}
		});
	}
	
	private void setSelectedMonth(Button button) {
		this.currentSelected = button;
	}
	private void styling() {
		String style = getClass().getResource("pickerStyle.css").toExternalForm();
		this.getStylesheets().add(style);
		this.getStyleClass().add("month-calendar");
	}
	
	private void initiYearLabel() {
		this.labeYear = new Label(this.picker.monthRef.year());
		AnchorConstraints.addHorizon(this.labeYear,0,0,0);
		this.labeYear.getStyleClass().add("month-calendar-year-label");
		this.labeYear.setAlignment(Pos.CENTER);
	}
	
	private void initPrevButton() {
		this.buttonPrev = new Button();
		styleButtonControl(this.buttonPrev,"month-previous-year-min.png");
		prevYearAction();
		AnchorConstraints.addLeft(this.buttonPrev, 3,3);
	}
	
	private void initNextButton() {
		this.buttonNext = new Button();
		styleButtonControl(this.buttonNext,"month-next-year-min.png");
		nextYearAction();
		AnchorConstraints.addRight(this.buttonNext,3,3);
	}
	
	private void initGridButtons() {
		this.gridMonths = new GridPane();
		createMonthButtons();
		AnchorConstraints.add(this.gridMonths,35,2,2,2);
	}
	
	private void styleButtonControl(Button b, String path) {
		ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(path)));
		b.getStyleClass().add("month-calendar-year-control");
		b.setGraphic(icon);
	}
}
