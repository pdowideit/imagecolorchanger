package imagecolorchanger;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6342252179307369875L;
	private JButton button_prev_image;
	private JButton button_next_image;
	private JPanel main_panel;
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
		button_prev_image.addActionListener((ActionListener) this);
		
		nav_panel.add(button_prev_image);
		nav_panel.add(button_next_image);
		
		settings_panel.setLayout(new GridLayout(20,2));
		
		w1 = new JLabel("Wand Fenster:");
		w2 = new JLabel("Wand Bett:");
		w3 = new JLabel("Wand Regal:");
		w4 = new JLabel("Wand Schreibtisch:");
		
		
		String[] colors = {"weiß","schwarz","grün","blau","gelb","orange","rot","lila"};
		String[] colors2 = {"hellblau","weiß","schwarz","grün","blau","gelb","orange","rot","lila"};
		
		JComboBox<String> cb1 = new JComboBox<String>(colors);
		JComboBox<String> cb2 = new JComboBox<String>(colors);
		JComboBox<String> cb3 = new JComboBox<String>(colors2);
		JComboBox<String> cb4 = new JComboBox<String>(colors);
		
		settings_panel.add(w1);
		settings_panel.add(cb1);
		settings_panel.add(w2);
		settings_panel.add(cb2);
		settings_panel.add(w3);
		settings_panel.add(cb3);
		settings_panel.add(w4);
		settings_panel.add(cb4);
		
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.button_prev_image){
			updateImage(Main.prevImg());
		}
		if(e.getSource()==this.button_next_image){
			updateImage(Main.nextImg());
		}
		
		if(e.getSource()==this.button_next_image || e.getSource()==this.button_prev_image)
		{
			switch(Main.imageNum)
			{
				case 1:
					Font f = w1.getFont();
					w1.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
					break;
				case 2:
					f = w2.getFont();
					w2.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
					break;
				case 3:
					f = w3.getFont();
					w3.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
					break;
				case 4:
					f = w4.getFont();
					w4.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
					break;
			}
		}
		
	}
	
	private void updateImage(String url)
	{
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
			};Image img = ImageIO.read(f);
			img = Main.getScaledImage(img,imageWidth,imageHeight);
			ImageIcon image = new ImageIcon(img);
			label = new JLabel();
			label.setIcon(image);
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
