
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;


	public Body(double x, double y, double xv, double yv, double mass, String filename) {
		myXPos = x;
		myYPos = y;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}

	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}

	public double getX() {
		return myXPos;	
	}

	public double getY() {
		return myYPos;
	}

	public double getXVel() {
		return myXVel;	
	}

	public double getYVel() {
		return myYVel;	
	}

	public double getMass() {
		return myMass;	
	}

	public String getName() {
		return myFileName;	
	}

	public double calcDistance(Body b) {
		double sum = Math.pow(myXPos-b.myXPos, 2) + Math.pow(myYPos-b.myYPos, 2);
		double dist = Math.sqrt(sum);
		return dist;
	}

	public double calcForceExertedBy(Body b) {
		double gravConstant = 6.67e-11;
		double dist = calcDistance(b);
		double force = (gravConstant*myMass*b.myMass)/(Math.pow(dist,2));
		return force;
	}

	public double calcForceExertedByX(Body b) {
		double f = calcForceExertedBy(b);
		double force = (f*(b.myXPos-myXPos))/calcDistance(b);
		return force;
	}

	public double calcForceExertedByY(Body b) {
		double f = calcForceExertedBy(b);
		double force = (f*(b.myYPos-myYPos))/calcDistance(b);
		return force;
	}

	public double calcNetForceExertedByX(Body[] bodies) {
		double sum = 0;
		for(Body b : bodies) {
			if(! b.equals(this))
			sum = sum + calcForceExertedByX(b);
			
		}
		return sum;
	}

	public double calcNetForceExertedByY(Body[] bodies) {
		double sum = 0;
		for(Body b : bodies) {
			if(! b.equals(this))
			sum = sum + calcForceExertedByY(b);
		}
		return sum;
	}
	
	public void update(double deltaT, double xForce, double yForce) {
		double ax = xForce/myMass;
		double ay = yForce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
		
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}

}
