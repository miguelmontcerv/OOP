import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class SolarSis {	
public SolarSis(){	 
	BranchGroup group = new BranchGroup();
	Appearance appsol = new Appearance();
	Appearance appmercury = new Appearance();
	Appearance appvenus = new Appearance();
	Appearance appearth = new Appearance();
	Appearance appmars = new Appearance();
        TextureLoader tex=new TextureLoader("TIERRA.JPG", null);
	appearth.setTexture(tex.getTexture());
	tex=new TextureLoader("SOL.JPG", null);
	appsol.setTexture(tex.getTexture());
        tex=new TextureLoader("MERCURIO.JPG", null);				
	appmercury.setTexture(tex.getTexture());	
        tex=new TextureLoader("VENUS.JPG", null);				
	appvenus.setTexture(tex.getTexture());	
        tex=new TextureLoader("MARTE.JPG", null);				
	appmars.setTexture(tex.getTexture());			
	Sphere sol = new Sphere(0.35f, Primitive.GENERATE_NORMALS | 
	Primitive.GENERATE_TEXTURE_COORDS, 32, appsol);
	Sphere mercury = new Sphere(0.035f, Primitive.GENERATE_NORMALS | 			
	Primitive.GENERATE_TEXTURE_COORDS, 32, appmercury);
	Sphere venus = new Sphere(0.040f, Primitive.GENERATE_NORMALS | 			
	Primitive.GENERATE_TEXTURE_COORDS, 32, appvenus);
	Sphere earth = new Sphere(0.045f, Primitive.GENERATE_NORMALS | 			
	Primitive.GENERATE_TEXTURE_COORDS, 32, appearth);
	Sphere mars = new Sphere(0.045f, Primitive.GENERATE_NORMALS | 			
	Primitive.GENERATE_TEXTURE_COORDS, 32, appmars);
	TransformGroup solRotXformGroup = Posi.rotate(sol, new Alpha(-1, 1250));
	TransformGroup mercuryRotXformGroup = Posi.rotate(mercury, new Alpha(-1, 2000));
	TransformGroup venusRotXformGroup = Posi.rotate(venus, new Alpha(-1, 1300));
	TransformGroup earthRotXformGroup = Posi.rotate(earth, new Alpha(-1, 1000));
	TransformGroup marsRotXformGroup = Posi.rotate(mars, new Alpha(-1, 1400));
	TransformGroup mercuryTransXformGroup = Posi.translate(mercuryRotXformGroup, 
	new Vector3f(0.0f, 0.0f, 0.4f));
	TransformGroup venusTransXformGroup = Posi.translate(venusRotXformGroup, 
	new Vector3f(0.0f, 0.0f, 0.6f));
	TransformGroup earthTransXformGroup = Posi.translate(earthRotXformGroup, 
	new Vector3f(0.0f, 0.0f, 0.8f));
	TransformGroup marsTransXformGroup = Posi.translate(marsRotXformGroup, 
	new Vector3f(0.0f, 0.0f, 1.0f));
TransformGroup mercuryRotGroupXformGroup = Posi.rotate(mercuryTransXformGroup, new Alpha(-1, 4000));
TransformGroup venusRotGroupXformGroup = Posi.rotate(venusTransXformGroup, new Alpha(-1, 4900));
TransformGroup earthRotGroupXformGroup = Posi.rotate(earthTransXformGroup, new Alpha(-1, 5000));
TransformGroup marsRotGroupXformGroup = Posi.rotate(marsTransXformGroup, new Alpha(-1, 4600));
    	group.addChild(solRotXformGroup);
	group.addChild(mercuryRotGroupXformGroup);
    	group.addChild(venusRotGroupXformGroup);
    	group.addChild(earthRotGroupXformGroup);
	group.addChild(marsRotGroupXformGroup);
    	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    	Canvas3D canvas = new Canvas3D(config); canvas.setSize(500, 500);
    	SimpleUniverse universe = new SimpleUniverse(canvas);
    	universe.getViewingPlatform().setNominalViewingTransform();
    	universe.addBranchGraph(group);  
    	JFrame f = new JFrame("Planetario");
    	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    	f.add(canvas); f.pack(); f.setVisible(true); }
public static void main(String a[]) { new SolarSis();}
}                                                                         
