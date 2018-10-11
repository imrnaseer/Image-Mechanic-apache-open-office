 package uoj.csc.rad.gp1;

 import java.awt.BorderLayout;
 import java.awt.Component;
 import java.awt.Cursor;
 import java.awt.Point;
 import java.awt.Rectangle;
 import java.awt.event.MouseEvent;
 import javax.swing.JComponent;
 import javax.swing.event.MouseInputAdapter;
 import javax.swing.event.MouseInputListener;
import static uoj.csc.rad.gp1.imdesktop.jTextField1;
import static uoj.csc.rad.gp1.imdesktop.jTextField2;


 class rsbox extends JComponent
 {
   public rsbox(Component comp)
   {
     this(comp, new rsbbr(8));
   }
 
   public rsbox(Component comp, rsbbr border) {
     setLayout(new BorderLayout());
     add(comp);
     addMouseListener(this.resizeListener);
     addMouseMotionListener(this.resizeListener);
     setBorder(border);
   }
   
   private void resize() {
     if (getParent() != null)
       ((JComponent)getParent()).revalidate();
   }
   
   MouseInputListener resizeListener = new MouseInputAdapter() {
     private int cursor;
    
     @Override
     public void mouseMoved(MouseEvent me) 
     { 
         if (rsbox.this.hasFocus()) 
         {
        rsbbr border = (rsbbr)rsbox.this.getBorder();
         rsbox.this.setCursor(Cursor.getPredefinedCursor(border.getCursor(me)));
         rsbox.this.repaint();
        rsbox.this.revalidate();
       }
     }
 /*        public void mouseClicked(MouseEvent me) {
          if (me.getButton()==1) 
         {
         System.out.println(rsbox.HEIGHT);
         return null;
        rsbox.this.revalidate();
       }
     }*/

     @Override
     public void mouseExited(MouseEvent mouseEvent)
     {
       rsbox.this.setCursor(Cursor.getDefaultCursor());
     }
     private Point sp = null;
   
     @Override
    public void mousePressed(MouseEvent me) {
       rsbbr border = (rsbbr)rsbox.this.getBorder();
       this.cursor = border.getCursor(me);
      this.sp = me.getPoint();
       rsbox.this.requestFocus();
       rsbox.this.repaint();
       rsbox.this.revalidate();
     }
   
     @Override
     public void mouseDragged(MouseEvent me)
     {
       if (this.sp != null)
       {
         int x = rsbox.this.getX();
         int y = rsbox.this.getY();
        int w = rsbox.this.getWidth();
         int h = rsbox.this.getHeight();
         
        int dx = me.getX() - this.sp.x;
        int dy = me.getY() - this.sp.y;
      
         switch (this.cursor) {
         case 8: 
           if (h - dy >= 10) {
             rsbox.this.setBounds(x, y + dy, w, h - dy);
             rsbox.this.resize();
               jTextField1.setText(String.valueOf(w ));
              jTextField2.setText(String.valueOf(h - dy));
           }
           
           break;
         case 9: 
           if (h + dy >= 10) {
             rsbox.this.setBounds(x, y, w, h + dy);
             this.sp = me.getPoint();
             rsbox.this.resize();
               jTextField1.setText(String.valueOf(w ));
              jTextField2.setText(String.valueOf(h + dy));
           }
           
           break;
         case 10: 
           if (w - dx >= 10) {
            rsbox.this.setBounds(x + dx, y, w - dx, h);
             rsbox.this.resize();
               jTextField1.setText(String.valueOf(w - dx));
              jTextField2.setText(String.valueOf(h ));
           }
           
          break;
         case 11: 
           if (w + dx >= 10) {
             rsbox.this.setBounds(x, y, w + dx, h);
             this.sp = me.getPoint();
             rsbox.this.resize();
               jTextField1.setText(String.valueOf(w + dx));
              jTextField2.setText(String.valueOf(h));
           }
           
           break;
         case 6: 
           if ((w - dx >= 10) && (h - dy >= 10)) {
             rsbox.this.setBounds(x + dx, y + dy, w - dx, h - dy);
             rsbox.this.resize();
               jTextField1.setText(String.valueOf(w - dx));
              jTextField2.setText(String.valueOf(h - dy));
           }
           
           break;
         case 7: 
           if ((w + dx >= 10) && (h - dy >= 10)) {
             rsbox.this.setBounds(x, y + dy, w + dx, h - dy);
             this.sp = new Point(me.getX(), this.sp.y);
             rsbox.this.resize();
               jTextField1.setText(String.valueOf(w + dx));
              jTextField2.setText(String.valueOf(h - dy));
           }
           
           break;
         case 4: 
           if ((w - dx >= 10) && (h + dy >= 10)) {
             rsbox.this.setBounds(x + dx, y, w - dx, h + dy);
             this.sp = new Point(this.sp.x, me.getY());
             rsbox.this.resize();
               jTextField1.setText(String.valueOf(w - dx));
              jTextField2.setText(String.valueOf(h + dy));
           }
           
           break;
         case 5: 
           if ((w + dx >= 10) && (h + dy >= 10)) {
             rsbox.this.setBounds(x, y, w + dx, h + dy);
             this.sp = me.getPoint();
             rsbox.this.resize();
              jTextField1.setText(String.valueOf(w + dx));
              jTextField2.setText(String.valueOf(h + dy));
          }
           
           break;
         case 13: 
          Rectangle bounds = rsbox.this.getBounds();
           bounds.translate(dx, dy);
           rsbox.this.setBounds(bounds);
           rsbox.this.resize();
             jTextField1.setText(String.valueOf(rsbox.this.getWidth()));
              jTextField2.setText(String.valueOf(rsbox.this.getHeight()));
         }
        
         
         rsbox.this.setCursor(Cursor.getPredefinedCursor(this.cursor));
       }
       rsbox.this.repaint();
       rsbox.this.revalidate();
     }
  
     @Override
     public void mouseReleased(MouseEvent mouseEvent) {
       this.sp = null;
      rsbox.this.repaint();
       rsbox.this.revalidate();
     }
   };
}