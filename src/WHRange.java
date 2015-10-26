/**
 * Created by bgenc on 10/19/15.
 */
public class WHRange
{
    private int minW, maxW, minH, maxH;
    private int orientationStrategy;
    public static final int SINGLE = 0;
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;
    public static final int OTHER = 3;

    public WHRange(int orientationStrategy, int minWidth, int minHeight, int maxWidth, int maxHeight)
    {
        this.minW = minWidth;
        this.minH = minHeight;
        this.maxW = maxWidth;
        this.maxH = maxHeight;
        this.orientationStrategy = orientationStrategy;
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

    public int getOrientationStrategy() {return this.orientationStrategy; }

    public String toString()
    {
        return "(" + this.minW + "," + this.minH + ")-(" + this.maxW + "," + this.maxH + ")";
    }
}
