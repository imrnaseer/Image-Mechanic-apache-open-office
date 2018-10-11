
package uoj.csc.rad.gp1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RasterFormatException;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import static uoj.csc.rad.gp1.imgafuncs.imgscale;
import static uoj.csc.rad.gp1.imgofuncs.max;

/**
 *
 * @author Mohammad NaseeR
 */
public class imgbfuncs {
    public static BufferedImage tobufferimage(Image img)
    {
    if(img instanceof BufferedImage)
    { return (BufferedImage) img; }
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();
    return bimage;
    }
    public static int[][][] pixary(BufferedImage img)
    {
    int w=img.getWidth();int h=img.getHeight();
     WritableRaster wi=img.getRaster();
    int[][][] pt=new int[1][3][w*h];
    int i=0;
    for(int y = 0; y < h; y++) 
    {
    for(int x = 0; x < w; x++) 
    {
       pt[0][0][i] = wi.getPixel(x, y, new int[4])[0];
       pt[0][1][i] = wi.getPixel(x, y, new int[4])[1];
       pt[0][2][i] = wi.getPixel(x, y, new int[4])[2];i++;
    }
    }
    return pt;
    }
    public static double[][] gipixary(BufferedImage img)
    {
    int w=img.getWidth();int h=img.getHeight();
     WritableRaster wi=img.getRaster();
    double[][] pt=new double[h][w];
    int i=0;
    for(int y = 0; y < h; y++) 
    {
    for(int x = 0; x < w; x++) 
    {int[] pi=wi.getPixel( y,x, new int[4]);
       pt[y][x] = (pi[0]+pi[1]+pi[2])/3;
    }
    }
    return pt;
    }
      public static int clamping(double val)
      {
      if (val > 255)
      {return 255;}
      else if (val < 0)
      {return 0;}
      else
      {return (int)val;}
      }
    public static BufferedImage imgcrp(BufferedImage img,rsbox rsizer,JLabel inf)
    {
    try
    {
    int iw=img.getWidth();int ih=img.getHeight();
    int xg=(inf.getWidth()-iw)/2-5;
    int yg=(inf.getHeight()-ih)/2-5;
    if(xg<0){xg=0;}
    if(yg<0){yg=0;}
    int w = rsizer.getWidth();
    int h = rsizer.getHeight();
    int x = rsizer.getX()-xg;
    int y = rsizer.getY()-yg;
    if(x+w+5>iw)
    {x=x-5;}
    if(y+h+5>ih)
    {y=y-5;}
    return img.getSubimage(x, y, w, h);
    }
    catch (RasterFormatException rfe)
     {return null;}
    }
    public static double imganlzr(BufferedImage img,rsbox rsizer,JLabel inf,int idx)
    {
    try
    {
    double avrgpixels=0;
    int iw=img.getWidth();int ih=img.getHeight();
    int xg=((inf.getWidth()-iw)/2)-5;
    int yg=((inf.getHeight()-ih)/2)-5;
    if(xg<0){xg=0;}
    if(yg<0){yg=0;}
    double w = rsizer.getWidth();
    double h = rsizer.getHeight();
    int x = rsizer.getX()-xg;
    int y = rsizer.getY()-yg;
    if(x+w+5>iw)
    {x-=5;}
    if(y+h+5>ih)
    {y-=5;}
    double aw=x+w;
    double ah=y+h;
    switch(idx){
        case 0:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();//int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=R;
            }}
            break;
        case 1:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
          //  int R=c.getRed();int B=c.getBlue();
            int G=c.getGreen();
            avrgpixels+=G;
            }}
            break;
        case 2:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
          //  int R=c.getRed();int G=c.getGreen();
            int B=c.getBlue();
            avrgpixels+=B;
            }}
            break;
        case 3:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=(0.2126*R+0.7152*G+0.0722*B);
            }}
            break;
        case 4:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=((R-B)/(R+B));
            }}
            break;
        case 5:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=(2*G*(R-B)/(R+B));
            }}
            break;
        case 6:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=(2*R*(G-B)/(G+B));
            }}
            break;
        case 7:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=(2*B/(R+G+B));
            }}
            break;
        case 8:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=((R+G)/B);
            }}
            break;
        case 9:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=((R+2*G)/B);
            }}
            break;
        case 10:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=((R-B)/G);
            }}
            break;
        case 11:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=(R-B)/(R+G+B);
            }}
            break;
        case 12:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            avrgpixels+=((G-(R/3)-(B/3))/255);
            }}
            break;
        case 13:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            double tmp=(G-(R/3)-(B/3))/255;
            avrgpixels+=(1/(1+Math.pow(Math.E, -tmp)));
            }}
            break;
        case 14:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
            double tmp=(R+G+B)/(R+G);
            avrgpixels+=tmp;//clamping();
            }}
            break;
        case 15:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
          //  double tmp=(G-(R/3)-(B/3))/255;
            avrgpixels+=(R+G);//clamping();
            }}
            break;
            


        case 16:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
          //  double tmp=(G-(R/3)-(B/3))/255;
            avrgpixels+=(R+G+B);//clamping();
            }}
            break;
        case 17:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
          //  double tmp=(G-(R/3)-(B/3))/255;
            avrgpixels+=((R+G+B)/3);//clamping();
            }}
            break;
        case 18:
            for(int j=y;j<ah;j++){
            for(int i=x;i<aw;i++){
            Color c=new Color(img.getRGB(i, j));
            int R=c.getRed();int G=c.getGreen();int B=c.getBlue();
          //  double tmp=(G-(R/3)-(B/3))/255;
            avrgpixels+=((R+G+2*B)/4);//clamping();
            }}
            break;

        default:
            break;
    }
    return avrgpixels/(w*h);
    }
    catch (Exception e)
     {System.out.println(e);return 0;}
    }
 
     public static int[] piamaxmin(BufferedImage img)
    {
    int w=img.getWidth();int h=img.getHeight();
     WritableRaster r1=img.getRaster();
      int mr=0;int mg=0;int mb=0;
      int mir=255;int mig=255;int mib=255;
    for (int y = 0; y < h; y++)
    {
    for (int x = 0; x < w; x++) 
    {
      int[] pi = r1.getPixel(x, y, new int[4]);
      if(pi[0]!=255 && pi[1]!=255 && pi[2]!=255)
      {
      if(pi[0]>mr){ mr=pi[0];}
       if(pi[1]>mg){ mg=pi[1];}
       if(pi[2]>mb){ mb=pi[2];}
      }
      if(pi[0]!=0 && pi[1]!=0 && pi[2]!=0)
      {
      if(pi[0]<mir){ mir=pi[0];}
       if(pi[1]<mig){ mig=pi[1];}
       if(pi[2]<mib){ mib=pi[2];}
      }}}
    int [] o={mr,mg,mb,mir,mig,mib}; 
    return o;
    }
  
    public static BufferedImage imgtxt(BufferedImage img,rsbox rsizer,
            JLabel inf,Font fnt,Color fc,String txt)
    {
    int iw=img.getWidth();int ih=img.getHeight();
    int xg=(inf.getWidth()-iw)/2-5;
    int yg=(inf.getHeight()-ih)/2-5;
    if(xg<0){xg=0;}
    if(yg<0){yg=0;}
    int w = rsizer.getWidth();
    int h = rsizer.getHeight();
    int x = rsizer.getX()-xg;
    int y = rsizer.getY()-yg;
    if(x+w+5>iw)
    {x=x-5;}
    if(y+h+5>ih)
    {y=y-5;}
    y=y+h/2+2;
       BufferedImage fimg = imgscale(img,100);
        Graphics2D g = fimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(fc);
        g.setFont(fnt);
        g.drawString(txt, x,y); 
        g.dispose();
    return fimg;
    }
    public static BufferedImage imglbl(BufferedImage bimg,BufferedImage fimg,rsbox rsizer,JLabel inf)
    {
     int iw=bimg.getWidth();int ih=bimg.getHeight();
    int xg=(inf.getWidth()-iw)/2-5;
    int yg=(inf.getHeight()-ih)/2-5;
    if(xg<0){xg=0;}
    if(yg<0){yg=0;}
    int w = rsizer.getWidth();
    int h = rsizer.getHeight();
    int x = rsizer.getX()-xg;
    int y = rsizer.getY()-yg;
    if(x+w+5>iw)
    {x=x-5;}
    if(y+h+5>ih)
    {y=y-5;}
    y=y+2;
       BufferedImage img = imgscale(bimg,100);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImageOp op = null;
       g.drawImage(fimg, op, x, y);
        g.dispose();
    return img;
    }
    
    public static BufferedImage plot(int[][]ary,int ww,int wh,int bg,int gpw,
         int yhc,Stroke ls,Color pc,Color lc1,Color lc2,Color lc3)
   {
     BufferedImage fimg = new BufferedImage(ww, wh, BufferedImage.TRANSLUCENT);
     Graphics2D g = fimg.createGraphics();
     g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     int[] m={max(ary[0]),max(ary[1]),max(ary[2])};
     int mv=max(m);
      double xScale = ((double) ww - 2 * bg) / (256 - 1);
      double yScale = ((double) wh - 2 * bg) / (mv - 1);
      
        List<Point> gp = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
         int x1 = (int) (i * xScale + bg);
         int y1 = (int) (((m[0] - ary[0][i])*yScale + bg));
         gp.add(new Point(x1, y1));
        }
       g.setColor(new Color(0,0,0,255));
       // create x and y axes 
      g.drawLine(bg, wh - bg, bg, bg);
      g.drawLine(bg, wh - bg, ww - bg, wh - bg);
      
         // create hatch marks for y axis. 
        for (int i = 1; i <= yhc; i++)
        {
         int x0 = bg;
         int x1 = gpw + bg;
         int y0 = wh - (((i) * (wh - bg * 2)) / yhc+ bg);
         int y1 = y0;
         g.drawLine(x0, y0, x1, y1);
         g.drawString(String.valueOf(i*mv/yhc), x0-40, y0+5); 
        }
        
          // and for x axis
        for (int i = 0; i <= 8; i++) 
        {
         int x0 = i * (ww - bg * 2) / 8 + bg;
         int x1 = x0;
         int y0 = wh - bg;
         int y1 = y0 - gpw;
         g.drawLine(x0, y0, x1, y1);
         g.drawString(String.valueOf(i*255/8), x1-5, y1+20);    
        }
      Stroke oldStroke = g.getStroke();
      
    if(ary[1][0]==-1 && ary[2][0]==-1)
    {
        g.setStroke(ls);
        g.setColor(lc1);
      for (int i = 0; i < gp.size() - 1; i++) {
         int x1 = gp.get(i).x;
         int y1 = gp.get(i).y;
         int x2 = gp.get(i + 1).x;
         int y2 = gp.get(i + 1).y;
         g.drawLine(x1, y1, x2, y2);         
      }
      g.setStroke(oldStroke);      
      g.setColor(pc);
      for (int i = 0; i < gp.size(); i++) 
      {
         int x = gp.get(i).x - gpw / 2;
         int y = gp.get(i).y - gpw / 2;
         g.fillOval(x, y, gpw, gpw);
      }
      
    }
    else
    {
       List<Point> ggp = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
         int x1 = (int) (i * xScale + bg);
         int y1 = (int) (((m[1] - ary[1][i])*yScale + bg));
         ggp.add(new Point(x1, y1));
        }
      List<Point> bgp = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
         int x1 = (int) (i * xScale + bg);
         int y1 = (int) (((m[2] - ary[2][i])*yScale + bg));
         bgp.add(new Point(x1, y1));
        }
        
      g.setStroke(ls);
      g.setColor(lc1);
      for (int i = 0; i < gp.size() - 1; i++) {
         int x1 = gp.get(i).x;
         int y1 = gp.get(i).y;
         int x2 = gp.get(i + 1).x;
         int y2 = gp.get(i + 1).y;
         g.drawLine(x1, y1, x2, y2);         
      }
      
      g.setColor(lc2);
        for (int i = 0; i < ggp.size() - 1; i++) {
         int x1 = ggp.get(i).x;
         int y1 = ggp.get(i).y;
         int x2 = ggp.get(i + 1).x;
         int y2 = ggp.get(i + 1).y;
         g.drawLine(x1, y1, x2, y2);         
      }
       g.setColor(lc3);
        for (int i = 0; i < bgp.size() - 1; i++) {
         int x1 = bgp.get(i).x;
         int y1 = bgp.get(i).y;
         int x2 = bgp.get(i + 1).x;
         int y2 = bgp.get(i + 1).y;
         g.drawLine(x1, y1, x2, y2);         
      }
        
      g.setStroke(oldStroke);      
      g.setColor(pc);
      for (int i = 0; i < gp.size(); i++) 
      {
         int x = gp.get(i).x - gpw / 2;
         int y = gp.get(i).y - gpw / 2;
         g.fillOval(x, y, gpw, gpw);
      }
      for (int i = 0; i < ggp.size(); i++) 
      {
         int x = ggp.get(i).x - gpw / 2;
         int y = ggp.get(i).y - gpw / 2;
         g.fillOval(x, y, gpw, gpw);
      }
      for (int i = 0; i < bgp.size(); i++) 
      {
         int x = bgp.get(i).x - gpw / 2;
         int y = bgp.get(i).y - gpw / 2;
         g.fillOval(x, y, gpw, gpw);
      }
    }
      g.dispose();
      return fimg;
   }
    
   public static int[][] imghist(BufferedImage img)
    {
        int w = img.getWidth();
       int h = img.getHeight();
       WritableRaster r1=img.getRaster();
      int [][] ary=new int[3][256];
       if(imggc(img,r1,w,h)==true)
       {
        for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
        ary[0][pi[0]]=ary[0][pi[0]]+1;
        }}
        ary[1][0]=-1;ary[2][0]=-1;
       }
        else
       {
       for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
        ary[0][pi[0]]=ary[0][pi[0]]+1;
        ary[1][pi[1]]=ary[1][pi[1]]+1;
        ary[2][pi[2]]=ary[2][pi[2]]+1;
        }}
       }
        return ary;
   }
   public static boolean imggc(BufferedImage img,WritableRaster r1,int w,int h)
   {
       boolean c=true;
       for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        { 
        int[] p=r1.getPixel(x,y, new int[4]);
        if((p[3]!=0 && (p[0]!=p[1] || p[1]!=p[2] || p[0]!=p[2])))
        { c=false; }
        }
        }
   return c;
   }
   
}
