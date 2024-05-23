public class NBody{
	public static String  file;
	public static double readRadius(String file){
		In in=new In(file);
		int n=in.readInt();
		double Radius=in.readDouble();
		return Radius;
	}
	public static Planet[] readPlanets(String file){
		In in=new In(file);
		int n=in.readInt();	
		double s=in.readDouble();
		Planet[] planetArray=new Planet[n];
		for(int i=0;i<n;i++){
			planetArray[i]=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
			
	}
		return planetArray;

	}
	public static void main(String[] args){
		String imageToDraw = "images/starfield.jpg";
		double T=Double.valueOf(args[0]);
		double dt=Double.valueOf(args[1]);
		
		String filename=args[2];
		double universeRadius=readRadius(filename);
		
		Planet[] planetRadius=readPlanets(filename);
		
		StdDraw.setScale(-universeRadius, universeRadius);
		StdDraw.picture(0, 0, imageToDraw);
		StdDraw.enableDoubleBuffering();
		
		for(int t=0;t<=T;t+=dt){
			
			double[] xForces=new double[5];
			double[] yForces=new double[5];
			
			for(int j=0;j<5;j++){
			
			xForces[j]=	planetRadius[j].calcNetForceExertedByX(planetRadius);
		
			yForces[j]=planetRadius[j].calcNetForceExertedByY(planetRadius);
			}
			
			for(int i=0;i<5;i++){
			planetRadius[i].update(dt,xForces[i],yForces[i]);
			}
			
			StdDraw.picture(0, 0, imageToDraw);
			
			for(Planet i:planetRadius){
			i.draw();
			}	
			
			StdDraw.show();
			StdDraw.pause(10);
		}
	
	
	}

}