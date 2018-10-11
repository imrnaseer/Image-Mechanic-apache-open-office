package uoj.csc.rad.gp1;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import static uoj.csc.rad.gp1.imgbfuncs.imggc;
import static uoj.csc.rad.gp1.imgofuncs.max;
import static uoj.csc.rad.gp1.imgofuncs.median;
import static uoj.csc.rad.gp1.imgofuncs.min;

/**
 *
 * @author Mohammad NaseeR
 */
public class imgsfltrs 
{
static int[][] mask(int r,int c,int n)
{
int[][]a=new int[r][c];
for(int i=0;i<r;i++)
{for(int j=0;j<c;j++)
{a[i][j]=n;}}
return a;
}

static BufferedImage lfiltrs(BufferedImage im,String s)
{
int w=im.getWidth();int h=im.getHeight();
BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
WritableRaster r1=im.getRaster();
WritableRaster r2=fimg.getRaster();
if(imggc(im,r1,w,h)==true)
{
if(s.equals("box")==true)
{
   int[][]H=mask(3,3,1);
    for(int x=1;x<h-1;x++)
    {
    for(int y=1;y<w-1;y++)
    {
    int sum=0;
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
             int[] pi=r1.getPixel( y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                sum =(pi[0]*H[i+1][j+1])+sum;
            }}}
        int[] nc={sum/9,sum/9,sum/9,255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}
else if(s.equals("gaussian")==true)
{
   int[][]H={{0,1,2,1,0},{1,3,5,3,1},{2,5,9,5,2},{1,3,5,3,1},{0,1,2,1,0}};
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
                sum =(pi[0]*H[i+2][j+2])+sum;
            }}}
        int[] nc={sum/25,sum/25,sum/25,255};
        r2.setPixel(y,x,nc);  
    } }
        fimg.setData(r2);
        return fimg;
}
else if(s.equals("laplace")==true)
{
   int[][]H={{0,0,-1,0,0},{0,-1,-2,-1,0},{-1,-2,16,-2,-1},{0,-1,-2,-1,0},{0,0,-1,0,0}};
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
                sum =(pi[0]*H[i+2][j+2])+sum;
            }}}
        int[] nc={sum/25,sum/25,sum/25,255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}
}
else
{
if(s.equals("box")==true)
{
   int[][]H=mask(3,3,1);
    for(int x=1;x<h-1;x++)
    {
    for(int y=1;y<w-1;y++)
    {
    int sum1=0;int sum2=0;int sum3=0;
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
             int[]  pi=r1.getPixel( y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                sum1 =(pi[0]*H[i+1][j+1])+sum1;
                sum2 =(pi[1]*H[i+1][j+1])+sum2;
                sum3 =(pi[2]*H[i+1][j+1])+sum3;
            }}}
        int[] nc={sum1/9,sum2/9,sum3/9,255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}
else if(s.equals("gaussian")==true)
{
   int[][]H={{0,1,2,1,0},{1,3,5,3,1},{2,5,9,5,2},{1,3,5,3,1},{0,1,2,1,0}};
    for(int x=2;x<h-2;x++)
    {
    for(int y=2;y<w-2;y++)
    {
    int sum1=0;int sum2=0;int sum3=0;
        for(int i=-2;i<=2;i++)
        {
            for(int j=-2;j<=2;j++)
            {
             int[]  pi=r1.getPixel(y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                sum1 =(pi[0]*H[i+2][j+2])+sum1;
                sum2 =(pi[1]*H[i+2][j+2])+sum2;
                sum3 =(pi[2]*H[i+2][j+2])+sum3;
            }}}
        int[] nc={sum1/25,sum2/25,sum3/25,255};
        r2.setPixel(y,x,nc);  
    } }
        fimg.setData(r2);
        return fimg;
}
else if(s.equals("laplace")==true)
{
   int[][]H={{0,0,-1,0,0},{0,-1,-2,-1,0},{-1,-2,16,-2,-1},{0,-1,-2,-1,0},{0,0,-1,0,0}};
    for(int x=2;x<h-2;x++)
    {
    for(int y=2;y<w-2;y++)
    {
    int sum1=0;int sum2=0;int sum3=0;
        for(int i=-2;i<=2;i++)
        {
            for(int j=-2;j<=2;j++)
            {
             int[]  pi=r1.getPixel(y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                sum1 =(pi[0]*H[i+2][j+2])+sum1;
                sum2 =(pi[1]*H[i+2][j+2])+sum2;
                sum3 =(pi[2]*H[i+2][j+2])+sum3;
            }}}
        int[] nc={sum1/25,sum2/25,sum3/25,255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}}
return im;
}
static BufferedImage nlfiltrs(BufferedImage im,String s)
{
int w=im.getWidth();int h=im.getHeight();
BufferedImage fimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
WritableRaster r1=im.getRaster();
WritableRaster r2=fimg.getRaster();
if(imggc(im,r1,w,h)==true)
{
if(s.equals("min")==true)
{
    for(int x=1;x<h-1;x++)
    {
    for(int y=1;y<w-1;y++)
    {
    int[]H=new int[9];int c=0;
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
             int[]  pi=r1.getPixel( y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                H[c] =pi[0];
                c++;
            }}}
        int[] nc={min(H),min(H),min(H),255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}
else if(s.equals("max")==true)
{
    for(int x=1;x<h-1;x++)
    {
    for(int y=1;y<w-1;y++)
    {
    int[]H=new int[9];int c=0;
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
             int[]  pi=r1.getPixel( y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                H[c] =pi[0];
                c++;
            }}}
        int[] nc={max(H),max(H),max(H),255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}
else if(s.equals("med")==true)
{
    for(int x=1;x<h-1;x++)
    {
    for(int y=1;y<w-1;y++)
    {
    int[]H=new int[9];int c=0;
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
             int[]  pi=r1.getPixel( y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                H[c] =pi[0];
                c++;
            }}}
        int[] nc={median(H),median(H),median(H),255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}}
else
{  
if(s.equals("min")==true)
{
    for(int x=1;x<h-1;x++)
    {
    for(int y=1;y<w-1;y++)
    {
        int[][]H=new int [3][9];int c=0;
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
             int[]  pi=r1.getPixel( y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                H[0][c] =pi[0];
                H[1][c] =pi[1];
                H[2][c] =pi[2];
                c++;
            }}}
        int[] nc={min(H[0]),min(H[1]),min(H[2]),255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}
else if(s.equals("max")==true)
{
    for(int x=1;x<h-1;x++)
    {
    for(int y=1;y<w-1;y++)
    {
    int[][]H=new int [3][9];int c=0;
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
             int[]  pi=r1.getPixel( y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                H[0][c] =pi[0];
                H[1][c] =pi[1];
                H[2][c] =pi[2];
                c++;
            }}}
        int[] nc={max(H[0]),max(H[1]),max(H[2]),255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}
else if(s.equals("med")==true)
{
    for(int x=1;x<h-1;x++)
    {
    for(int y=1;y<w-1;y++)
    {
    int[][]H=new int [3][9];int c=0;
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
             int[]  pi=r1.getPixel( y+j,x+i, new int[4]);
             if(pi[3]!=0)
            {
                H[0][c] =pi[0];
                H[1][c] =pi[1];
                H[2][c] =pi[2];
                c++;
            }}}
        int[] nc={median(H[0]),median(H[1]),median(H[2]),255};
        r2.setPixel(y,x,nc);  
    }}
        fimg.setData(r2);
        return fimg;
}}
return im;
}

}

