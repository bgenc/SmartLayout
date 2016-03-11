import java.util.Vector;

/**
 * LayoutContainer class.
 * Created by bgenc on 10/19/15.
 */
public class LayoutContainer implements Layoutable
{
	private int aX, aY, aWidth, aHeight;
	private Vector<Layoutable> children;

	public LayoutContainer()
	{
		this.children = new Vector<Layoutable>();
	}

	/**
	 * This method returns all possible layout combinations for this subtree
	 * of children. While computing all layouts, this method
	 * does the following:<br>
	 * <ul>
	 *    <li>
	 *      For each orientation strategy:
	 *      <ul>
	 *         <li>Get all layouts of all children</li>
	 *          <li>Create all layout combinations under this strategy</li>
	 *          <li>Store each combination as a possible layout</li>
	 *      </ul>
	 *    </li>
	 *    <li>Return all possible layouts</li>
	 * </ul>
	 *
	 * Here is how this works on an example:
	 * Let's assume that we have 3 children, A, B and C, under this container. Let's
	 * assume again that A has 2 children, each a component, B is a component itself and
	 * C has two children which are also components. Therefore, A and C can be layed out
	 * in two different ways, namely horizontal or vertical, while B has a single natural
	 * layout. Overall this produces the following combinations of layouts for this
	 * container:<br>
	 * H(H(A)-B-H(C)) or H(H(A)-B-V(C)) or H(V(A)-B-H(C)) or H(V(A)-B-V(C)) or
	 * V(H(A)-B-H(C)) or V(H(A)-B-V(C)) or V(V(A)-B-H(C)) or V(V(A)-B-V(C))<br>
	 *
	 * So, based on the orientation strategy of this container along with the orientation
	 * strategies of its children, we get a total of 8 different layouts.
	 * The way we compute all these layouts is as follows:
	 * <ul>
	 *   <li>We first do the following for a horizontal layout strategy of this container:
	 *    <ul>
	 *       <li>We first insert all possible layouts of A into a vector, namely
	 *       movingRanges</li>
	 *       <li>We than get the single possible layout of B and update movingRanges
	 *       accordingly. In other words, we get each layout in movingRanges and attach
	 *       B to it in a horizontal fashion, and record the new sizes</li>
	 *       <li>Finally, we do the same for C. However, now we have two layouts in
	 *       movingRanges and two layouts for C. So, we multiply them to obtain 4
	 *       layouts and store them all in movingRanges</li>
	 *    </ul>
	 *   </li>
	 *    <li>Once the horizontal strategy is computed, we do the above steps for the
	 *    vertical strategy, which produces four more layouts.</li>
	 *    <li>The total of eight layouts are returned as the result.</li>
	 * </ul>
	 *
	 * @return All possible layout combinations for this container.
	 */
	public Vector<WHRange> getRanges()
	{
		// vec is what we will return at the end.
		Vector<WHRange> vec = new Vector<WHRange>();

		// movingRanges and tempRanges are used temporarily for creating all possible
		// layouts.

		Vector<WHRange> movingRanges = new Vector<WHRange>();
		Vector<WHRange> tempRanges = new Vector<WHRange>();

		// First, the HORIZONTAL orientation strategy

		// We will iterate over all children and one by one integrate them to the solution
		for (Layoutable l : children)
		{
			// Get all possible ranges (layouts) for the layoutable child, l
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
				// If this is not the first child, then we already have some ranges computed
				// in movingRanges.
				// For all other children, we compute the product with existing
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
						// if the height ranges of the two children are not intersecting.

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

		for (Layoutable l : children)
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
				// For all other children, we compute the product with existing
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
						// if the width ranges of the two children are not intersecting.

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
	public int getAssignedX()
	{
		return this.aX;
	}

	@Override
	public int getAssignedY()
	{
		return this.aY;
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

	public void layout(int x, int y, int w, int h, WHRange whr)
	{
		// This is the main method that does the computation of layout
		this.setAssignedX(x);
		this.setAssignedY(y);
		this.setAssignedWidth(w);
		this.setAssignedHeight(h);

		// First, the HORIZONTAL orientation strategy

		if (whr.getOrientationStrategy() == WHRange.HORIZONTAL)
		{
			Vector<WHRange> subRanges = whr.getSubRanges();
			int[] minWvalues = new int[subRanges.size()];
			int[] maxWvalues = new int[subRanges.size()];
			for (int i = 0; i < subRanges.size(); i++)
			{
				minWvalues[i] = subRanges.get(i).getMinWidth();
				maxWvalues[i] = subRanges.get(i).getMaxWidth();
			}

			// TODO Process min and max values to compute optimal values

			int cumW = 0;

			for (int i = 0; i < subRanges.size(); i++) {
				this.children.get(i).layout(cumW, y, minWvalues[i], h, subRanges.get(i));
				cumW += minWvalues[i];
			}
		}

		// Second, the HORIZONTAL orientation strategy

		else if (whr.getOrientationStrategy() == WHRange.VERTICAL)
		{
			Vector<WHRange> subRanges = whr.getSubRanges();
			int[] minHvalues = new int[subRanges.size()];
			int[] maxHvalues = new int[subRanges.size()];
			for (int i = 0; i < subRanges.size(); i++)
			{
				minHvalues[i] = subRanges.get(i).getMinHeight();
				maxHvalues[i] = subRanges.get(i).getMaxHeight();
			}

			// TODO Process min and max values to compute optimal values

			int cumH = 0;

			for (int i = 0; i < subRanges.size(); i++) {
				this.children.get(i).layout(x, cumH, w, minHvalues[i], subRanges.get(i));
				cumH += minHvalues[i];
			}
		}

		else
		{
			System.out.println("Shouldn't be here");
		}
	}

	public void addComponent(Layoutable comp)
	{
		this.children.add(comp);
	}
}