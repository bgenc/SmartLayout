import java.util.Vector;

public interface Layoutable
{
	int getAssignedWidth();
	int getAssignedHeight();
	void setAssignedWidth(int w);
	void setAssignedHeight(int h);
	void setAssignedX(int x);
	void setAssignedY(int y);

	Vector<WHRange> getRanges();
}
