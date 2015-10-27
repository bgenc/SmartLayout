import java.util.Vector;

/**
 * Created by bgenc on 10/19/15.
 */
public class LayoutComponent implements Layoutable
{
	private int aX, aY, aWidth, aHeight;
	private WHRange whRange;


	public LayoutComponent(WHRange whRange)
	{
		this.aX = 0;
		this.aY = 0;
		this.whRange = whRange;
		this.aWidth = whRange.getMinWidth();
		this.aHeight = whRange.getMinHeight();
	}

	public void setX(int val)
	{
		this.aX = val;
	}

	public void setY(int val)
	{
		this.aY = val;
	}

	public void setWidth(int val)
	{
		this.aWidth = val;
	}

	public void setHeight(int val)
	{
		this.aHeight = val;
	}

	public void setPosition(int xVal, int yVal)
	{
		this.setX(xVal);
		this.setY(yVal);
	}

	public void setSize(int width, int height)
	{
		if (width > this.whRange.getMaxWidth() ||
				  width < this.whRange.getMinWidth() ||
				  height > this.whRange.getMaxHeight() ||
				  height < this.whRange.getMinHeight())
		{
			System.err.println("invalid setSize: (" + width + "," + height + ") on " + this.whRange);
		}
		this.setWidth(width);
		this.setHeight(height);
	}

	public String toString()
	{
		return "[(" + this.aX + "," + this.aY + ")." + "(" + this.aWidth + "," + this.aHeight + ") < " + this.whRange + "]";
	}

	@Override
	public int getAssignedWidth()
	{
		return this.aWidth;
	}

	@Override
	public int getAssignedHeight()
	{
		return this.aHeight;
	}

	public Vector<WHRange> getRanges()
	{
		Vector<WHRange> vec = new Vector<WHRange>();
		vec.add(this.whRange);
		return vec;
	}
}
