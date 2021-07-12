
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;




public class Interface extends JFrame implements ActionListener{
    
	private static final long serialVersionUID = 1L;
        JPanel entete = new JPanel();
	JLabel lblNewLabel = new JLabel("Detection Yeux");
        JPanel pied = new JPanel();
	JButton buttonRechercher = new JButton("Rechercher");
	JButton btnVisage = new JButton("Visage");
	JButton btnAnnuler = new JButton("Annuler");
	JButton btnYeux = new JButton("Yeux");
	JPanel centre = new JPanel();
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	JLabel lblVisage = new JLabel("Visage :");
	JLabel lblYeux = new JLabel("Yeux :");
	JLabel lblCouleur = new JLabel("Couleur :");
	JLabel lblDetect = new JLabel("Detect\u00E9 :");
	JLabel lblNbvis = new JLabel("   -");
	JLabel lblNbyeux = new JLabel("   -");
	JPanel lblClryeux = new JPanel();
	JLabel lblDetect_1 = new JLabel("   -");
        JButton btnnext=new JButton( new ImageIcon("img/next.jpg"));
        JButton btnenrgyeux=new JButton( new ImageIcon("img/save.jpg"));
        JButton btnprev=new JButton( new ImageIcon("img/previous.jpg"));
	JPanel panel_3 = new JPanel();
        JLabel image = new JLabel(new ImageIcon("img/blank.jpg"));
	JPanel panel_2 = new JPanel();
	JButton btnOuvrirPhoto = new JButton("Ouvrir photo");
	JButton btnEffacerPhoto = new JButton("Effacer photo");
        JButton button = new JButton("Enregistrer");
        JMenuBar menuBar = new JMenuBar();
	JMenu menuFichier = new JMenu("Fichier");
	JMenuItem menuitemOuvrir = new JMenuItem("Ouvrir");
	JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
	JMenuItem mntmEffacer = new JMenuItem("Effacer");
	JMenuItem mntmQuitter = new JMenuItem("Quitter");
	JMenu mnEdition = new JMenu("Edition");
	JMenu mnDetecter = new JMenu("Detecter");
	JMenuItem mntmVisage = new JMenuItem("Visage");
	JMenuItem mntmYeux = new JMenuItem("Yeux");
	JMenuItem mntmRechercher = new JMenuItem("Rechercher");
        String chemin_image=null;
        String chemin_dest=null;
        int h,iter=0;
        CascadeClassifier dtecteurvisage = new CascadeClassifier("C:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt2.xml");
        CascadeClassifier dtecteuryeux = new CascadeClassifier("C:\\opencv\\sources\\data\\haarcascades_cuda\\haarcascade_eye_tree_eyeglasses.xml");
        Mat picture;
        MatOfRect visages = new MatOfRect();
        MatOfRect yeux = new MatOfRect();
        
	Interface(){
	this.setTitle("Detection des yeux");
	this.setBounds(100, 100, 580, 460);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	entete.setBackground(new Color(245, 245, 245));
	entete.setPreferredSize(new Dimension(10, 50));
	this.getContentPane().add(entete, BorderLayout.NORTH);
	entete.setLayout(null);
	
	lblNewLabel.setForeground(new Color(72, 61, 139));
	lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
	lblNewLabel.setBounds(160, 0, 280, 60);
	entete.add(lblNewLabel);
	
	pied.setBackground(new Color(245, 245, 245));
	pied.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pied.setPreferredSize(new Dimension(10, 45));
	this.getContentPane().add(pied, BorderLayout.SOUTH);
	pied.setLayout(null);
	
	buttonRechercher.setForeground(new Color(0, 0, 128));
	buttonRechercher.setBackground(new Color(220, 220, 220));
	buttonRechercher.setBounds(303, 13, 120, 25);
        buttonRechercher.addActionListener(this);
	pied.add(buttonRechercher);
	

	btnVisage.setForeground(new Color(0, 0, 128));
	btnVisage.setBackground(new Color(220, 220, 220));
	btnVisage.setBounds(85, 13, 97, 25);
        btnVisage.addActionListener(this);
	pied.add(btnVisage);
	
	btnAnnuler.setForeground(new Color(0, 0, 128));
	btnAnnuler.setBackground(new Color(220, 220, 220));
	btnAnnuler.setBounds(435, 13, 97, 25);
        btnAnnuler.addActionListener(this);
	pied.add(btnAnnuler);
	
	btnYeux.setForeground(new Color(0, 0, 128));
	btnYeux.setBackground(new Color(220, 220, 220));
	btnYeux.setBounds(194, 13, 97, 25);
        btnYeux.addActionListener(this);
	pied.add(btnYeux);
	
	centre.setBackground(new Color(245, 245, 245));
	this.getContentPane().add(centre, BorderLayout.CENTER);
	centre.setLayout(null);
	
	panel.setBackground(new Color(245, 245, 245));
	panel.setBounds(376, 20, 196, 265);
	centre.add(panel);
	panel.setLayout(null);
	
	panel_1.setBackground(new Color(245, 245, 245));
	panel_1.setBorder(new TitledBorder(null, "Infos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_1.setBounds(10, 145, 172, 120);
	panel.add(panel_1);
	panel_1.setLayout(null);

        lblVisage.setBounds(12, 23, 56, 16);
	panel_1.add(lblVisage);
	
	lblYeux.setBounds(12, 46, 56, 16);
	panel_1.add(lblYeux);
	
	lblCouleur.setBounds(12, 68, 56, 16);
	panel_1.add(lblCouleur);
	
	lblDetect.setBounds(12, 91, 56, 16);
	panel_1.add(lblDetect);
	
	lblNbvis.setBounds(80, 23, 80, 16);
	panel_1.add(lblNbvis);
	
	lblNbyeux.setBounds(80, 46, 80, 16);
	panel_1.add(lblNbyeux);
	
	lblClryeux.setBounds(80, 68, 80, 16);
	panel_1.add(lblClryeux);
	
	lblDetect_1.setBounds(80, 91, 56, 16);
	panel_1.add(lblDetect_1);
	
        btnnext.setBounds(145, 88, 35, 35);
        btnnext.setEnabled(false);
        btnnext.addActionListener(this);
	panel.add(btnnext);
        
        btnenrgyeux.setBounds(145, 44, 35, 35);
        btnenrgyeux.setEnabled(false);
        btnenrgyeux.addActionListener(this);
	panel.add(btnenrgyeux);
        
        btnprev.setBounds(145, 0, 35, 35);
        btnprev.setEnabled(false);
        btnprev.addActionListener(this);
	panel.add(btnprev);

        panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_3.setBounds(12, 0, 125, 125);
	panel.add(panel_3);
	panel_3.setLayout(null);
	
		
	panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_2.setBounds(12, 18, 350, 250);
	centre.add(panel_2);
	panel_2.setLayout(null);
	
	btnOuvrirPhoto.setForeground(new Color(0, 0, 128));
	btnOuvrirPhoto.setBackground(new Color(220, 220, 220));
	btnOuvrirPhoto.setBounds(12, 275, 125, 25);
        btnOuvrirPhoto.addActionListener(this);
	centre.add(btnOuvrirPhoto);
	

	btnEffacerPhoto.setForeground(new Color(0, 0, 128));
	btnEffacerPhoto.setBackground(new Color(220, 220, 220));
	btnEffacerPhoto.setBounds(142, 275, 114, 25);
        btnEffacerPhoto.addActionListener(this);
	centre.add(btnEffacerPhoto);
	
	button.setForeground(new Color(0, 0, 128));
	button.setBackground(new Color(220, 220, 220));
	button.setBounds(261, 275, 100, 25);
        button.addActionListener(this);
	centre.add(button);

        menuBar.setBackground(new Color(245, 245, 245));
	menuBar.setPreferredSize(new Dimension(0, 25));
	this.setJMenuBar(menuBar);
	
	menuBar.add(menuFichier);
	
	menuitemOuvrir.addActionListener(this);
        menuFichier.add(menuitemOuvrir);
	
	mntmEffacer.addActionListener(this);
        menuFichier.add(mntmEffacer);
	
	
        mntmQuitter.addActionListener(this);
        menuFichier.add(mntmQuitter);
	
	menuBar.add(mnEdition);
	
	mnEdition.add(mnDetecter);
	
	mntmVisage.addActionListener(this);
        mnDetecter.add(mntmVisage);
	
	mntmYeux.addActionListener(this);
        mnDetecter.add(mntmYeux);
        
        mntmEnregistrer.addActionListener(this);
	mnEdition.add(mntmEnregistrer);
	
	mntmRechercher.addActionListener(this);
        mnEdition.add(mntmRechercher);
	
	this.setVisible(true);
        this.setLocationRelativeTo(null);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
        if(arg0.getSource()==btnOuvrirPhoto){
            JFileChooser chooser = new JFileChooser(); 
            chooser.setCurrentDirectory(new java.io.File("./imagesvisages"));
            chooser.setDialogTitle(null);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
                chemin_image=chooser.getSelectedFile().getPath();
                image = new JLabel(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));
                Graphics g = panel_2.getGraphics();
                Image image5 = Toolkit.getDefaultToolkit().getImage(chooser.getSelectedFile().getAbsolutePath());
                h=image5.getHeight(null);
                g.drawImage(image5, 0, 0, 350, 250 , 0, 0, image5.getWidth(null), image5.getHeight(null), null);
                g.drawRect(0, 0, 349, 249);
                g = panel_3.getGraphics();
                image5 = Toolkit.getDefaultToolkit().getImage("img/blank.jpg");
                g.drawImage(image5, 0, 0, null);
                panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
                btnnext.setEnabled(false);
                btnprev.setEnabled(false);
                btnenrgyeux.setEnabled(false);
            }
        }
                
        if(arg0.getSource()==btnEffacerPhoto){
            visages = new MatOfRect();
            image = new JLabel(new ImageIcon("img/blank.jpg"));
            image.setPreferredSize(new Dimension(350, 250));
            image.setBackground(new Color(240, 240, 240));
            Graphics g = panel_2.getGraphics();
            Image image5 = Toolkit.getDefaultToolkit().getImage("img/blank.jpg");
            g.drawImage(image5, 0, 0, null);
            g = panel_3.getGraphics();
            image5 = Toolkit.getDefaultToolkit().getImage("img/blank.jpg");
            g.drawImage(image5, 0, 0, null);
            panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            chemin_image=null;
            lblNbvis.setText("   -");
            lblNbyeux.setText("   -");
            lblDetect_1.setText("   -");
            btnnext.setEnabled(false);
            btnprev.setEnabled(false);
            btnenrgyeux.setEnabled(false);
        }
        
        if(arg0.getSource()==button){
            File rep = new File ("imageOutput");
            if(rep.isDirectory()){
                File [] fileList = rep.listFiles();
                for(int i = 0;i<fileList.length;i++){
                    fileList[i].delete();
                }
            }
            int i=0;
            Mat dest = null;
            for (Rect rect : visages.toArray()) {
                chemin_dest="imageOutput/visage"+i+".jpg";
                Imgproc.rectangle(picture, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(245, 169, 188), 2*(h/250));
                dest=picture.submat(new Rect(rect.x,rect.y,rect.width,rect.height));
                Imgcodecs.imwrite(chemin_dest, dest);
                i++;
            }
            Graphics g = panel_3.getGraphics();
            BufferedImage buff;
            try{
            MatOfByte mem = new MatOfByte();
            Imgcodecs.imencode(".jpg", dest, mem);
            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
            buff = (BufferedImage) im;
             g.drawImage(buff, 0, 0, 125, 125 , 0, 0, buff.getWidth(), buff.getHeight(), null);
             g.drawRect(0, 0, 124, 124);
            }catch(IOException e){}
            iter=0;
            if(visages.toArray().length>1){
            btnenrgyeux.setEnabled(true);
            btnnext.setEnabled(true);
            }
            lblNbvis.setText((iter+1)+"/"+visages.toArray().length);
        }
         
        if(arg0.getSource()==buttonRechercher){
            int x1 = 0,y1 = 0;
            for (Rect rect : visages.toArray()) {
                Mat dest=picture.submat(new Rect(rect.x,rect.y,rect.width,rect.height));
                dtecteuryeux.detectMultiScale(dest, yeux);
                for(Rect r : yeux.toArray()){
                  x1=r.x+r.width/2;
                  y1=r.y+r.height/2;

                }
            }
            try {
                
                File file = new File(chemin_image);
                BufferedImage image = ImageIO.read(file);
System.out.println(""+chemin_image);
System.out.println(""+x1+"."+y1);

                int r=0,g=0,b=0;
                for(int x=x1;x<x1+4;x++){
                    for(int y=y1;y<y1+4;y++){
                        int clr = image.getRGB(x1, y1);
                        int red = (clr & 0x00ff0000) >> 16;
                        int green = (clr & 0x0000ff00) >> 8;
                        int blue = clr & 0x000000ff;
                        r=r+red;
                        g=g+green;
                        b=b+blue;
                    }
                }              
                r=r/16;
                g=g/16;
                b=b/16;
                System.out.println("R=" + r);
                System.out.println("G=" + g);
                System.out.println("B=" + b);
                lblClryeux.setBackground(new Color(r, g, b));
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
	if(arg0.getSource()==btnprev){
            if(iter>=0){
                btnnext.setEnabled(true);
                iter--;
                lblNbvis.setText((iter+1)+"/"+visages.toArray().length);
                if(iter==0){
                    btnprev.setEnabled(false);
                }
                Graphics g = panel_3.getGraphics();
                Image image5 = Toolkit.getDefaultToolkit().getImage("imageOutput/visage"+iter+".jpg");
                g.drawImage(image5, 0, 0, 125, 125 , 0, 0, image5.getWidth(null), image5.getHeight(null), null);
                g.drawRect(0, 0, 124, 124);
            }
        }
        
        if(arg0.getSource()==btnnext){
            if(iter<visages.toArray().length){
                btnprev.setEnabled(true);
                iter++;
                lblNbvis.setText((iter+1)+"/"+visages.toArray().length);
                if(iter==(visages.toArray().length-1)){
                    btnnext.setEnabled(false);
                }
                Graphics g = panel_3.getGraphics();
                Image image5 = Toolkit.getDefaultToolkit().getImage("imageOutput/visage"+iter+".jpg");
                g.drawImage(image5, 0, 0, 125, 125 , 0, 0, image5.getWidth(null), image5.getHeight(null), null);
                g.drawRect(0, 0, 124, 124);
            }
        }
        
        if(arg0.getSource()==btnenrgyeux){
            
        }
        
        if(arg0.getSource()==btnVisage){
            picture = Imgcodecs.imread(chemin_image);
            dtecteurvisage.detectMultiScale(picture, visages);
            for (Rect rect : visages.toArray()) {
                Imgproc.rectangle(picture, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(245, 169, 188), 2*(h/250));
            }
            Graphics g = panel_2.getGraphics();
            BufferedImage buff;
            try{
            MatOfByte mem = new MatOfByte();
            Imgcodecs.imencode(".jpg", picture, mem);
            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
            buff = (BufferedImage) im;
            g.drawImage(buff, 0, 0, 350, 250 , 0, 0, buff.getWidth(), buff.getHeight(), null);
            g.drawRect(0, 0, 349, 249);
            }catch(IOException e){}
        }
        
        if(arg0.getSource()==btnYeux){
            for (Rect rect : visages.toArray()) {
                Mat dest=picture.submat(new Rect(rect.x,rect.y,rect.width,rect.height));
                dtecteuryeux.detectMultiScale(dest, yeux);
                for(Rect r : yeux.toArray()){
                    
                    Imgproc.rectangle(dest, new Point(r.x,r.y), new Point(r.x+r.width,r.y+r.height), new Scalar(0, 255, 127), 2*(h/250));

                }
            }
            Graphics g = panel_2.getGraphics();
            BufferedImage buff;
            try{
            MatOfByte mem = new MatOfByte();
            Imgcodecs.imencode(".jpg", picture, mem);
            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
            buff = (BufferedImage) im;
             g.drawImage(buff, 0, 0, 350, 250 , 0, 0, buff.getWidth(), buff.getHeight(), null);
            }catch(IOException e){}
        }
        
        if(arg0.getSource()==btnAnnuler){
            image = new JLabel(new ImageIcon("img/blank.jpg"));
                image.setPreferredSize(new Dimension(350, 250));
                image.setBackground(new Color(240, 240, 240));
                Graphics g = panel_2.getGraphics();
                Image image5 = Toolkit.getDefaultToolkit().getImage("img/blank.jpg");
                g.drawImage(image5, 0, 0, null);
                panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
                lblNbvis.setText("   -");
                lblNbyeux.setText("   -");
                lblDetect_1.setText("   -");
                if(JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter l'application ?","Quitter",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
        }
        
        if(arg0.getSource()==mntmVisage){
             picture = Imgcodecs.imread(chemin_image);
            dtecteurvisage.detectMultiScale(picture, visages);
            for (Rect rect : visages.toArray()) {
                Imgproc.rectangle(picture, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(245, 169, 188), 2*(h/250));
            }
            Graphics g = panel_2.getGraphics();
            BufferedImage buff;
            try{
            MatOfByte mem = new MatOfByte();
            Imgcodecs.imencode(".jpg", picture, mem);
            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
            buff = (BufferedImage) im;
            g.drawImage(buff, 0, 0, 350, 250 , 0, 0, buff.getWidth(), buff.getHeight(), null);
            g.drawRect(0, 0, 348, 248);
            }catch(IOException e){}
         }
                
	 if(arg0.getSource()==mntmYeux){
             for (Rect rect : visages.toArray()) {
                Mat dest=picture.submat(new Rect(rect.x,rect.y,rect.width,rect.height));
                dtecteuryeux.detectMultiScale(dest, yeux);
                for(Rect r : yeux.toArray()){
                    
                    Imgproc.rectangle(dest, new Point(r.x,r.y), new Point(r.x+r.width,r.y+r.height), new Scalar(0, 255, 127), 2*(h/250));

                }
                lblNbyeux.setText(""+yeux.toArray().length);
            }
            Graphics g = panel_2.getGraphics();
            BufferedImage buff;
            try{
            MatOfByte mem = new MatOfByte();
            Imgcodecs.imencode(".jpg", picture, mem);
            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
            buff = (BufferedImage) im;
             g.drawImage(buff, 0, 0, 350, 250 , 0, 0, buff.getWidth(), buff.getHeight(), null);
            }catch(IOException e){}
         }
         
         if(arg0.getSource()==mntmEnregistrer){
             File rep = new File ("imageOutput");
            if(rep.isDirectory()){
                File [] fileList = rep.listFiles();
                for(int i = 0;i<fileList.length;i++){
                    fileList[i].delete();
                }
            }
            int i=0;
            Mat dest = null;
            for (Rect rect : visages.toArray()) {
                chemin_dest="imageOutput/visage"+i+".jpg";
                Imgproc.rectangle(picture, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height), new Scalar(245, 169, 188), 2*(h/250));
                dest=picture.submat(new Rect(rect.x,rect.y,rect.width,rect.height));
                Imgcodecs.imwrite(chemin_dest, dest);
                i++;
            }
            Graphics g = panel_3.getGraphics();
            BufferedImage buff;
            try{
            MatOfByte mem = new MatOfByte();
            Imgcodecs.imencode(".jpg", dest, mem);
            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
            buff = (BufferedImage) im;
             g.drawImage(buff, 0, 0, 125, 125 , 0, 0, buff.getWidth(), buff.getHeight(), null);
             g.drawRect(0, 0, 124, 124);
            }catch(IOException e){}
            iter=0;
            if(visages.toArray().length>1){
            btnenrgyeux.setEnabled(true);
            btnnext.setEnabled(true);
            }
            lblNbvis.setText((iter+1)+"/"+visages.toArray().length);
         }
                
	 if(arg0.getSource()==mntmRechercher){
             
         }
        
         if(arg0.getSource()==menuitemOuvrir){
            JFileChooser chooser = new JFileChooser(); 
            chooser.setCurrentDirectory(new java.io.File("./imagesvisages"));
            chooser.setDialogTitle(null);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setAcceptAllFileFilterUsed(false);
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
                chemin_image=chooser.getSelectedFile().getPath();
                image = new JLabel(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));
                Graphics g = panel_2.getGraphics();
                Image image5 = Toolkit.getDefaultToolkit().getImage(chooser.getSelectedFile().getAbsolutePath());
                h=image5.getHeight(null);
                g.drawImage(image5, 0, 0, 350, 250 , 0, 0, image5.getWidth(null), image5.getHeight(null), null);
                g.drawRect(0, 0, 348, 248);
                g = panel_3.getGraphics();
                image5 = Toolkit.getDefaultToolkit().getImage("img/blank.jpg");
                g.drawImage(image5, 0, 0, null);
                panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
                btnnext.setEnabled(false);
                btnprev.setEnabled(false);
                btnenrgyeux.setEnabled(false);
            }
         }
                
	 if(arg0.getSource()==mntmEffacer){
             visages = new MatOfRect();
            image = new JLabel(new ImageIcon("img/blank.jpg"));
            image.setPreferredSize(new Dimension(350, 250));
            image.setBackground(new Color(240, 240, 240));
            Graphics g = panel_2.getGraphics();
            Image image5 = Toolkit.getDefaultToolkit().getImage("img/blank.jpg");
            g.drawImage(image5, 0, 0, null);
            g = panel_3.getGraphics();
            image5 = Toolkit.getDefaultToolkit().getImage("img/blank.jpg");
            g.drawImage(image5, 0, 0, null);
            panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            chemin_image=null;
            lblNbvis.setText("   -");
            lblNbyeux.setText("   -");
            lblDetect_1.setText("   -");
            btnnext.setEnabled(false);
            btnprev.setEnabled(false);
            btnenrgyeux.setEnabled(false);
         }
        
        if(arg0.getSource()==mntmQuitter){
                image = new JLabel(new ImageIcon("img/blank.jpg"));
                image.setPreferredSize(new Dimension(350, 250));
                image.setBackground(new Color(240, 240, 240));
                Graphics g = panel_2.getGraphics();
                Image image5 = Toolkit.getDefaultToolkit().getImage("img/blank.jpg");
                g.drawImage(image5, 0, 0, null);
                panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
                lblNbvis.setText("   -");
                lblNbyeux.setText("   -");
                lblDetect_1.setText("   -");
                if(JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter l'application ?","Quitter",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
	}
        
        }
        
	public static void main(String[] args) {
		// TODO Auto-generated method stub
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new Interface();
	}   
}