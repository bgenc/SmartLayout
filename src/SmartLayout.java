import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class SmartLayout extends JFrame implements ComponentListener
{
	Vector<LayoutComponent> components;
	Layoutable root;
	Canvas canv;
	JPanel p;
	BufferedImage buffer;
	Graphics bufferGraphics;
	Vector<WHRange> finalLayoutCases;

	public SmartLayout()
	{
		super();
		this.components = new Vector<LayoutComponent>();
		this.root = null;
		this.finalLayoutCases = null;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponentListener(this);

		canv = new Canvas();
		canv.setSize(100, 100);
		p = new JPanel(new FlowLayout());
		p.add(canv);

		buffer = new BufferedImage(canv.getWidth(), canv.getHeight(), BufferedImage.TYPE_INT_RGB);
		bufferGraphics = buffer.createGraphics();

		this.setContentPane(p);
		this.setVisible(true);
	}

	/**
	 * Creates a new component under this layout.
	 * @param label The label of the new component.
	 * @param range The width and height range of the new component.
	 * @return The component to be created.
	 */
	public LayoutComponent createComponent(String label, WHRange range)
	{
		LayoutComponent c = new LayoutComponent(label, range);
		this.components.add(c);
		return c;
	}

	/**
	 * Creates a demo layout model and runs the layout algorithm on the model.
	 */
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

		LayoutComponent compA = createComponent("A",
		                                        new WHRange(WHRange.SINGLE, 100, 200, 100, 100));
		LayoutComponent compB = createComponent("B",
				new WHRange(WHRange.SINGLE, 100, 200, 100, 100));
		LayoutComponent compC = createComponent("C",
				new WHRange(WHRange.SINGLE, 100, 200, 100, 100));
		LayoutComponent compD = createComponent("D",
				new WHRange(WHRange.SINGLE, 200, 400, 200, 400));
		LayoutComponent compE = createComponent("E",
				new WHRange(WHRange.SINGLE, 300, 400, 100, 150));
		LayoutComponent compF = createComponent("F",
				new WHRange(WHRange.SINGLE, 300, 400, 100, 150));
		LayoutComponent compG = createComponent("G",
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

		this.root = contM;

		finalLayoutCases = this.root.getRanges();
		System.out.println(finalLayoutCases);
		this.root.layout(0, 0, 800, 300, finalLayoutCases.get(0));
	}

	/**
	 * Draw the layout on the screen.
	 */
	public void drawLayout()
	{
		if (this.root == null)
			return;

		for (LayoutComponent c : this.components)
		{
			int x = c.getAssignedX();
			int y = c.getAssignedY();
			int w = c.getAssignedWidth();
			int h = c.getAssignedHeight();
			int red = 100 + (int)(Math.random() * 100);
			int gre = 100 + (int)(Math.random() * 100);
			int blu = 100 + (int)(Math.random() * 100);
//			System.out.println(c + " " + red + " " + gre + " " + blu);

			bufferGraphics.setColor(new Color(red, gre, blu));
			bufferGraphics.fillRect(x, y, w, h);

			bufferGraphics.setColor(Color.black);
			bufferGraphics.drawString(c.getLabel(), x + w / 2, y + h / 2);
			bufferGraphics.drawString( x + " , " + y, x + 5, y + 15);
			bufferGraphics.drawString("" + w, x + w / 2, y + h - 10);
			bufferGraphics.drawString("" + h, x + w - 30, y + h / 2);
		}
		this.repaint();
	}

	@Override
	public void paint(Graphics g)
	{
		canv.getGraphics().drawImage(buffer, 0, 0, null);
	}

	public static void main(String args[])
	{
		SmartLayout app = new SmartLayout();
		app.run();
		app.setSize(app.root.getAssignedWidth() + 50, app.root.getAssignedHeight() + 50);
	}

	@Override
	/**
	 *
	 */
	public void componentResized(ComponentEvent componentEvent)
	{
		if (root == null)
		{
			return;
		}
//		this.root.layout(0, 0, this.getWidth() - 50, this.getHeight() - 50, finalLayoutCases
//				.get(0));
		canv.setSize(new Dimension(this.root.getAssignedWidth(), this.root.getAssignedHeight()));
		buffer = new BufferedImage(canv.getWidth(), canv.getHeight(), BufferedImage.TYPE_INT_RGB);
		bufferGraphics = buffer.createGraphics();
		drawLayout();

	}

	@Override
	public void componentMoved(ComponentEvent componentEvent)
	{

	}

	@Override
	public void componentShown(ComponentEvent componentEvent)
	{
	}

	@Override
	public void componentHidden(ComponentEvent componentEvent)
	{

	}
}