
package uoj.csc.rad.gp1;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import static uoj.csc.rad.gp1.imgbfuncs.*;

/**
 *
 * @author Mohammad NaseeR
 */

public class imgafuncs 
{ 
    public static GraphicsConfiguration gcp()
    {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    return ge.getDefaultScreenDevice().getDefaultConfiguration();
    }
 public static BufferedImage rotate(BufferedImage img,int ang) throws IOException
   {
    double rv=ang*Math.PI/180;
    double cos = Math.abs(Math.cos(rv));
    double sin = Math.abs(Math.sin(rv));
    int h = img.getHeight();
    int w = img.getWidth();
    int nh =(int)Math.floor(h*cos + w*sin);
    int nw = (int)Math.floor(w*cos + h*sin);
    BufferedImage bi = new BufferedImage(nw, nh, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = bi.createGraphics();
    g.translate((nw - w) / 2, (nh - h) / 2);
    g.rotate(rv, w / 2, h / 2);
    g.drawRenderedImage(img, null);
    g.dispose();
    return bi;
   }
   

   public static BufferedImage flip(BufferedImage img, String str)
   {
     if(str.equals("vertical")) 
     {
      AffineTransform tx = AffineTransform.getScaleInstance(1.0D, -1.0D);
      tx.translate(0.0D, -img.getHeight(null));
      AffineTransformOp op = new AffineTransformOp(tx, 1);
      return op.filter(img, null);
     }
     else
     {
     AffineTransform tx = AffineTransform.getScaleInstance(-1.0D, 1.0D);
     tx.translate(-img.getWidth(null), 0.0D);
     AffineTransformOp op = new AffineTransformOp(tx, 1);
     return op.filter(img, null);
     }
   }
public static BufferedImage imzoom(BufferedImage img,int zl) 
{
int nw = img.getWidth()*zl/100;
int nh = img.getHeight()*zl/100;
BufferedImage resizedImage = new BufferedImage(nw , nh, BufferedImage.TYPE_INT_ARGB);
Graphics2D g = resizedImage.createGraphics();
g.drawImage(img, 0, 0, nw , nh , null);
g.dispose();
return resizedImage;
}
   
   public static BufferedImage img2gry(BufferedImage img)
   {
        int w = img.getWidth();
       int h = img.getHeight();
      BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
       WritableRaster r1=img.getRaster();
       WritableRaster r2=fimg.getRaster();
      if(imggc(img,r1,w,h)==true)
        {
           return img;
        }
        else
        {
         for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
        if(pi[3]!=0)
        {
            int cv=clamping(pi[0]*0.2126+pi[1]*0.7152+pi[2]*0.0722);
            r2.setPixel(x, y,new int[]{cv,cv,cv,255});
        }}}
        fimg.setData(r2);
        return fimg;
        }
     
   }
   
   public static BufferedImage imgbw(BufferedImage img, int tv)
   {  
        int w=img.getWidth();
        int h=img.getHeight();
        int[]bc={0,0,0,255};
        int[]wc={255,255,255,255};
        BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        WritableRaster r1=img2gry(img).getRaster();
        WritableRaster r2=fimg.getRaster();
        for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
       if(pi[3]!=0)
        {
        if(pi[0] < tv )
        {r2.setPixel(x, y, bc); }
        else
        { r2.setPixel(x, y, wc); }
        }}}
    fimg.setData(r2);
    return fimg;
   }
   public static BufferedImage imgcntrst(BufferedImage img, int val)
   {
     double sf=(259*(val+255))/(255*(259 -val));
        final int w = img.getWidth();
      final int h = img.getHeight();
      BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
       WritableRaster r1=img.getRaster();
       WritableRaster r2=fimg.getRaster();
       if(imggc(img,r1,w,h)==true)
       {
       for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
         if(pi[3]!=0)
        {
        int cv=clamping(sf*(pi[0]-128)+128);
            r2.setPixel(x, y,new int[]{cv,cv,cv,255});
        }
        }}
       }
       else
        { 
         for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
        if(pi[3]!=0)
        {
            r2.setPixel(x, y,new int[]{
                clamping(sf*(pi[0]-128)+128),clamping(sf*(pi[1]-128)+128),
                clamping(sf*(pi[2]-128)+128),255});
        }}}
        }
        fimg.setData(r2);
        return fimg;
   }
   public static BufferedImage imgacntrst(BufferedImage img)
   {
       int w = img.getWidth();
       int h = img.getHeight();
      BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
       WritableRaster r1=img.getRaster();
       WritableRaster r2=fimg.getRaster();
        int [] mpi=piamaxmin(img);
       if(imggc(img,r1,w,h)==true)
       {
       for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        { 
        int[]  pi=r1.getPixel(x, y, new int[4]);
        if(pi[3]!=0)
        {
        int cv=clamping((pi[0]-mpi[3])*(255/(mpi[0]-mpi[3])));
            r2.setPixel(x, y,new int[]{cv,cv,cv,255});
        }
        }}
       }
        else
        { 
         for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
        if(pi[3]!=0)
        {
            r2.setPixel(x, y,new int[]{
                clamping((pi[0]-mpi[3])*(255/(mpi[0]-mpi[3]))),
            clamping((pi[1]-mpi[4])*(255/(mpi[1]-mpi[4]))),
            clamping((pi[2]-mpi[5])*(255/(mpi[2]-mpi[5]))),255}
                    );
        }}}
        }
        fimg.setData(r2);
        return fimg;
   }
      public static BufferedImage imgscale(BufferedImage img,int v)
      {int w=img.getWidth();
      int h=img.getHeight();
      int nw=w-(w*(100-v)/100);
      int nh=h-(h*(100-v)/100);
      return tobufferimage(img.getScaledInstance(nw, nh, Image.SCALE_FAST));
      }
    public static BufferedImage imgivrs(BufferedImage img)
    {
        final int w = img.getWidth();
      final int h = img.getHeight();
      BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
       WritableRaster r1=img.getRaster();
       WritableRaster r2=fimg.getRaster();
       if(imggc(img,r1,w,h)==true)
       {
        for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
          if(pi[3]!=0)
          {
            r2.setPixel(x, y,new int[]{255-pi[0],255-pi[0],255-pi[0],255});
          }
        }}
       }
       else
        { 
        for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
        if(pi[3]!=0)
        {
            r2.setPixel(x, y,new int[]{255-pi[0],255-pi[1],255-pi[2],255});
        }}}
        }
        fimg.setData(r2);
        return fimg;
    }
    public static BufferedImage imgtnprt(BufferedImage img,int val)
    {
      final int w = img.getWidth();
      final int h = img.getHeight();
      BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
      WritableRaster r1=img.getRaster();
      WritableRaster r2=fimg.getRaster();
        for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
     //   if(pi[3]!=0)
        {
            r2.setPixel(x,y,new int[]{pi[0],pi[1],pi[2],val});
        }}}    
        fimg.setData(r2);
        return fimg;
    }
    
    public static double[][] convtn(BufferedImage img,double[][]H)
    {
    int w=img.getWidth();int h=img.getHeight();
    BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    WritableRaster r1=img.getRaster();
    double[][] o=gipixary(img);
    for(int x=2;x<h-2;x++)
    {
    for(int y=2;y<w-2;y++)
    {
    int sum=0;
        for(int i=-2;i<=2;i++)
        {
            for(int j=-2;j<=2;j++)
            {
             int[]  pi=r1.getPixel(y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                sum +=(pi[0]*H[i+2][j+2]);
            }}}
        o[x][y]=sum/25;
    } }
        return o;
    }
    
    public static BufferedImage imgbrtns(BufferedImage img, int val)
    {
        final int w = img.getWidth();
      final int h = img.getHeight();
      BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
       WritableRaster r1=img.getRaster();
       WritableRaster r2=fimg.getRaster();
       if(imggc(img,r1,w,h)==true)
       {
       for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
        if(pi[3]!=0)
        {
        int cv=clamping(pi[0]+val);
            r2.setPixel(x, y,new int[]{cv,cv,cv,255});
        }}}
       }
       else
       {
        for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
        if(pi[3]!=0)
        {
            r2.setPixel(x, y,new int[]{
            clamping(pi[0]+val),clamping(pi[1]+val),clamping(pi[2]+val),255});
        }}}
       }
    
        fimg.setData(r2);
        return fimg;
   }
     public static BufferedImage imgrgbajst(BufferedImage img, int rc,int gc,int bc)
    {
        final int w = img.getWidth();
      final int h = img.getHeight();
      BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
       WritableRaster r1=img.getRaster();
       WritableRaster r2=fimg.getRaster();
        { 
         for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
        if(pi[3]!=0)
        {
            r2.setPixel(x, y,new int[]{
                clamping(pi[0]+rc),clamping(pi[1]+gc),clamping(pi[2]+bc),255});
        }}}
        }
        fimg.setData(r2);
        return fimg;
   }
   
   }
  
