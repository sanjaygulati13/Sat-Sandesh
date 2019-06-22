import java.awt.*;
import java.awt.event.*;
import javax.swing.text.AttributeSet;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextFieldWithLimit extends JTextField implements KeyListener
{
   private int  maxLength;
   public TextFieldWithLimit(String initialStr,int col,int maxLength) 
   {
     super(initialStr,col);
     this.maxLength = maxLength;
     this.setDocument(new LimitDocument(maxLength));
     this.addKeyListener(this);

   }
   
   public TextFieldWithLimit (int col,int maxLength) 
   {
     this("",col,maxLength);
   }

   public void keyPressed(KeyEvent e) 
   {
       /*
    char c = e.getKeyChar();
    if(c=='\'')
    {
        e.consume();
    }
    int len = getText().length();
    if (len < maxLength) 
    {
      return;
    }
    else
    {
      if((c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE) ||(c==KeyEvent.VK_ENTER)||(c==KeyEvent.VK_TAB)||e.isActionKey())
         return;
      else 
      {
         e.consume(); 
       }
     }

        */

     //  char c=e.getKeyChar();

//       System.out.println(""+(int)c  );
//       if(c==(char)39)
//       {
//
//       }
//       {
//           //e.consume();
//           String data=this.getText();
//           int len=data.length();
//           System.out.println(len);
//           String editted="";
//           if(len>0)
//               editted=data.substring(0, len-2);
//           else if(len==0)
//               editted="";
//           this.setText(editted);
//       }
//        else
//            return;
   }
   @Override
   public void keyReleased(KeyEvent e) { }
   @Override
   public void keyTyped(KeyEvent e) { }
   }


class LimitDocument extends PlainDocument
{
 int limit;

 public LimitDocument(int limit)
 {
  this.limit = limit;
 }

 @Override
 public void insertString(int offset, String s, AttributeSet a) throws BadLocationException
 {
     if(s == null) return;
     //System.out.println(offset + " " + s.length() + " " + s + " " + limit );
  //if (offset + s.length() <= limit)
  if (getLength() + s.length() <= limit)
   super.insertString(offset,s,a);
  else
   Toolkit.getDefaultToolkit().beep();
 }
}

