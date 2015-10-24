import java.util.Vector;

/**
 * Created by bgenc on 10/19/15.
 */
public class LayoutContainer implements Layoutable
{
    private int m = 1;
    private Vector<Layoutable> components;
    private int preferredLayoutOrientation;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int OTHER = 2;


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
        int returnVal = 0;

        if (this.preferredLayoutOrientation == HORIZONTAL)
        {
            for (Layoutable c : components)
            {
                if (c.getMinHeight() > returnVal)
                    returnVal = c.getMinHeight();
            }
        }
        else if (this.preferredLayoutOrientation == VERTICAL)
        {
            for (Layoutable c : components)
            {
                returnVal += c.getMinHeight();
            }
        }

        return returnVal;
    }

    @Override
    public int getMaxWidth()
    {
        int returnVal = 0;

        if (this.preferredLayoutOrientation == HORIZONTAL)
        {
            for (Layoutable c : components)
            {
                returnVal += c.getMaxWidth();
            }
        }
        else if (this.preferredLayoutOrientation == VERTICAL)
        {
            for (Layoutable c : components)
            {
                if (c.getMaxWidth() > returnVal)
                    returnVal = c.getMaxWidth();
            }
        }

        return returnVal;
    }

    @Override
    public int getMaxHeight()
    {
        int returnVal = 0;

        if (this.preferredLayoutOrientation == HORIZONTAL)
        {
            for (Layoutable c : components)
            {
                if (c.getMaxHeight() > returnVal)
                    returnVal = c.getMaxHeight();
            }
        }
        else if (this.preferredLayoutOrientation == VERTICAL)
        {
            for (Layoutable c : components)
            {
                returnVal += c.getMaxHeight();
            }
        }

        return returnVal;
    }

    public Vector<WHRange> getRanges()
    {
        Vector<WHRange> vec = new Vector<WHRange>();

        for (Layoutable l : components)
        {
            Vector<WHRange> compVec = l.getRanges();

            for (int i = 0; i < compVec.size(); i++)
            {
                // For now it's just adding each components WHRange to a vector
                vec.add(compVec.elementAt(i));
            }

        }

        return vec;
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
