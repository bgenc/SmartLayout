import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SmartLayout
{
	public SmartLayout()
	{
	}

	public void run()
	{
		System.out.println("Starting test...");

        /*
        A diagram to show what this test is about:

        M
        +--------------------------------+--------------------------------+
        |                                |                                |
        Z                                X                                D
        +---------------------+          +----------+----------+
        |                     |          |          |          |
        Y                     G          A          B          C
        +----------+
        |          |
        E          F
         */

		LayoutComponent compA = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 100, 300, 100, 300));
		LayoutComponent compB = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 101, 300, 100, 300));
		LayoutComponent compC = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 102, 300, 100, 300));
		LayoutComponent compD = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 103, 300, 100, 300));
		LayoutComponent compE = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 104, 300, 100, 300));
		LayoutComponent compF = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 105, 300, 100, 300));
		LayoutComponent compG = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 106, 300, 100, 300));

		LayoutContainer contY = new LayoutContainer();
		contY.addComponent(compE);
		contY.addComponent(compF);

		LayoutContainer contZ = new LayoutContainer();
		contZ.addComponent(contY);
		contZ.addComponent(compG);

		LayoutContainer contX = new LayoutContainer();
		contX.addComponent(compA);
		contX.addComponent(compB);
		contX.addComponent(compC);

		LayoutContainer contM = new LayoutContainer();
		contM.addComponent(contZ);
		contM.addComponent(contX);
		contM.addComponent(compD);

		System.out.println(contM.getRanges());
	}


	public static void main(String args[])
	{
		SmartLayout app = new SmartLayout();
		app.run();
	}
}