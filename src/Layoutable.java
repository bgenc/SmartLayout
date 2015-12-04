import java.util.Vector;

public interface Layoutable
{
	int getAssignedWidth();
	int getAssignedHeight();
	void setAssignedWidth(int w);
	void setAssignedHeight(int h);
	void setAssignedX(int x);
	void setAssignedY(int y);
	void layout(int x, int y, int w, int h, WHRange r);

	Vector<WHRange> getRanges();

}