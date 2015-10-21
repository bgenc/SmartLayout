import java.util.Vector;

/**
 * Created by bgenc on 10/19/15.
 */
public class LayoutContainer implements Layoutable
{
    private Vector<Layoutable> components;
    private int preferredLayoutOrientation;
    public static final int HORIZONTAL = 0;
    public static  final int VERTICAL = 1;
    public  static final int OTHER = 2;


    public LayoutContainer()
    {
        this.components = new Vector<Layoutable>();
        this.preferredLayoutOrientation = HORIZONTAL;
    }

    public void setLayoutOrientation(int orientation)
    {
        this.preferredLayoutOrientation = orientation;
    }

    @Override
    public int getMinWidth()
    {
        int returnVal = 0;

        if (this.preferredLayoutOrientation == HORIZONTAL)
        {
            for (Layoutable c : components)
            {
                returnVal += c.getMinWidth();
            }
        }
        else if (this.preferredLayoutOrientation == VERTICAL)
        {
            for (Layoutable c : components)
            {
                if (c.getMinWidth() > returnVal)
                    returnVal = c.getMinWidth();
            }
        }

        return returnVal;
    }

    @Override
    public int getMinHeight()
    {
        return 0;
    }

    @Override
    public int getMaxWidth()
    {
        return 0;
    }

    @Override
    public int getMaxHeight()
    {
        return 0;
    }

    @Override
    public int getAssignedWidth()
    {
        return 0;
    }

    @Override
    public int getAssignedHeight()
    {
        return 0;
    }

    public void layout(int WIDTH, int HEIGHT)
    {
        // This is the main method that does the computation of layout

    }

    public void addComponent(Layoutable comp)
    {
        this.components.add(comp);
    }
}
