/**
 * Created by bgenc on 10/19/15.
 */
public class WHRange
{
    private int minW, maxW, minH, maxH;

    public WHRange(int minWidth, int minHeight, int maxWidth, int maxHeight)
    {
        this.minW = minWidth;
        this.minH = minHeight;
        this.maxW = maxWidth;
        this.maxH = maxHeight;
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

    public String toString()
    {
        return "(" + this.minW + "," + this.minH + ")-(" + this.maxW + "," + this.maxH + ")";
    }
}
