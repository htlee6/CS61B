
/**
 * NBody
 */
public class NBody {
    public static double readRadius(String filepath) {
        double universeRadius;
        In in = new In(filepath);
        in.readInt();
        universeRadius = in.readDouble(); 
        return universeRadius;
    }

    public static Planet[] readPlanets(String filepath) {
        In in = new In(filepath);
        in.readInt(); in.readDouble();
        Planet[] parray = new Planet[5]; 
        int iters = 0;
        while(iters < 5) {
            parray[iters] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            iters = iters + 1;
        }
        return parray;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]); 
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = readRadius(filename);
        Planet[] prray = readPlanets(filename);
        String backgroudPic = "./images/starfield.jpg";
        double elapsedTime = 0.0; 

        /** Draw Background */
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-universeRadius, universeRadius);

        for( ; elapsedTime < T;  elapsedTime = elapsedTime + dt) {
            double[] xForces = new double[prray.length];
            double[] yForces = new double[prray.length];
            for(int i = 0; i < prray.length; i++) {
                xForces[i] = prray[i].calcNetForceExertedByX(prray);
                yForces[i] = prray[i].calcNetForceExertedByY(prray); 
                prray[i].update(dt, xForces[i], yForces[i]);
            }


            
            StdDraw.clear();
            StdDraw.picture(0, 0, backgroudPic);
            StdDraw.show();

            /** Draw Planets */
            for(int i = 0; i < prray.length; i++) {
                prray[i].draw();
                StdDraw.show();
            }
        }

        StdOut.printf("%d\n", prray.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < prray.length; i++) {
             StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  prray[i].xxPos, prray[i].yyPos, prray[i].xxVel,
                  prray[i].yyVel, prray[i].mass, prray[i].imgFileName);   
        }
    }
}