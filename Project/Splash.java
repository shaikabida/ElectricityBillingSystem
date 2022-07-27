
package Project;
import javax.swing.*;
import java.awt.*;
public class Splash extends JFrame implements Runnable{
	Thread t;   //defining obj of Thread class

    //constructor is called when obj created.
	Splash(){
		//imageIcon class is used to set images on Frame
		//ClassLoader.getSystemResource (class.staticMethod) is used to load the resource(image) from the system.
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
		Image i2=i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);  //taking image from i1 into i2 and scaling it.
		ImageIcon i3=new ImageIcon(i2);  //changing back to imageIcon to pass it in label.
		JLabel image=new JLabel(i3);   //adding image in Label to further add it in frame, as image cannot be directly added.
		add(image);   //adding component to frame


		setVisible(true);
		//setting the size and location of frame dynamically
		// to get the splash effect, we are decreasing the location and increasing the size.
		int x=1;
		for(int i=2;i<600;i+=4,x+=1){
		setSize(i+x,i);
		setLocation(700-((i+x)/2), 400-(i/2));
		try{
			Thread.sleep(5);  //using multi threading to stop code execution for given mili secs

		}catch(Exception e){
			e.printStackTrace();
		}
		}
		t=new Thread(this); //making obj of thread class
		t.start();
		setVisible(true);

	}
    //over-riding the method from runnable interface
	public void run(){
		try{
			Thread.sleep(7000);    //holding frame for 7 secs
			setVisible(false);          //closing frame
			new Login();                  //creating obj of login class
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public static void main(String[] args){
		new Splash();   //obj created.

	}
}
 