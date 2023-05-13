/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Course;
import com.mycompany.services.ServiceCourse;
import java.util.ArrayList;

/**
 *
 * @author Zahra
 */
public class Back extends BaseForm {

   
    ArrayList<Course> mat;

    public float calcul_nbr_matiere(ArrayList<Course> r, String ch) {

        ArrayList<Course> mat = new ArrayList<Course>();
        mat = ServiceCourse.getInstance().getAllCourses();

        int f = 0;
        for (int i = 0; i < mat.size(); i++) {
            if (mat.get(i).getCategory().equals(ch)) {
                f++;
            }
        }
        return f;
    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(70);
        renderer.setLegendTextSize(70);
        renderer.setMargins(new int[]{12, 14, 11, 10, 19, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("Mathematics", this.calcul_nbr_matiere(mat, "Mathematics"));
        series.add("Development", this.calcul_nbr_matiere(mat, "Development"));
        series.add("Bussiness", this.calcul_nbr_matiere(mat, "Bussiness"));
        series.add("Marketing", this.calcul_nbr_matiere(mat, "Marketing"));

        return series;

    }

    public  Back(Resources res) {
  super(" Back office", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        new Label("Courses statistics");
        // Generate the values
        double[] values = new double[]{
            this.calcul_nbr_matiere(mat, "Esprit"),
            this.calcul_nbr_matiere(mat, "Telnet"),
            this.calcul_nbr_matiere(mat, "Axia"),
            this.calcul_nbr_matiere(mat, "Sopra")
        };

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.GRAY, ColorUtil.GREEN, ColorUtil.CYAN, ColorUtil.BLUE};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);

        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Calandrier_ex", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);
    tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        super.addSideMenuB(res);
        // Create a form and show it.
      add(c);
        /* f.getToolbar().addCommandToOverflowMenu("Retour", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
              Gestcalan f2 = new Gestcalan();
              f2.show();
            }
        });*/
       
   
    }

    
}
