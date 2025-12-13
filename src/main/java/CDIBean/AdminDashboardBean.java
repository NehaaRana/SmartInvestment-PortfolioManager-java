/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Assets;
import client.AdminClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "adminDashboardBean")
//@RequestScoped
@ViewScoped
public class AdminDashboardBean implements Serializable  {

    /**
     * Creates a new instance of AdminDashboardBean
     */
    @Inject keepRecord keepRecord;
    AdminClient ac;

     private PieChartModel pieModel;
    private BarChartModel barModel;
    

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    @PostConstruct
    public void init() {
        ac = new AdminClient(keepRecord.getToken());
        createPieChart();
        createBarChart();

    }
    
     // ================= PIE CHART ====================
    // ---------- PIE CHART ----------
    public void createPieChart() {

        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();

        values.add(getTotalUsers());
        values.add(getTotalAssets());
        values.add(getTotalInvestmentAmount().doubleValue());
        values.add(getTotalPayouts().doubleValue());

        dataSet.setData(values);
        dataSet.setBackgroundColor(Arrays.asList(
                "#42A5F5", "#66BB6A", "#FFA726", "#FF6384"
        ));

        data.addChartDataSet(dataSet);
        data.setLabels(Arrays.asList("Users", "Assets", "Investment", "Payouts"));

        pieModel.setData(data);
    }

    
    
     // ================= BAR CHART ====================
     // ---------- BAR CHART ----------
    public void createBarChart() {

        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet set = new BarChartDataSet();
        set.setLabel("Overview");

        List<Object> values = new ArrayList<>();

        values.add(getTotalUsers());
        values.add(getTotalAssets());
        values.add(getTotalInvestmentAmount().doubleValue());
        values.add(getTotalPayouts().doubleValue());

        set.setData(values);
        set.setBackgroundColor(Arrays.asList("#42A5F5", "#66BB6A", "#FFA726", "#FF6384"));
        set.setBorderColor(Arrays.asList("#1E88E5", "#43A047", "#FB8C00", "#E53935"));
        set.setBorderWidth(1);

        data.addChartDataSet(set);
        data.setLabels(Arrays.asList("Users", "Assets", "Investment", "Payouts"));

        barModel.setData(data);
    }
   
    
    public Collection<Assets> getAllAssets() {
        return ac.getAllAssets();
    }

    
    public long getTotalUsers(){
        return ac.getTotalUsers(Long.class);
    }

    public long getTotalAssets(){
        return ac.getTotalAssets(Long.class);
    }

    public BigDecimal getTotalInvestmentAmount(){
        return ac.getTotalInvestedAmount(BigDecimal.class);
    }

    public BigDecimal getTotalPayouts(){
        return ac.getTotalPayouts(BigDecimal.class);
    }
    
}
