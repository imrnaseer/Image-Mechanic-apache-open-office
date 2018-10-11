
package uoj.csc.rad.gp1;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Mohammad NaseeR
 */
public class imgldr {
    public static JFileChooser imgcsr(String str)
    {
    FileNameExtensionFilter jfilter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
        FileNameExtensionFilter pfilter = new FileNameExtensionFilter("PNG file", "png");
        FileNameExtensionFilter gfilter = new FileNameExtensionFilter("GIF file", "gif");
        FileNameExtensionFilter tfilter = new FileNameExtensionFilter("TIFF file", "tiff");
        FileNameExtensionFilter bfilter = new FileNameExtensionFilter("BitMap file", "bmp");
        FileNameExtensionFilter pbfilter = new FileNameExtensionFilter("Portable BitMap file", "pbm");
        FileNameExtensionFilter efilter = new FileNameExtensionFilter("Exchangle Image file", "exif");
        FileNameExtensionFilter ifilter = new FileNameExtensionFilter("All Image files", "jpg", "jpeg","png", "gif", "tiff"
        , "bmp", "pbm", "exif");
        JFileChooser fc=new JFileChooser("Image Picker");
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(jfilter);fc.setFileFilter(gfilter);fc.setFileFilter(tfilter);
        fc.setFileFilter(bfilter);fc.setFileFilter(pbfilter);fc.setFileFilter(efilter);
        fc.setFileFilter(pfilter);
        if(str.equals("open"))
        {fc.setFileFilter(ifilter);}
         return fc;
    }
    
}
