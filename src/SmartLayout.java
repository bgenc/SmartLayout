public class SmartLayout
{
    public SmartLayout()
    {
    }

    public void run()
    {
        System.out.println("Starting test...");

        LayoutComponent compA = new LayoutComponent(new WHRange(100, 100, 200, 200));
        LayoutComponent compB = new LayoutComponent(new WHRange(200, 200, 400, 400));

        LayoutContainer contX = new LayoutContainer();
        contX.addComponent(compA);
        contX.addComponent(compB);

        contX.setLayoutOrientation(LayoutContainer.VERTICAL);

        System.out.println(contX.getMinWidth());
    }


    public static void main(String args[])
    {
        SmartLayout app = new SmartLayout();
        app.run();
    }
}