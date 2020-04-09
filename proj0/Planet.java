import java.lang.Math;
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dist = 0.0;
        dist = Math.sqrt(Math.pow(this.xxPos-p.xxPos, 2) + Math.pow(this.yyPos-p.yyPos, 2));
        return dist;
    }

    public double calcForceExertedBy(Planet p) {
        double force = 0.0; 
        final double G = 6.67e-11;
        force = G * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double forceX = 0.0; 
        double force = this.calcForceExertedBy(p); 
        forceX = force * -(this.xxPos - p.xxPos) / this.calcDistance(p);
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double forceY = 0.0; 
        double force = this.calcForceExertedBy(p); 
        forceY = force * -(this.yyPos - p.yyPos) / this.calcDistance(p);
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] parray) {
        double netForceX = 0.0; 
        for(int i = 0; i < parray.length; i ++) {
            if(!this.equals(parray[i])){
                netForceX = netForceX + this.calcForceExertedByX(parray[i]);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] parray) {
        double netForceY = 0.0; 
        for(int i = 0; i < parray.length; i ++) {
            if(!this.equals(parray[i])){
                netForceY = netForceY + this.calcForceExertedByY(parray[i]);
            }
        }
        return netForceY;
    }

    public void update(double dT, double fX, double fY) {
        double xAcc = fX / this.mass;
        double yAcc = fY / this.mass;
        xxVel = dT * xAcc + xxVel; 
        yyVel = dT * yAcc + yyVel; 
        xxPos = dT * xxVel + xxPos;
        yyPos = dT * yyVel + yyPos;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}

