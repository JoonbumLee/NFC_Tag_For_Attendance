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
    	  	
    	
        // 렌더링 생성 및 세팅
        // 렌더링 생성
        final BarRenderer renderer = new BarRenderer();
     
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        
        // 공통 옵션 정의
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER 
            );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
                     );
        Font f = new Font("Gulim", Font.PLAIN, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
        
        // 렌더링 세팅
        // 그래프 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f);

        renderer.setSeriesPaint(0, new Color(0,162,255));
        
        // 그래프 3        
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
        
        // plot 생성
        final CategoryPlot plot = new CategoryPlot();
        
        // plot 에 데이터 적재
        
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);
        
        
        plot.setDataset(2, dataset2);
        plot.setRenderer(2, renderer2);
 
        // plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL);             // 그래프 표시 방향
        plot.setRangeGridlinesVisible(true);                       // X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true);                      // Y축 가이드 라인 표시여부
 
        // 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        // X축 세팅
        plot.setDomainAxis(new CategoryAxis("City_names or Date"));              // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont( new Font("Gulim", Font.BOLD, 9) ); // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);       // 카테고리 라벨 위치 조정
 
        // Y축 세팅
        plot.setRangeAxis(new NumberAxis());                 // Y축 종류 설정
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y축 눈금라벨 폰트 조정
        
        // 세팅된 plot을 바탕으로 chart 생성
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
