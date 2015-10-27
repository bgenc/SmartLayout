import java.util.Vector;

/**
 * Created by bgenc on 10/19/15.
 */
public class WHRange
{
	private int minW, maxW, minH, maxH;
	private int orientationStrategy;
	private Vector<WHRange> subRanges;
	public static final int SINGLE = 0;
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final int OTHER = 3;

	public WHRange(int orientationStrategy, int minWidth, int maxWidth, int minHeight, int maxHeight)
	{
		this.minW = minWidth;
		this.maxW = maxWidth;
		this.minH = minHeight;
		this.maxH = maxHeight;
		this.subRanges = new Vector<WHRange>();
		this.orientationStrategy = orientationStrategy;
	}

	public WHRange(WHRange whr)
	{
		this.minW = whr.getMinWidth();
		this.maxW = whr.getMaxWidth();
		this.minH = whr.getMinHeight();
		this.maxH = whr.getMaxHeight();
		this.subRanges = new Vector<WHRange>();
		this.orientationStrategy = whr.getOrientationStrategy();
	}

	public int getMinWidth()
	{
		return this.minW;
	}

	public int getMinHeight()
	{
		return this.minH;
	}

	public int getMaxWidth()
	{
		return this.maxW;
	}

	public int getMaxHeight()
	{
		return this.maxH;
	}

	public int getOrientationStrategy()
	{
		return this.orientationStrategy;
	}

	public void setOrientationStrategy(int os)
	{
		this.orientationStrategy = os;
	}

	public void addSubRange(WHRange whr)
	{
		this.subRanges.add(whr);
	}

	public void addSubRanges(Vector<WHRange> subRangeVector)
	{
		this.subRanges.addAll(subRangeVector);
	}

	public Vector<WHRange> getSubRanges()
	{
		return this.subRanges;
	}

	public String getOrientationStrategyString()
	{
		if (this.getOrientationStrategy() == SINGLE)
			return "S";
		else if (this.getOrientationStrategy() == HORIZONTAL)
			return "H";
		else if (this.getOrientationStrategy() == VERTICAL)
			return "V";
		else if (this.getOrientationStrategy() == OTHER)
			return "O";
		else
			return "!!!";
	}

	public String getOrientationTreeString()
	{
		String s = "<" + this.getOrientationStrategyString();
		if (this.subRanges.size() > 0)
		{
			for (WHRange whr : this.subRanges)
			{
				s += whr.getOrientationTreeString();
			}
		}
		return s + ">";
	}

	public String toString()
	{
		return "{" + this.getOrientationTreeString() + "(" + this.minW + "," + this.maxW + ")-(" + this.minH + "," + this.maxH + ")}";
	}
}
