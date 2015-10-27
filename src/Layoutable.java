import java.util.Vector;

public interface Layoutable
{
	int getAssignedWidth();

	int getAssignedHeight();

	Vector<WHRange> getRanges();
}
