
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

/**
 * Constructor with six arguments for Body
 * @param x initial x position
 * @param y initial y position
 * @param xv initial x velocity
 * @param yv initial y velocity
 * @param mass of object
 * @param filename of imag for object animation
 */
	public Body(double x, double y, double xv, double yv, double mass, String filename) {
		myXPos = x;
		myYPos = y;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
/**
 * Constructor that makes a copy of the Body in the parameter
 * @param b used to initialize this body
 */
	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}
	
/**
 * Returns the x position
 * @return x position
 */
	public double getX() {
		return myXPos;	
	}
	
	/**
	 * Returns the y position
	 * @return y position
	 */
	public double getY() {
		return myYPos;
	}
	
	/**
	 * Returns the x velocity
	 * @return x velocity
	 */
	public double getXVel() {
		return myXVel;	
	}
	
	/**
	 * Returns the y velocity
	 * @return y velocity
	 */
	public double getYVel() {
		return myYVel;	
	}
	
	/**
	 * Returns the mass of the body
	 * @return mass
	 */
	public double getMass() {
		return myMass;	
	}

	/**
	 * Returns the name of the file
	 * @return the name of the file
	 */
	public String getName() {
		return myFileName;	
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(Body b) {
		double sum = Math.pow(myXPos-b.myXPos, 2) + Math.pow(myYPos-b.myYPos, 2);
		double dist = Math.sqrt(sum);
		return dist;
	}

	/**
	 * Returns the force exerted on a body by gravity
	 * @param b the other body to which force is calculated
	 * @return the force exerted by a body using a physics formula
	 */
	public double calcForceExertedBy(Body b) {
		double gravConstant = 6.67e-11;
		double dist = calcDistance(b);
		double force = (gravConstant*myMass*b.myMass)/(Math.pow(dist,2));
		return force;
	}

	/**
	 * Returns the force exerted on a body by gravity in the x direction
	 * @param b the other body to which force is calculated
	 * @return the force exerted by gravity on a body in x using a physics formula
	 */
	public double calcForceExertedByX(Body b) {
		double f = calcForceExertedBy(b);
		double force = (f*(b.myXPos-myXPos))/calcDistance(b);
		return force;
	}

	/**
	 * Returns the force exerted on a body by gravity in the y direction
	 * @param b the other body to which force is calculated
	 * @return the force exerted by gravity on a body in y using a physics formula
	 */
	public double calcForceExertedByY(Body b) {
		double f = calcForceExertedBy(b);
		double force = (f*(b.myYPos-myYPos))/calcDistance(b);
		return force;
	}

	/**
	 * Returns the net force exerted on a body by gravity in the x direction
	 * @param b the other body to which force is calculated
	 * @return the net force exerted by gravity on a body in x using a physics formula
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double sum = 0;
		for(Body b : bodies) {
			if(! b.equals(this))
			sum = sum + calcForceExertedByX(b);
			
		}
		return sum;
	}

	/**
	 * Returns the net force exerted on a body by gravity in the y direction
	 * @param b the other body to which force is calculated
	 * @return the net force exerted by gravity on a body in y using a physics formula
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double sum = 0;
		for(Body b : bodies) {
			if(! b.equals(this))
			sum = sum + calcForceExertedByY(b);
		}
		return sum;
	}
	
	/**
	 * Mutator method that updates the values of the
	 * x, y position and x, y velocities using new formulas
	 * @param deltaT small time steps
	 * @param xForce net force acted on in x
	 * @param yForce net force acted on in y
	 */
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
	
	/**
	 * Draws the bodies on the picture
	 */
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}

}
