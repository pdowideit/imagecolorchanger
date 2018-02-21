package imagecolorchanger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class MainUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6342252179307369875L;
	private JButton button_prev_image;
	private JButton button_next_image;
	private JPanel img_panel;
	private JPanel nav_panel;
	private JPanel settings_panel;
	private GroupLayout l;
	private JLabel label;
	private int imageHeight;
	private int imageWidth;
	private double scale = 0.8;
	private JLabel w1;
	private JLabel w2;
	private JLabel w3;
	private JLabel w4;
	private JTextField tf1; 
	private JTextField tf2; 
	private JTextField tf3; 
	private JTextField tf4;
	private Color to1; 
	private Color to2; 
	private Color to3;
	private Color to4;
	private Color to5;
	private Color from1; 
	private Color from2; 
	private Color from3;
	private Color from4;
	private Color from5;
	
	
	
	
	public MainUI()
	{
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension d = new Dimension((int)	(screensize.getWidth()*scale),(int)	(screensize.getHeight()*scale));
		this.setSize(d);
		this.setResizable(true);
		this.setBounds((int)	(screensize.getWidth()*((1-scale)*0.5)), (int)	(screensize.getHeight()*((1-scale)*0.5)), (int)	d.getWidth(), (int)	d.getHeight());
		this.setTitle("Image Color Changer");
		File f = new File("./img/buckets/bucket128x128.png");
		try
		{
			Image image = ImageIO.read(f);
			this.setIconImage(image);
		}
		catch(IOException e)	{	e.printStackTrace();	}
		
		createComponents((int)	d.getHeight(),(int)	d.getWidth());
	}
	
	public void startup()
	{
		this.setVisible(true);
	}
	
	public void createComponents(int height, int width)
	{
		imageHeight = (int) (height*scale);
		imageWidth =  (int) (width*scale);
		JPanel main_panel = new JPanel(); 
		l = new GroupLayout(main_panel);
		main_panel.setLayout(l);
		
		
		img_panel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
			    return new Dimension(imageWidth,imageHeight);
			}
		};
		File f = new File("./img/room/1.jpg");
		try{
			Image img = ImageIO.read(f);
			img = Main.getScaledImage(img,imageWidth,imageHeight);
			ImageIcon image = new ImageIcon(img);
			label = new JLabel();
			label.setIcon(image);
			img_panel.add(label);
		}
		catch(IOException e){e.printStackTrace();}
		
		
		nav_panel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
			    return new Dimension(imageWidth, (int) (height*(1.0-scale)));
			}
		};
		
		
		settings_panel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
			    return new Dimension((int) (width*0.2), height);
			}
		};
		
		
		l.setHorizontalGroup(
	            l.createSequentialGroup()
	            	.addGroup(l.createParallelGroup()
	                	.addComponent(img_panel)
	                	.addComponent(nav_panel))
	                .addComponent(settings_panel));
	                
	    l.setVerticalGroup(
	            l.createParallelGroup()
	                .addGroup(l.createSequentialGroup()
	                	.addComponent(img_panel)
	                	.addComponent(nav_panel))
	                .addComponent(settings_panel));
		                
		this.setContentPane(main_panel);
			
		button_next_image = new JButton(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
			    return new Dimension((int) (height*0.1), (int) (height*0.1));
			}
		};
		Icon arrow_next = new ImageIcon("./img/arrows/next128x128.png");
		button_next_image.setIcon((Icon) arrow_next);
		button_next_image.addActionListener(this);
		
		button_prev_image = new JButton(){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
			    return new Dimension((int) (height*0.1), (int) (height*0.1));
			}
		};
		Icon arrow_prev = new ImageIcon("./img/arrows/back128x128.png");
		button_prev_image.setIcon((Icon) arrow_prev);
		button_prev_image.addActionListener(this);
		
		nav_panel.add(button_prev_image);
		nav_panel.add(button_next_image);
		
		settings_panel.setLayout(new GridLayout(20,2));
		
		w1 = new JLabel("Wand Fenster:");
		w2 = new JLabel("Wand Bett:");
		w3 = new JLabel("Wand Regal:");
		w4 = new JLabel("Wand Schreibtisch:");
		
		tf1 = new JTextField("#A3A623");
		tf2 = new JTextField("#002364");
		tf3 = new JTextField("#640023");
		tf4 = new JTextField("#FF6400");
		
		Action action = new AbstractAction()
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				if(e.getSource() == tf1)
		        {
					updateImage();
		        }
				else if(e.getSource() == tf2)
		        {
					updateImage();
		        }
				else if(e.getSource() == tf3)
		        {
					updateImage();
		        }
				else if(e.getSource() == tf4)
		        {
					updateImage();
		        }
		    }
		};

		tf1.addActionListener(action);
		tf2.addActionListener(action);
		tf3.addActionListener(action);
		tf4.addActionListener(action);
		
		settings_panel.add(w1);
		settings_panel.add(tf1);
		settings_panel.add(w2);
		settings_panel.add(tf2);
		settings_panel.add(w3);
		settings_panel.add(tf3);
		settings_panel.add(w4);
		settings_panel.add(tf4);
		
		from1 = Color.decode(tf1.getText());
		from2 = Color.decode(tf2.getText());
		from3 = Color.decode(tf3.getText());
		from4 = Color.decode(tf4.getText());
		from5 = Color.decode("#23B14D");
		
		updateImage(("./img/room/1.jpg"));
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.button_prev_image){
			updateImage(Main.prevImg());
		}
		if(e.getSource()==this.button_next_image){
			updateImage(Main.nextImg());
		}
	}
	private String currentUrl;

	private void updateImage()
	{
		updateImage(currentUrl);
	}
	
	private void updateImage(String url)
	{
		currentUrl = url;
		File f = new File(url);
		try{
			this.remove(img_panel);
			img_panel.remove(label);
			img_panel = new JPanel(){
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Dimension getPreferredSize() {
				    return new Dimension(imageWidth,imageHeight);
				}
			};
			
			to1 = Color.decode(tf1.getText()); 
			System.out.println(tf1.getText());
			to2 = Color.decode(tf2.getText()); 
			to3 = Color.decode(tf3.getText()); 
			to4 = Color.decode(tf4.getText()); 
			to5 = Color.decode("#FFFFFF"); //
			
			Image image = ImageIO.read(f);
			image = Main.getScaledImage(image,imageWidth,imageHeight);
			BufferedImage bimage = Main.toBufferedImage(image);
			
			BufferedImageOp lookup = new LookupOp(new ColorMapper(from1, to1), null);
			BufferedImage convertedImage = lookup.filter(bimage, null);
			
			BufferedImageOp lookup2 = new LookupOp(new ColorMapper(from2, to2), null);
			BufferedImage convertedImage2 = lookup2.filter(convertedImage, null);
			
			BufferedImageOp lookup3 = new LookupOp(new ColorMapper(from3, to3), null);
			BufferedImage convertedImage3 = lookup3.filter(convertedImage2, null);
			
			BufferedImageOp lookup4 = new LookupOp(new ColorMapper(from4, to4), null);
			BufferedImage convertedImage4 = lookup4.filter(convertedImage3, null);
			
			BufferedImageOp lookup5 = new LookupOp(new ColorMapper(from5, to5), null);
			BufferedImage convertedImage5 = lookup5.filter(convertedImage4, null);
			
			ImageIcon imgIcon = new ImageIcon(convertedImage5);
			label = new JLabel();
			label.setIcon(imgIcon);
			img_panel.add(label);
			
			l.setHorizontalGroup(
		            l.createSequentialGroup()
		            	.addGroup(l.createParallelGroup()
		                	.addComponent(img_panel)
		                	.addComponent(nav_panel))
		                .addComponent(settings_panel));
		                
		    l.setVerticalGroup(
		            l.createParallelGroup()
		                .addGroup(l.createSequentialGroup()
		                	.addComponent(img_panel)
		                	.addComponent(nav_panel))
		                .addComponent(settings_panel));
		}
		catch(Exception e){e.printStackTrace();}
		
	}
	
	
}
