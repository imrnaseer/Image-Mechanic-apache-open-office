package uoj.csc.rad.gp1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;
import static uoj.csc.rad.gp1.imgafuncs.img2gry;
import static uoj.csc.rad.gp1.imgofuncs.fllw;
import static uoj.csc.rad.gp1.imgofuncs.fndgrds;

/**
 *
 * @author Mohammad NaseeR
 */
public class imgefltrs 
{
public static BufferedImage efiltrs(BufferedImage im,String s,Color ec,Color bc)
{
int w=im.getWidth();int h=im.getHeight();
float[] nc=ec.getRGBComponents(new float[4]);
int[]c1={(int)nc[0]*255,(int)nc[1]*255,(int)nc[2]*255,(int)nc[3]*255};
    nc=bc.getRGBComponents(new float[4]);
int[]c2={(int)nc[0]*255,(int)nc[1]*255,(int)nc[2]*255,(int)nc[3]*255};
BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
WritableRaster r1=img2gry(im).getRaster();
WritableRaster r2=fimg.getRaster();
if(s.equals("prewitt")==true)
{
    int [][]Hx={{-1,0,1},{-1,0,1},{-1,0,1}};
    int [][]Hy={{-1,-1,-1},{0,0,0},{1,1,1}};
      for(int x=1;x<h-1;x++)
      {
      for(int y=1;y<w-1;y++)
      {
        double Dx=0.0;double Dy=0.0;
        for(int i=-1;i<=1;i++)
        {
           for(int j=-1;j<=1;j++)
            {
            int[] pi=r1.getPixel(y+j,x+i,new int[4]);
            if(pi[3]!=0)
            {
              Dx +=(pi[0]*Hx[i+1][j+1]);
              Dy +=(pi[0]*Hy[i+1][j+1]);
            }}
        }
       double d=Math.round(Math.sqrt(Dx*Dx + Dy*Dy));
        if(d>255)
        {r2.setPixel(y,x,c1);}
        else
        { r2.setPixel(y,x,c2);}
        }
        } 
        fimg.setData(r2);
        return fimg;
} 
else if(s.equals("sobel")==true)
{
    int [][]Hx={{-1,0,1},{-2,0,2},{-1,0,1}};
    int [][]Hy={{-1,-2,-1},{0,0,0},{1,2,1}};
      for(int x=1;x<h-1;x++)
      {
      for(int y=1;y<w-1;y++)
      {
        double Dx=0.0;double Dy=0.0;
        for(int i=-1;i<=1;i++)
        {
           for(int j=-1;j<=1;j++)
            {
            int[] pi=r1.getPixel(y+j,x+i,new int[4]);
            if(pi[3]!=0)
            {
              Dx +=(pi[0]*Hx[i+1][j+1]);
              Dy +=(pi[0]*Hy[i+1][j+1]);
            }}
        }
       double d=Math.round(Math.sqrt(Dx*Dx + Dy*Dy));
        if(d>255)
        {r2.setPixel(y,x,c1);}
        else
        { r2.setPixel(y,x,c2);}
        }
        } 
        fimg.setData(r2);
        return fimg;
} 
else if(s.equals("robert")==true)
{
      for(int x=1;x<h-1;x++)
      {
      for(int y=1;y<w-1;y++)
      {
        double Dx =Math.abs(r1.getPixel(y-1,x-1,new int[4])[0]-r1.getPixel(y,x,new int[4])[0]);
        double Dy =Math.abs(r1.getPixel(y,x-1,new int[4])[0]-r1.getPixel(y-1,x,new int[4])[0]);
        if((Dx+Dy)>255)
        {r2.setPixel(y,x,c1);}
        else
        { r2.setPixel(y,x,c2);}
        }} 
        fimg.setData(r2);
        return fimg;
}
fimg.setData(r2);
return fimg;
}
public static BufferedImage cnyefiltr(BufferedImage img,float lt,float ht,float gkr,int gkw,boolean cn) 
{
int h,w,ps;int[] data = null,mgt = null;
    float[] xConv = null,yConv = null,xgrd = null,ygrd = null;
    float gco = 0.005f;
    float mgs = 100F;
    float mgl = 1000F;
    int mgm = (int) (mgs* mgl);
    w = img.getWidth();
    h = img.getHeight();
    ps = w * h;
    if (data == null || ps != data.length) 
    {
    data = new int[ps];
    mgt = new int[ps];
    xConv = new float[ps];
    yConv = new float[ps];
    xgrd = new float[ps];
    ygrd = new float[ps];
    }
    int type = img.getType();
    switch (type) {
        case BufferedImage.TYPE_INT_RGB:
        case BufferedImage.TYPE_INT_ARGB:
         {
            int[] pixels = (int[]) img.getData().getDataElements(0, 0, w, h, null);
            for (int i = 0; i < ps; i++) {
            int p = pixels[i];
            int r = (p & 0xff0000) >> 16;
            int g = (p & 0xff00) >> 8;
            int b = p & 0xff;
            data[i] = Math.round(0.299f * r + 0.587f * g + 0.114f * b);
            }
            break;
            }
            case BufferedImage.TYPE_BYTE_GRAY:
            {
            byte[] pixels = (byte[]) img.getData().getDataElements(0, 0, w, h, null);
            for (int i = 0; i < ps; i++) {
            data[i] = (pixels[i] & 0xff);
            }
            break;
            }
            case BufferedImage.TYPE_USHORT_GRAY:
            {
            short[] pixels = (short[]) img.getData().getDataElements(0, 0, w, h, null);
            for (int i = 0; i < ps; i++) {
            data[i] = (pixels[i] & 0xffff) / 256;
            }
            break;
            }
            case BufferedImage.TYPE_3BYTE_BGR:
            {
            byte[] pixels = (byte[]) img.getData().getDataElements(0, 0, w, h, null);
            int offset = 0;
            for (int i = 0; i < ps; i++) {
            int b = pixels[offset++] & 0xff;
            int g = pixels[offset++] & 0xff;
            int r = pixels[offset++] & 0xff;
            data[i] = Math.round(0.299f * r + 0.587f * g + 0.114f * b);
            }break;}
            default:
                throw new IllegalArgumentException("Unsupported image type: " + type);
            }
        if (cn)
        {
        int[] histogram = new int[256];
	for (int i = 0; i < data.length; i++) 
        {histogram[data[i]]++;}
	int[] remap = new int[256];
	int sum = 0;
	int j = 0;
	for (int i = 0; i < histogram.length; i++) 
        {
	sum += histogram[i];
	int target = sum*255/ps;
	for (int k = j+1; k <=target; k++) 
        {remap[k] = i;}
	j = target;}
	for (int i = 0; i < data.length; i++)
        {data[i] = remap[data[i]];}}
        fndgrds(gkr, gkw,h,w,xConv,yConv,xgrd,ygrd,data,mgt,gco,mgs,mgl,mgm);
	int low = Math.round(lt * mgs);
	int high = Math.round( ht * mgs);
        Arrays.fill(data, 0);
	int offset = 0;
	for (int y = 0; y < h; y++) 
        {
	for (int x = 0; x < w; x++)
        {
	if (data[offset] == 0 && mgt[offset] >= high)
        {fllw(x, y, offset, low,w,h,data,mgt);}
	offset++;}}
        for (int i = 0; i < ps; i++) 
        {data[i] = data[i] > 0 ? -1 : 0xff000000;} 
        BufferedImage edgesImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	edgesImage.getWritableTile(0, 0).setDataElements(0, 0, w, h, data);
        return edgesImage; 

}

}
