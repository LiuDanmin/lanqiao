import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;

public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas implements Runnable
{
	Thread thread;
	int heroX,heroY,bossX,bossY;
	int flag;
	Image heroImg[][]=new Image[4][3];
	Image currentImg;
	Image bossImg;

	public MainCanvas(){
	  try
	  {
		  //二维数组
		  for(int i=0;i<heroImg.length;i++){
			  for(int j=0;j<heroImg[i].length;j++){
				  heroImg[i][j]=Image.createImage("/sayo"+i+j+".png");//0表示down，1表示up，2表示left，3表示right
			  }
		  }
		  bossImg=Image.createImage("/zuzu000.png");
		  currentImg=heroImg[0][1];
		  flag=1;

		  heroX=120;
		  heroY=120;

		  bossX=0;
		  bossY=0;

		  thread=new Thread(this);
		  thread.start();

		 /*一维数组
		for (int i=0; i<heroDownImg.length; i++){
			heroDownImg[i]=Image.createImage("/sayo"+i+"0.png");
			heroLeftImg[i]=Image.createImage("/sayo"+i+"2.png");
			heroLeftImg[i]=Image.createImage("/sayo"+i+"6.png");
			heroLeftImg[i]=Image.createImage("/sayo"+i+"4.png");
		}
		downImg=Image.createImage("/sayo10.png");
		downImg1=Image.createImage("/sayo00.png");
		downImg2=Image.createImage("/sayo20.png");
		leftImg=Image.createImage("/sayo12.png");
		leftImg1=Image.createImage("/sayo02.png");
		leftImg2=Image.createImage("/sayo22.png");
		rightImg=Image.createImage("/sayo16.png");
		rightImg1=Image.createImage("/sayo06.png");
		rightImg2=Image.createImage("/sayo26.png");
		upImg=Image.createImage("/sayo14.png");
		upImg1=Image.createImage("/sayo04.png");
		upImg2=Image.createImage("/sayo24.png");
		*/

		
	  }
	  catch (IOException e)
	  {
		  e.printStackTrace();
	  }
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(200);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(bossY<heroY){
				bossY++;
			}
			else{
				bossY--;
			}
			if(bossX<heroX){
				bossX++;
			}
			else{
				bossX--;
			}
			repaint();
		}
	}

	public void paint(Graphics g){
		g.setColor(250,200,180);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);
		g.drawImage(bossImg,bossX,bossY,0);
	}

	//定义方法
	public void changePicDirection(int direction){
		if(flag==1){
			currentImg=heroImg[direction][0];
			flag++;
		}
		else if(flag==2){
			currentImg=heroImg[direction][2];
			flag=1;
		}
	}

	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		if(action==LEFT){
			changePicDirection(2);
			heroX=heroX-3;
		}
		else if(action==RIGHT){
			//调用方法
			changePicDirection(3);
			heroX=heroX+3;
		}
		else if(action==UP){
			changePicDirection(1);
			heroY=heroY-3;
		}
		else if(action==DOWN){
			changePicDirection(0);
			heroY=heroY+3;
			}
	}
}