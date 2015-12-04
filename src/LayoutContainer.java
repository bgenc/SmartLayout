import java.util.Vector;

/**
 * LayoutContainer class.
 * Created by bgenc on 10/19/15.
 */
public class LayoutContainer implements Layoutable
{
	private int aX, aY, aWidth, aHeight;
	private Vector<Layoutable> components;

	public LayoutContainer()
	{
		this.components = new Vector<Layoutable>();
	}

	/**
	 * This method returns all possible layout combinations for this subtree
	 * of components and containers. While computing all layouts, this method
	 * does the following:
	 * - For each orientation strategy:
	 * --- Get all layouts of all children
	 * --- Create all layout combinations under this strategy
	 * --- Store each combination as a possible layout
	 * - Return all possible layouts
	 *
	 * @return All possible layout combinations for this subtree.
	 */
	public Vector<WHRange> getRanges()
	{
		Vector<WHRange> vec = new Vector<WHRange>();

		// First, the HORIZONTAL orientation strategy

		Vector<WHRange> movingRanges = new Vector<WHRange>();
		Vector<WHRange> tempRanges = new Vector<WHRange>();

		for (Layoutable l : components)
		{
			Vector<WHRange> compVec = l.getRanges();
			tempRanges.clear();

			if (movingRanges.isEmpty())
			{
				// If this is the first component, we simply fill the movingRanges data
				// based on this component

				for (WHRange whr : compVec)
				{
					WHRange newRange = new WHRange(whr);
					newRange.setOrientationStrategy(WHRange.HORIZONTAL);
					newRange.addSubRange(whr);
					movingRanges.add(newRange);
				}
			}
			else
			{
				// For all other components, we compute the product with existing
				// movingRanges data and obtain the new movingRanges.

				for (WHRange whr : movingRanges)
				{
					for (WHRange whrNew : compVec)
					{
						// Since this is horizontal, we know how to compute the new range:
						int newMinWidth = whr.getMinWidth() + whrNew.getMinWidth();
						int newMaxWidth = whr.getMaxWidth() + whrNew.getMaxWidth();
						int newMinHeight = Math.max(whr.getMinHeight(), whrNew.getMinHeight());
						int newMaxHeight = Math.min(whr.getMaxHeight(), whrNew.getMaxHeight());

						// Make sure that newMaxHeight is greater than newMinHeight.
						// Otherwise we have an infeasible layout. This only should happen
						// if the height ranges of the two components are not intersecting.

						if (newMaxHeight >= newMinHeight)
						{
							// Now create the new range object and add it to the temp vector:
							WHRange newRange = new WHRange(WHRange.HORIZONTAL,
							                               newMinWidth, newMaxWidth,
							                               newMinHeight, newMaxHeight);
							newRange.addSubRanges(whr.getSubRanges());
							newRange.addSubRange(whrNew);
							tempRanges.add(newRange);
						}
					}
				}

				movingRanges.clear();
				movingRanges.addAll(tempRanges);
			}
		}

		vec.addAll(movingRanges);

		// Now, the VERTICAL orientation strategy

		movingRanges.clear();

		for (Layoutable l : components)
		{
			Vector<WHRange> compVec = l.getRanges();
			tempRanges.clear();

			if (movingRanges.isEmpty())
			{
				// If this is the first component, we simply fill the movingRanges data
				// based on this component

				for (WHRange whr : compVec)
				{
					WHRange newRange = new WHRange(whr);
					newRange.setOrientationStrategy(WHRange.VERTICAL);
					newRange.addSubRange(whr);
					movingRanges.add(newRange);
				}
			}
			else
			{
				// For all other components, we compute the product with existing
				// movingRanges data and obtain the new movingRanges.

				for (WHRange whr : movingRanges)
				{
					for (WHRange whrNew : compVec)
					{
						// Since this is vertical, we know how to compute the new range:
						int newMinWidth = Math.max(whr.getMinWidth(), whrNew.getMinWidth());
						int newMaxWidth = Math.min(whr.getMaxWidth(), whrNew.getMaxWidth());
						int newMinHeight = whr.getMinHeight() + whrNew.getMinHeight();
						int newMaxHeight = whr.getMaxHeight() + whrNew.getMaxHeight();

						// Make sure that newMaxWidth is greater than newMinWidth.
						// Otherwise we have an infeasible layout. This only should happen
						// if the width ranges of the two components are not intersecting.

						if (newMaxWidth >= newMinWidth)
						{
							// Now create the new range object and add it to the temp vector:
							WHRange newRange = new WHRange(WHRange.VERTICAL,
							                               newMinWidth, newMaxWidth,
							                               newMinHeight, newMaxHeight);
							newRange.addSubRanges(whr.getSubRanges());
							newRange.addSubRange(whrNew);
							tempRanges.add(newRange);
						}
					}
				}

				movingRanges.clear();
				movingRanges.addAll(tempRanges);
			}
		}

		vec.addAll(movingRanges);

		return vec;
	}

	@Override
	public int getAssignedWidth()
	{
		return this.aWidth;
	}

	@Override
	public int getAssignedHeight()
	{
		return this.aHeight;
	}

	@Override
	public void setAssignedWidth(int w)
	{
		this.aWidth = w;
	}

	@Override
	public void setAssignedHeight(int h)
	{
		this.aHeight = h;
	}

	@Override
	public void setAssignedX(int x)
	{
		this.aX = x;
	}

	@Override
	public void setAssignedY(int y)
	{
		this.aY = y;
	}

	public void layout(int WIDTH, int HEIGHT, WHRange whr)
	{
		// This is the main method that does the computation of layout

	}

	public void addComponent(Layoutable comp)
	{
		this.components.add(comp);
	}
}
