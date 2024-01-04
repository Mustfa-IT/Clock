package Clock;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.time.LocalTime;

import javax.swing.JComponent;

class JClock extends JComponent {
		private static final int DEFAULT_WIDTH = 400;
		private static final int DEFAULT_HEIGHT = 400;
		static final double CENTER_X = DEFAULT_WIDTH/2;
		static final double CENTER_Y = DEFAULT_HEIGHT/2;
		private static double radius;
		
		JClock(double radius) {
			JClock.radius = radius ;
		}
		
		String name = "MyComponent";

		@Override
		public void paintComponent(Graphics g) {
		
			Graphics2D g2d = (Graphics2D) g;

			
			
			var circle = new Ellipse2D.Double();
			circle.setFrameFromCenter(CENTER_X, CENTER_Y, CENTER_X + radius, CENTER_Y + radius);
			g2d.draw(circle);
			drawLetters(g2d);
			drawCenterDot(g2d);
			drawMinutsLines(g2d);
			drawClockHands(g2d);
		}
		
		
		
		static void drawMinutsLines(Graphics2D g2d) {
			
			int LinesCount = 60;
			double lineStartingAngel = -90;
			
			int bigLineSize = 12;
			int smallLineSize = 6; 
			
			int size = 0;
	
			double DevideTheCircle = 360/LinesCount;
			
			for(int i = 0; i <=LinesCount; i++) {
				 double LineAngel = DevideTheCircle * i;
				 
				 LineAngel = Math.toRadians(LineAngel);
				 LineAngel += Math.toRadians(lineStartingAngel);
				 
				 
				 if(i%5 == 0) {
					 
					 size = bigLineSize;
				 }else {
					
					 size = smallLineSize;
				 }
				 
				 int lineX1 =(int)( Math.cos(LineAngel) * (radius-size)+ CENTER_X);
				 int lineY1 =(int) (Math.sin(LineAngel) * (radius-size)+ CENTER_Y);
				 int lineX2 =(int)( Math.cos(LineAngel) * (radius)+ CENTER_X);
				 int lineY2 =(int) (Math.sin(LineAngel) * (radius)+ CENTER_X); 
				
				 g2d.drawLine(lineX1, lineY1, lineX2, lineY2);
				 
			 }
				
		}
		
		static void drawCenterDot(Graphics2D g2d ) {
			double dotRadius = 3;
			var circle = new Ellipse2D.Double();
			circle.setFrameFromCenter(CENTER_X, CENTER_Y, CENTER_X + dotRadius, CENTER_Y + dotRadius);
			g2d.draw(circle);
			g2d.fill(circle);
			
		}
		
		static void drawLetters(Graphics2D g2d) {
			
			double letterStartingAngel = -90;
			int LettersCount = 12;
			String[] letters = {"0","1","2","3","4","5","6","7","8","9","10","11","12"}; 
			
			
	
			double DevideTheCircle = 360/LettersCount;
			
			for(int i = 1; i <letters.length; i++) {
				 double letterAngel = DevideTheCircle * i;
	
				 letterAngel = Math.toRadians(letterAngel);
				 letterAngel += Math.toRadians(letterStartingAngel);
				 
				 int letterX =(int)( Math.cos(letterAngel) * (131));
				 int letterY =(int) (Math.sin(letterAngel) * (131));
				 
				 g2d.drawString(letters[i],(int) (letterX+CENTER_X-5),(int) (letterY+CENTER_Y+3));
				 
				 
			 }
			
		}
		
		@Override
		public Dimension getPreferredSize() {
		
			return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		}
		
		 private static void drawClockHands(Graphics2D g2d) {
		        drawHourHand(g2d);
		        drawMinuteHand(g2d);
		        drawSecondHand(g2d);
		    }

		 private static void drawHourHand(Graphics2D g2d) {
			    LocalTime currentTime = LocalTime.now();
			    int hours = currentTime.getHour();
			    int minutes = currentTime.getMinute();

			    double degreesPerHour = 360.0 / 12;
			    

			    double hourAngle = hours * degreesPerHour + minutes * (degreesPerHour / 60);
			    hourAngle = Math.toRadians(hourAngle - 90);

			    int handSize = 80;

			    int handX = (int) (Math.cos(hourAngle) * handSize + CENTER_X);
			    int handY = (int) (Math.sin(hourAngle) * handSize + CENTER_Y);

			    g2d.drawLine((int) CENTER_X, (int) CENTER_Y, handX, handY);
			}

		    private static void drawMinuteHand(Graphics2D g2d) {
		    	int minute = LocalTime.now().getMinute();
		    	double DevideTheCircle = 360/60;
		        double minuteAngle = DevideTheCircle * minute;
		        minuteAngle = Math.toRadians(minuteAngle-90);
		        int handSize = 120;

		        int handX = (int) (Math.cos(minuteAngle) * handSize + CENTER_X);
		        int handY = (int) (Math.sin(minuteAngle) * handSize + CENTER_Y);

		        g2d.drawLine((int) CENTER_X, (int) CENTER_Y, handX, handY);
		    }

		    private static void drawSecondHand(Graphics2D g2d) {
		    	int seconds = LocalTime.now().getSecond();
		    	int handSize = 40;
		    	double DevideTheCircle = 360/60;
		        double secondAngle = DevideTheCircle * seconds;
		        secondAngle = Math.toRadians(secondAngle-90);

		        int handX = (int) (Math.cos(secondAngle) * handSize + CENTER_X);
		        int handY = (int) (Math.sin(secondAngle) * handSize + CENTER_Y);

		        g2d.drawLine((int) CENTER_X, (int) CENTER_Y, handX, handY);
		    }
	}