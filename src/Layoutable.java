import java.util.Vector;

public interface Layoutable
{
    int getMinWidth();
    int getMinHeight();
    int getMaxWidth();
    int getMaxHeight();
    int getAssignedWidth();
    int getAssignedHeight();
    Vector<WHRange> getRanges();
}
