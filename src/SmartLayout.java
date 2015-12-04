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
				new WHRange(WHRange.SINGLE, 100, 200, 100, 100));
		LayoutComponent compB = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 100, 200, 100, 100));
		LayoutComponent compC = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 100, 200, 100, 100));
		LayoutComponent compD = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 200, 400, 200, 400));
		LayoutComponent compE = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 300, 400, 100, 150));
		LayoutComponent compF = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 300, 400, 100, 150));
		LayoutComponent compG = new LayoutComponent(
				new WHRange(WHRange.SINGLE, 200, 300, 200, 300));

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

		Vector<WHRange> finalLayoutCases = contM.getRanges();
		contM.layout(0, 0, 800, 300, finalLayoutCases.get(0));

		System.out.println(compA);
		System.out.println(compB);
		System.out.println(compC);
		System.out.println(compD);
		System.out.println(compE);
		System.out.println(compF);
		System.out.println(compG);
	}


	public static void main(String args[])
	{
		SmartLayout app = new SmartLayout();
		app.run();
	}
}