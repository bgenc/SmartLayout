public class SmartLayout
{
    public static void main(String args[])
    {
        System.out.println("Starting test...");

        LayoutComponent compA = new LayoutComponent(new WHRange(100, 100, 200, 200));
        LayoutComponent compB = new LayoutComponent(new WHRange(200, 200, 400, 400));

        LayoutContainer contX = new LayoutContainer();
        contX.addComponent(compA);
        contX.addComponent(compB);

        System.out.println(compA);
        System.out.println(compB);
        //System.out.println(contX);
    }
}