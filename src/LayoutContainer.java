import java.util.Vector;

/**
 * Created by bgenc on 10/19/15.
 */
public class LayoutContainer implements Layoutable
{
    private Vector<Layoutable> components;
    private int preferredLayoutOrientation;

    @Override
    public int getMinWidth()
    {
        return 0;
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

    }

    public void addComponent(Layoutable comp)
    {
        this.components.add(comp);
    }
}
