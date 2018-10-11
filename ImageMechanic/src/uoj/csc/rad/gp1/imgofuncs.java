
package uoj.csc.rad.gp1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import javax.swing.JColorChooser;

/**
 *
 * @author Mohammad NaseeR
 */
public class imgofuncs {
    
    public static Font[] gtfnt()
    {
    return GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
    }
     public static String[] gtfntns()
    {
    return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }
    public static Color sc(Component cmp,Color c0)
    {
        Color c = JColorChooser.showDialog(cmp, "I Color Picker", c0);
        if (c != null)
        { return c;}
        return c0;
    }
    public static void fndgrds(float kernelRadius, int kernelWidth,int h,
        int w,float[] xConv,float[] yConv,float[] xgrd,float[]ygrd,int[] data,
        int[] mgt,float gco,float mgs,float mgl,int mgm) 
{
float kernel[] = new float[kernelWidth];
float diffKernel[] = new float[kernelWidth];
int kw;
for (kw = 0; kw < kernelWidth; kw++) 
{
float g1 = gaussian(kw, kernelRadius);
if (g1 <= gco && kw >= 2) break;
float g2 = gaussian(kw - 0.5f, kernelRadius);
float g3 = gaussian(kw + 0.5f, kernelRadius);
kernel[kw] = (g1 + g2 + g3) / 3f / (2f * (float) Math.PI * kernelRadius * kernelRadius);
diffKernel[kw] = g3 - g2;
}
int initX = kw - 1;
int maxX = w - (kw - 1);
int initY = w * (kw - 1);
int maxY = w * (h - (kw - 1));
for (int x = initX; x < maxX; x++) 
{
for (int y = initY; y < maxY; y += w)
{
int index = x + y;
float sumX = data[index] * kernel[0];
float sumY = sumX;
int xOffset = 1;
int yOffset = w;
for(; xOffset < kw ;) 
{
sumY += kernel[xOffset] * (data[index - yOffset] + data[index + yOffset]);
sumX += kernel[xOffset] * (data[index - xOffset] + data[index + xOffset]);
yOffset += w;
xOffset++;
}
yConv[index] = sumY;
xConv[index] = sumX;
}}
for (int x = initX; x < maxX; x++) 
{
for (int y = initY; y < maxY; y += w)
{
float sum = 0f;
int index = x + y;
for (int i = 1; i < kw; i++)
{
sum += diffKernel[i] * (yConv[index - i] - yConv[index + i]);
xgrd[index] = sum;
}}}
for (int x = kw; x < w - kw; x++) 
{
for (int y = initY; y < maxY; y += w)
{
float sum = 0.0f;
int index = x + y;
int yOffset = w;
for (int i = 1; i < kw; i++)
{
sum += diffKernel[i] * (xConv[index - yOffset] - xConv[index + yOffset]);
yOffset += w;
}
ygrd[index] = sum;
}}
initX = kw;
maxX = w - kw;
initY = w * kw;
maxY = w * (h - kw);
for (int x = initX; x < maxX; x++) 
{
for (int y = initY; y < maxY; y += w)
{
int index = x + y;
int indexN = index - w;
int indexS = index + w;
int indexW = index - 1;
int indexE = index + 1;
int indexNW = indexN - 1;
int indexNE = indexN + 1;
int indexSW = indexS - 1;
int indexSE = indexS + 1;
float xGrad = xgrd[index];
float yGrad = ygrd[index];
float gradMag = hypot(xGrad, yGrad);
float nMag = hypot(xgrd[indexN], ygrd[indexN]);
float sMag = hypot(xgrd[indexS], ygrd[indexS]);
float wMag = hypot(xgrd[indexW], ygrd[indexW]);
float eMag = hypot(xgrd[indexE], ygrd[indexE]);
float neMag = hypot(xgrd[indexNE], ygrd[indexNE]);
float seMag = hypot(xgrd[indexSE], ygrd[indexSE]);
float swMag = hypot(xgrd[indexSW], ygrd[indexSW]);
float nwMag = hypot(xgrd[indexNW], ygrd[indexNW]);
float tmp;
if (xGrad * yGrad <= (float) 0
? Math.abs(xGrad) >= Math.abs(yGrad) 
? (tmp = Math.abs(xGrad * gradMag)) >= Math.abs(yGrad * neMag - (xGrad + yGrad) * eMag) 
&& tmp > Math.abs(yGrad * swMag - (xGrad + yGrad) * wMag) 
: (tmp = Math.abs(yGrad * gradMag)) >= Math.abs(xGrad * neMag - (yGrad + xGrad) * nMag) 
&& tmp > Math.abs(xGrad * swMag - (yGrad + xGrad) * sMag)
: Math.abs(xGrad) >= Math.abs(yGrad) 
? (tmp = Math.abs(xGrad * gradMag)) >= Math.abs(yGrad * seMag + (xGrad - yGrad) * eMag) 
&& tmp > Math.abs(yGrad * nwMag + (xGrad - yGrad) * wMag) 
: (tmp = Math.abs(yGrad * gradMag)) >= Math.abs(xGrad * seMag + (yGrad - xGrad) * sMag) 
&& tmp > Math.abs(xGrad * nwMag + (yGrad - xGrad) * nMag) 
) {
mgt[index] = gradMag >= mgl ? mgm : (int) (mgs * gradMag);
} else {mgt[index] = 0;}
}}}
 
public static float hypot(float x, float y) {
return (float) Math.hypot(x, y);
}


public static float gaussian(float x, float sigma) {
return (float) Math.exp(-(x * x) / (2f * sigma * sigma));
}
 
public static void fllw(int x1, int y1, int i1, int threshold,int w,int h,int[] data,int[] mgt)
{
int x0 = x1 == 0 ? x1 : x1 - 1;
int x2 = x1 == w - 1 ? x1 : x1 + 1;
int y0 = y1 == 0 ? y1 : y1 - 1;
int y2 = y1 == h -1 ? y1 : y1 + 1;
data[i1] = mgt[i1];
for (int x = x0; x <= x2; x++) 
{
for (int y = y0; y <= y2; y++)
{
int i2 = x + y * w;
if ((y != y1 || x != x1)
&& data[i2] == 0 
&& mgt[i2] >= threshold) {
fllw(x, y, i2, threshold,w,h,data,mgt);
return;}}}
}
    public static int[][] mtrxmul(double[][]a,double[][]b)
    {
    int sum=0;
    int r1=a.length,c1=a[0].length,r2=b.length,c2=b[0].length;
    if(c1==r2)
    {
    int c[][]=new int[r1][c2];
    for(int i=0;i<r1;i++)
    {
    for(int k=0;k<c2;k++)
    {
    for(int j=0;j<c1;j++)
    {
    sum+=Math.abs(a[i][j])*Math.abs(b[j][k]);
    }
    c[i][k]=sum;
    sum=0;
    }}
    return c;
   }
   else
   {return null;}
   }
    public static int sum(int[][] ary)
    {
    int sum=0;
    for(int i = 0; i < ary.length; i++) 
    {
    for(int j = 0; j < ary[0].length; j++) 
    {
     sum+=ary[i][j];
    }
    }
    return sum;
    }
    public static int max(int[] ary)
    {
    int w=ary.length;int mv=0;
    for (int x = 0; x < w; x++) 
    {
      if(ary[x]>mv){ mv=ary[x];}
    }
    return mv;
    }
    public static int[][] mtrxadd(int[][]a,int[][]b)
    {
    
    int r1=a.length,c1=a[0].length,r2=b.length,c2=b[0].length;
    if(r1==r2 && c1==c2)
    {
    int c[][]=new int[r1][c1];
    for(int i=0;i<r1;i++)
    {
    for(int j=0;j<c1;j++)
    {
    c[i][j]=(a[i][j]+b[i][j]);
    }
    }
    return c;
   }
   else
   {return null;}
   }
    public static int median(int []a)
    {
    int []b =a;
    Arrays.sort(b);
    double med;
    if (b.length % 2 == 0)
    {med = ((double)b[b.length/2] + (double)b[b.length/2-1])/2;}
    else
    {med = (double) b[b.length/2];}
    return (int)Math.round(med);
    }
    public static int min(int[] ary)
    {
    int w=ary.length;int mv=255;
    for (int x = 0; x < w; x++) 
    {
      if(ary[x]<mv){ mv=ary[x];}
    }
    return mv;
    }
    public Rectangle2D mkrct(int x1, int y1, int x2, int y2)
    {
      return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}

