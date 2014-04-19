import javax.swing.JFrame;

import org.junit.Test;


public class AddEditViewTest {

	@Test
	public void test() {
		GeoModel model = new GeoModel();
		Continent testCon = new Continent("ConName", "ConPop", "ConArea");
		model.addRegion(testCon);
		
		AddEditView hello = new AddEditView("Continent", "Edit", testCon, model);
        hello.nameJtf.setText(testCon.name);
        hello.areaJtf.setText(testCon.area);
        hello.popJtf.setText(testCon.pop);
		
        hello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true){}
	}

}
