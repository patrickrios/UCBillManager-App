package view.donutchart;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class DonutChartController {
    @FXML
    private PieChart pieChart;
    
    public void init(ObservableList<PieChart.Data> datas) {
    	this.pieChart.setData(datas);
    	initPieChart(datas);
    }
    
    private void initPieChart(ObservableList<PieChart.Data> datas) {
    	applyCustomColorSequence(datas,"#FE8086","#66CC99");
        this.pieChart.setLegendVisible(false);
    }
    
    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
          data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
          i++;
        }
    }

}