package org.sjd.gordon.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.Element;

public class ResultsPanel extends LayoutContainer {

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		ContentPanel panel = new ContentPanel();
		panel.add(new Html(html));
		add(panel);
	}
	
	public static String html2 = "<h1>RSS Reader</h1>";
	
	public static String html = 
		"<script type=\"text/javascript\" src=\"http://www.google.com/jsapi\"> " +
		"</script> " +
		"<script type=\"text/javascript\"> " +
		"google.load(\"visualization\", \"1\", { packages:[\"motionchart\"] }); " +
		"google.setOnLoadCallback(drawChart); " +
		"function drawChart() { " +
		"var data = new google.visualization.DataTable(); " +
		"var datajson = [ " +
		" [ " +
		" \"Apples\", " +
		"  2008, " +
		"\"West\", " +
		"    98, " +
		"    78, " +
		"    20, " +
		"\"2008-12-31\" " + 
		"], " +
		"[ " +
		" \"Apples\", " +
		"  2009, " +
		"\"West\", " +
		"   111, " +
		"    79, " +
		"    32, " +
		"\"2009-12-31\" " + 
		"], " +
		"[ " +
		" \"Apples\", " +
		"  2010, " +
		"\"West\", " +
		"    89, " +
		"    76, " +
		"    13, " +
		"\"2010-12-31\" " + 
		"], " +
		"[ " +
		" \"Oranges\", " +
		"  2008, " +
		"\"East\", " +
		"    96, " +
		"    81, " +
		"    15, " +
		"\"2008-12-31\" " + 
		"], " +
		"[ " +
		" \"Bananas\", " +
		"  2008, " +
		"\"East\", " +
		"    85, " +
		"    76, " +
		"     9, " +
		"\"2008-12-31\" " + 
		"], " +
		"[ " +
		" \"Oranges\", " +
		"  2009, " +
		"\"East\", " +
		"    93, " +
		"    80, " +
		"    13, " +
		"\"2009-12-31\" " + 
		"], " +
		"[ " +
		" \"Bananas\", " +
		"  2009, " +
		"\"East\", " +
		"    94, " +
		"    78, " +
		"    16, " +
		"\"2009-12-31\" " + 
		"], " +
		"[ " +
		" \"Oranges\", " +
		"  2010, " +
		"\"East\", " +
		"    98, " +
		"    91, " +
		"     7, " +
		"\"2010-12-31\" " + 
		"], " +
		"[ " +
		" \"Bananas\", " +
		"  2010, " +
		"\"East\", " +
		"    81, " +
		"    71, " +
		"    10, " +
		"\"2010-12-31\" " + 
		"]  " +
		"]; " +
		"data.addColumn('string','Fruit'); " +
		"data.addColumn('number','Year'); " +
		"data.addColumn('string','Location'); " +
		"data.addColumn('number','Sales'); " +
		"data.addColumn('number','Expenses'); " +
		"data.addColumn('number','Profit'); " +
		"data.addColumn('string','Date'); " +
		"data.addRows(datajson); " +
		"var chart = new google.visualization.MotionChart( " +
		"   document.getElementById('MotionChart_2011-01-10-21-41-52') " +
		"); " +
		"var options ={}; " +
		"ptions[\"width\"] =    600; " +
		"options[\"height\"] =    500; " +
		"chart.draw(data,options); " +
		"} " +
		"</script> " +
		"<div id=\"MotionChart_2011-01-10-21-41-52\" style=\"width: 600px; height: 500px;\"> " +
		"</div> ";	
	
	public static String html3 =
		"<div class=text style=\"padding:5px\">"
	      + "<h1>Heading1</h1>"
	      + "<i>Some text</i></br>"
	      + "Some more text</br>"
	      + "  <UL> <LI>item 1 <LI>item 2 </UL></br>"
	      + "<u>Final text</u></div>";
		
}
