 package uoj.csc.rad.gp1;

 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Graphics;
 import java.awt.Insets;
import java.awt.Rectangle;
 import java.awt.event.MouseEvent;
 import javax.swing.border.Border;


 class rsbbr implements Border
 {
   private int dst = 8;
   int[] lcns = { 1, 5, 7, 3, 8, 2, 6, 4 };
  int[] crss = { 8, 9, 10, 11, 6, 7, 4, 5 };
   public rsbbr(int dst)
   {
     this.dst = dst;
   }
   @Override
   public Insets getBorderInsets(Component component) {
    return new Insets(this.dst, this.dst, this.dst, this.dst);
    }
   @Override
  public boolean isBorderOpaque() {return false; }
   @Override
   public void paintBorder(Component component, Graphics g, int x, int y, int w, int h)
  {
     g.setColor(Color.black);
     g.drawRect(x + this.dst / 2, y + this.dst / 2, w - this.dst, h - this.dst);
     if (component.hasFocus())
     {
    for (int i = 0; i < this.lcns.length; i++) {
        Rectangle rect = getRectangle(x, y, w, h, this.lcns[i]);
         g.setColor(Color.WHITE);
         g.fillRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
         g.setColor(Color.BLACK);
         g.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
       } }}
  private Rectangle getRectangle(int x, int y, int w, int h, int location) {
     switch (location) {
     case 1: 
      return new Rectangle(x + w / 2 - this.dst / 2, y, this.dst, this.dst);
     case 5: 
       return new Rectangle(x + w / 2 - this.dst / 2, y + h - this.dst, this.dst, this.dst);
     
     case 7: 
       return new Rectangle(x, y + h / 2 - this.dst / 2, this.dst, this.dst);
     case 3: 
       return new Rectangle(x + w - this.dst, y + h / 2 - this.dst / 2, this.dst, this.dst);
     
     case 8: 
       return new Rectangle(x, y, this.dst, this.dst);
     case 2: 
       return new Rectangle(x + w - this.dst, y, this.dst, this.dst);
     case 6: 
       return new Rectangle(x, y + h - this.dst, this.dst, this.dst);
     case 4: 
       return new Rectangle(x + w - this.dst, y + h - this.dst, this.dst, this.dst);
     }
     return null;
   }
   public int getCursor(MouseEvent me) {
     Component c = me.getComponent();
     int w = c.getWidth();
     int h = c.getHeight();
     for (int i = 0; i < this.lcns.length; i++) {
       Rectangle rect = getRectangle(0, 0, w, h, this.lcns[i]);
       if (rect.contains(me.getPoint())) {
         return this.crss[i];
       }} 
     return 13; 
   }}
