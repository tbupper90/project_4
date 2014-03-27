import java.io.IOException;

import org.junit.Test;


public class ShowGraphicTest {

    @Test
    public void testMakeBarGraph() {
        ShowGraphic.makeBarGraph(new String[]{"Name1", "Name2", "Name3"},
        		new long[]{999999999, 555555555, 111111111}, "Bar Graph Title");
    }

    @Test
    public void testMakeSegmentGraph() {
        ShowGraphic.makeSegmentGraph(new String[]{"Name1", "Name2", "Name3"},
        		new long[]{999999999, 555555555, 111111111}, "Segment Graph Title");
    }

    @Test
    public void testMakeWorldMap() throws IOException {
    	ShowGraphic.makeWorldMap(new String[]{"Name1", "Name2", "Name3"},
    			new String[][]{{"E100.0", "S50.0"}, {"W100.0", "S50.0"},
    			{"W100.0", "N50.0"}}, "World Map Title");
    }

}
