public class Planet{
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G=6.67e-11; 
	/**
	*
	*/
	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxPos;
		yyVel=p.yyPos;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}
	
	public double calcDistance(Planet p){
		double dx=this.xxPos-p.xxPos;
		double dy=this.yyPos-p.yyPos;
		double temp;
		double sumSqrtDxDy=dy*dy+dx*dx;
		double r=sumSqrtDxDy/2.0;
		do{
			temp=r;
			r=(temp+(sumSqrtDxDy/temp))/2.0;
		}while((temp-r)!=0);
		return r;
	}
	
	public double calcForceExertedBy(Planet p){
		double r=calcDistance(p);
		double netForce=(Planet.G*this.mass*p.mass)/(r*r);
		return netForce;
	}
	
	public double calcForceExertedByX(Planet p){
		double r=calcDistance(p);
		double netForce=calcForceExertedBy(p);
		double ForceBX=netForce*(p.xxPos-this.xxPos)/r;
		return ForceBX;
	}
	
	public double calcForceExertedByY(Planet p){
		double r=calcDistance(p);
		double netForce=calcForceExertedBy(p);
		double ForceBY=netForce*(p.yyPos-this.yyPos)/r;
		return ForceBY;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double SumForceBX=0;
			for(Planet i:allPlanets){
				if(i.equals(this)){
					continue;
				}
				SumForceBX+=calcForceExertedByX(i);
			}
		return SumForceBX;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double SumForceBY=0;
			for(Planet i:allPlanets){
				if(i.equals(this)){
					continue;
				}
				SumForceBY+=calcForceExertedByY(i);
			}
			return SumForceBY;
	}
	
	public void update(double dt,double fx,double fy){
		double Ax=fx/this.mass;
		double Ay=fy/this.mass;
		
		this.xxVel+=dt*Ax;
		this.yyVel+=dt*Ay;
		
		this.xxPos+=dt*this.xxVel;
		this.yyPos+=dt*this.yyVel;
	}
	public void draw(){
	
		String s="images/"+this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,s);
	
	}
}