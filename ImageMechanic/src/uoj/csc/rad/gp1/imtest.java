
package uoj.csc.rad.gp1;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static uoj.csc.rad.gp1.imgbfuncs.imggc;

/**
 *
 * @author Mohammad NaseeR
 */
public class imtest 
{
/*  public static void main(String []args) throws IOException
  {
    BufferedImage img=ImageIO.read(new File("e://cameraman.gif"));
    int h = img.getHeight();
    int w = img.getWidth();
    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    WritableRaster r1=img.getRaster();
    WritableRaster r2=bi.getRaster();
      if(imggc(img,r1,w,h)==true)
        {
             for (int y = 0; y < h; y++)
        {
        for (int x = 0; x < w; x++) 
        {     
        int[]  pi=r1.getPixel(x, y, new int[4]);
      //  if(pi[3]!=0)
        {
            r2.setPixel(x, y,new int[]{pi[0]-20,pi[0]-20,pi[0]-20,255});
        }
        }}
        bi.setData(r2);
        File f=new File("e://2.png");
        ImageIO.write(bi, "PNG", f);
       // return bi; 
        }
      else
      {
     //   return img;
      }
  }*/
}
