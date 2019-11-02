package controller;

import model.util.HomeCard;
import model.util.RegisterInformations;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeCardController {
    @FXML
    private Label labelCardTitle;
    @FXML
    private Label labelNotPaidValue;
    @FXML
    private Label notPaidTotal;
    @FXML
    private Label labelNotPaidPercent;
    @FXML
    private Label labelPaidValue;
    @FXML
    private Label labelPaidTotal;
    @FXML
    private Label labelPaidPercent;
    @FXML
    private Label labelCardTotal;
    @FXML
    private Label labelCardValue;

	private RegisterInformations info;
	
	public void inti(int type){
		this.info = new RegisterInformations(type);
		initCardDatas();
		initiPaidDatas();
		initiNotPaidDatas();
	}
	
	private void initCardDatas()
	{
		HomeCard card = this.info.loadCardDatas();
		this.labelCardTitle.setText(card.getName());
		this.labelCardTotal.setText("Total: "+card.getSubtotal()+"  ("+card.getPercentual()+")");
		this.labelCardValue.setText(card.getValue());
		stylingTitleName(card.getName());
	}
	
	private void initiPaidDatas()
	{
		HomeCard paid = this.info.loadPaidDatas();
		this.labelPaidTotal.setText("("+paid.getSubtotal()+")");
		this.labelPaidValue.setText(paid.getValue());
		this.labelPaidPercent.setText(paid.getPercentual());
	}
	
	private void initiNotPaidDatas()
	{
		HomeCard not = this.info.loadNotPaidDatas();
		this.notPaidTotal.setText("("+not.getSubtotal()+")");
		this.labelNotPaidValue.setText(not.getValue());
		this.labelNotPaidPercent.setText(not.getPercentual());
	}
	
	private void stylingTitleName(String name)
	{
		if(name.equals("Receita"))
			this.labelCardTitle.getStyleClass().add("home-card-title-receita");
		else
			this.labelCardTitle.getStyleClass().add("home-card-title-despesa");
		
	}
}
