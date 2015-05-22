import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFrame;
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
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.TextAnchor;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;


public class fineDust extends JComponent{
	static JTextArea area;
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		BufferedReader inputStream = null;
		JButton sendBtn;
		Scanner keyboard = new Scanner(System.in);
		try{
			Class.forName ("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/termproject", "root", "gudwls12");
			Statement stmt = con.createStatement();
			inputStream = new BufferedReader (new FileReader("106_DT_106N_03_0200045_.csv"));
			
			
			String inputLine= null; 
			int i = 0, j,k=1,p=0,q=0,date=1;
			int count = 1, line_count=0,date_num = 0;
			//insert fine_dust_city table, fine_dust_date table, fine_dust_data.
			//date_num = count of date , count = city_num
			//read file and insert data into the database.
			/*
			while( (inputLine = inputStream.readLine() )!= null)
			{
				//insert into fine_dust_date table.
				if(i==0)
				{
					String[] list = inputLine.split(",");
					
					for(j=1; j<list.length;j++)
					{
						
						String[] temp = list[j].split("/");

						System.out.println(j+" "+temp[0]+" "+temp[1]);
						stmt.executeUpdate("insert into fine_dust_date values("+j+","+temp[0]+","+temp[1]+") ");
	
						//System.out.println(list[j]+ " ");
					}
					date_num = j;
					i++;
				}
				
				else
				{
					String[] list = inputLine.split(",");
					for(j=0; j<list.length;j++)
					{
						//insert into fine_dust_city table.
						if(j==0)
						{
							stmt.executeUpdate("insert into fine_dust_city values("+count+",'"+list[j]+"')");
							count++;
							break;
						}
					}
						//System.out.println(list[j]+ " ");
					//insert into fine_dust_date table.
					while(k < count)
					{
						for(j=0;j<list.length;j++)
						{
							if(j!=0)
							{
								if(list[j].equals(" ")||list[j].equals(""))
									stmt.executeUpdate("insert into fine_dust_data values("+j+","+k+","+0+")");
								else
									stmt.executeUpdate("insert into fine_dust_data values("+j+","+k+","+Integer.valueOf(list[j])+")");
							}
						}
						k++;
					}
					
				}
			
				
			}
			stmt.close();
			*/
		}/*
		
		catch(SQLException e) 
		{
	         e.printStackTrace();
	    }
		catch(ClassNotFoundException e) 
		{
	         e.printStackTrace();
	    } 
	    */
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 도시 데이터 평균 들고옴.
		ArrayList<DataModel> avgData = null;
		avgData = getDataFromDB.getData();//get data average from database.
		ArrayList<DataModel> maxData = null;
		maxData = getDataFromDB.getMAXData();//get max data from database.
		
		int check = 1;
		String cityName = null;
        System.out.println("Type city that you want to search :(Just enter for maximum,average dust for cities)");
        cityName = keyboard.nextLine();
       
        if(cityName.equalsIgnoreCase(""))
			check = 0;
        
         
        ArrayList<DataModel2> cityData = null;
        cityData = getDataFromDB.getCityData(cityName);//get city data from database.
        PolylineBarChart demo = new PolylineBarChart();
    	demo.setChartData( maxData, avgData ,cityData, check); //
        
		
		// 평균데이터 주입.
		  
    	
    	
	   	
	   	JFreeChart chart = demo.getChart();
        ChartFrame frame1=new ChartFrame("Bar Chart",chart);
          
        frame1.setSize(1000,600);  
        frame1.setVisible(true);
	}
	

	
}
