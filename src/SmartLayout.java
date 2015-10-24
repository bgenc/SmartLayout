import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
        LayoutComponent compC = new LayoutComponent(new WHRange(150, 100, 150, 250));

        LayoutComponent compD = new LayoutComponent(new WHRange(200, 200, 400, 400));

        LayoutComponent compE = new LayoutComponent(new WHRange(100, 150, 300, 400));
        LayoutComponent compF = new LayoutComponent(new WHRange( 50, 300, 100, 450));

        LayoutComponent compG = new LayoutComponent(new WHRange(100, 200, 150, 350));



        LayoutContainer contY = new LayoutContainer();
        contY.addComponent(compE);
        contY.addComponent(compF);


        LayoutContainer contX = new LayoutContainer();
        contX.addComponent(compA);
        contX.addComponent(compB);
        contX.addComponent(compC);


        LayoutContainer contZ = new LayoutContainer();
        contZ.addComponent(contY);
        contZ.addComponent(compG);



        LayoutContainer contM = new LayoutContainer();
        contM.addComponent(contX);
        contM.addComponent(contZ);
        contM.addComponent(compD);




        System.out.println(contM.getRanges());



    }


    public static void main(String args[])
    {
        SmartLayout app = new SmartLayout();
        app.run();
    }
}