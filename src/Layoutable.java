import java.util.Vector;

public interface Layoutable
{
	/**
	 * Returns the x-coordinate of the top-left corner of the bounding rectangle of this
	 * layoutable component after the smart layout algorithm has been run.
	 * @return the assigned x-coordinate value of the top-left corner
	 */
	int getAssignedX();

	/**
	 * Returns the y-coordinate of the top-left corner of the bounding rectangle of this
	 * layoutable component after the smart layout algorithm has been run.
	 * @return the assigned y-coordinate value of the top-left corner
	 */
	int getAssignedY();

	/**
	 * Returns the assigned width of the bounding rectangle of this layoutable component
	 * after the smart layout algorithm has been run.
	 * @return the assigned width of the component
	 */
	int getAssignedWidth();

	/**
	 * Returns the assigned height of the bounding rectangle of this layoutable component
	 * after the smart layout algorithm has been run.
	 * @return the assigned height of the component
	 */
	int getAssignedHeight();

	/**
	 * Set the provided width value to this layoutable object.
	 * @param w The width value to be set.
	 */
	void setAssignedWidth(int w);

	/**
	 * Set the provided height value to this layoutable object.
	 * @param h The height value to be set.
	 */
	void setAssignedHeight(int h);

	/**
	 * Set the provided value as the x-coordinate of the top-left corner of this
	 * layoutable object.
	 * @param x The x-coordinate of the top-level corner of this layoutable object.
	 */
	void setAssignedX(int x);

	/**
	 * Set the provided value as the y-coordinate of the top-left corner of this
	 * layoutable object.
	 * @param y The y-coordinate of the top-level corner of this layoutable object.
	 */
	void setAssignedY(int y);

	/**
	 * This method assigns the provided top-left corner coordinates and width and height
	 * values to this layoutable object. For containers, it will then distribute the
	 * provided width or height (based on the orientation strategy of the parameter r)
	 * among the children of this container and recursively call their layout functions.
	 * @param x The x-coordinate of the top-left corner of this layoutable object.
	 * @param y The y-coordinate of the top-left corner of this layoutable object.
	 * @param w The width of this layoutable object.
	 * @param h The height of this layoutable object.
	 * @param r The WHRange object that holds the layout of the children of this
	 *            layoutable object.
	 */
	void layout(int x, int y, int w, int h, WHRange r);

	Vector<WHRange> getRanges();
}