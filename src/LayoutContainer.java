import java.util.Vector;

/**
 * Created by bgenc on 10/19/15.
 */
public class LayoutContainer implements Layoutable
{
    private int m = 1;
    private Vector<Layoutable> components;

    public LayoutContainer()
    {
        this.components = new Vector<Layoutable>();
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
