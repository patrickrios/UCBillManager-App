package view.monthpicker;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class MonthPicker extends VBox{
	private MonthPickerControl header;
	private MonthPickerCalendar calendar;
	private MonthPickerCalendar clone;
	protected MonthRef monthRef;
	private boolean displayControl = false;
	
	public MonthPicker() {
		this.monthRef = new MonthRef();
		initiComponents();
		stylingConteiner();
		setHeaderAction();
	}
	
	public MonthPicker(MonthRef month) {
		this.monthRef = month;
		initiComponents();
		stylingConteiner();
		setHeaderAction();
	}
	
	protected void displayCalendar() {
		clone = this.calendar;
		this.displayControl = !this.displayControl;
		
		if(this.displayControl) {

			this.getChildren().add(clone);
			this.header.markAsSelected();
		}else {
			this.getChildren().remove(clone);
			this.header.markAsUnselected();
		}
			
	}
	
	private void initiComponents() {
		this.header = new MonthPickerControl(this.monthRef.monthAndYear());
		this.calendar = new MonthPickerCalendar(this);
		this.getChildren().add(header);
	}
	
	private void stylingConteiner() {
		this.setStyle("-fx-background-color:#FFF");
	}
	
	private void setHeaderAction() {
		this.header.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				displayCalendar();
			}
		});
	}
	
	protected void updateHeaderValue() {
		this.header.updateValue(this.monthRef.monthAndYear());
	}
	
	public MonthRef getValue() {
		return this.monthRef;
	}

}
