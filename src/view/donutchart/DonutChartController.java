package view.donutchart;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class DonutChartController {
    @FXML
    private PieChart pieChart;
    
    public void init(ObservableList<PieChart.Data> datas) {
    	this.pieChart.setData(datas);
    	initPieChart();
    }
    
    private void initPieChart() {
    	//this.pieChart.setPrefSize(416,248);
        this.pieChart.setLegendVisible(false);
    }

}