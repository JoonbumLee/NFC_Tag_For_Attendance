import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;


public class PolylineBarChart extends JComponent {
	
   	
	DefaultCategoryDataset dataset1 = new DefaultCategoryDataset(); // Bar
	DefaultCategoryDataset dataset2 = new DefaultCategoryDataset(); // line
	
    public JFreeChart getChart() {
    	  	
    	
        // ������ ���� �� ����
        // ������ ����
        final BarRenderer renderer = new BarRenderer();
     
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        
        // ���� �ɼ� ����
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER 
            );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
                     );
        Font f = new Font("Gulim", Font.PLAIN, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
        
        // ������ ����
        // �׷��� 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f);

        renderer.setSeriesPaint(0, new Color(0,162,255));
        
        // �׷��� 3        
        renderer2.setBaseItemLabelGenerator(generator);
        renderer2.setBaseItemLabelsVisible(true);
        renderer2.setBaseShapesVisible(true);
        renderer2.setDrawOutlines(true);
        renderer2.setUseFillPaint(true);
        renderer2.setBaseFillPaint(Color.WHITE); 
        renderer2.setBaseItemLabelFont(f);
        renderer2.setBasePositiveItemLabelPosition(p_below);
        renderer2.setSeriesPaint(0,new Color(219,121,22));
        renderer2.setSeriesStroke(0,new BasicStroke(2.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,3.0f));
        
        // plot ����
        final CategoryPlot plot = new CategoryPlot();
        
        // plot �� ������ ����
        
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);
        
        
        plot.setDataset(2, dataset2);
        plot.setRenderer(2, renderer2);
 
        // plot �⺻ ����
        plot.setOrientation(PlotOrientation.VERTICAL);             // �׷��� ǥ�� ����
        plot.setRangeGridlinesVisible(true);                       // X�� ���̵� ���� ǥ�ÿ���
        plot.setDomainGridlinesVisible(true);                      // Y�� ���̵� ���� ǥ�ÿ���
 
        // ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        // X�� ����
        plot.setDomainAxis(new CategoryAxis("City_names or Date"));              // X�� ���� ����
        plot.getDomainAxis().setTickLabelFont( new Font("Gulim", Font.BOLD, 9) ); // X�� ���ݶ� ��Ʈ ����
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);       // ī�װ� �� ��ġ ����
 
        // Y�� ����
        plot.setRangeAxis(new NumberAxis());                 // Y�� ���� ����
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y�� ���ݶ� ��Ʈ ����
        
        // ���õ� plot�� �������� chart ����
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("Quantity of Fine_Dust"); 
        chart.setAntiAlias(true);
        

        return chart;
    }
    
    public void setChartData( ArrayList<DataModel> dataList, ArrayList<DataModel> dataList2, 
    		ArrayList<DataModel2> dataList3,int check) {
    		if(check == 0)//if check =0 , show average, max chart(bar, line).
    		{
    			for(int i =0; i < dataList.size(); i++) {
    				DataModel d = dataList.get(i);
    				dataset1.addValue( d.data, "Maximum fine_dust of Cities", d.city);
    			}
    		
    			for(int i= 0; i < dataList2.size(); i++) {
    				DataModel d2 = dataList2.get(i);
    				dataset2.addValue( d2.data, "Average fine_dust of Cities", d2.city);
    			}
    		}
    		else if(check ==1)//if check =1 , show city data for 4 years.(bar)
    		{
    			for(int i=0; i < dataList3.size(); i++) {
    				DataModel2 d3 = dataList3.get(i);
    				dataset1.addValue( d3.data, "Fine_Dust of "+d3.city_name, d3.year +"/"+ d3.month);
    			}
    		}
    }
 
}
