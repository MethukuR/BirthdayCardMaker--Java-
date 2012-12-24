/*
 * Final Project: Birthday Card Maker
 * CSS 111, Wabash College
 * Long Pham
 */

import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;


public class BirthdayCard extends Picture
{
  
  //////// fields ////////
  private int width;
  private int height;
  private String text;
  private Picture bcoutside;
  private Picture bcinside;
    
  /////// constructors ///////
  public BirthdayCard() { }
  
  public BirthdayCard(int theWidth, int theHeight)
  {
    this.width = theWidth;
    this.height = theHeight;
  }
  
  public BirthdayCard(int theWidth, int theHeight, String theText)
  {
    this.width = theWidth;
    this.height = theHeight;
    this.text = theText;
  }
  
  public BirthdayCard(int theWidth, int theHeight, String theText, Picture theOutsidePic, Picture theInsidePic)
  {
    this.width = theWidth;
    this.height = theHeight;
    this.text = theText;
    this.bcoutside = theOutsidePic;
    this.bcinside = theInsidePic;
  }
  
  /////// methods ////////
  
  public int getCardWidth()
  {
    return this.width;
  }
  
  public void setCardWidth(int newWidth)
  {
    this.width = newWidth;
  }
  
  public int getCardHeight()
  {
    return this.height;
  }
  
  public void setCardHeight(int newHeight)
  {
    this.height = newHeight;
  }
  
  public String getCardText()
  {
    return this.text;
  }
  
  public void setCardText(String newText)
  {
    this.text = newText;
  }
  
  public void setGray()
  {
    this.bcoutside.makeGray();
  }
  
  public void setObama()
  {
    this.bcoutside.makeObama();
  }
  
  public void setPoster()
  {
    this.bcoutside.makePoster(4);
  }
  

  ////// generating the birthday card /////// 
  public static void main(String[] args) 
  {
     Picture bcfront = new Picture((String) FileChooser.pickAFile());
     Picture bcback = new Picture(300, 400);
     Picture bctop = new Picture(300, 400);
     Picture bcbottom = new Picture(300, 400);
     
     int width = 400;
     int height = 600;
     
     Picture bcoutside = new Picture(height, width);
     Picture bcinside = new Picture(height, width);
     String bctext = new String("Happy Birthday!!!");
     
     BirthdayCard bdaycard = new BirthdayCard(height, width, bctext, bcoutside, bcinside);
     
     bcoutside.BCoutside(bcfront.rotate(), bcback, 300);
     bcinside.BCinside(bctop,bcbottom,300,bctext);
     
     JOptionPane.showMessageDialog(null, "Here is the card you want to create.");
     
     bcoutside.show();
     bcinside.show();
  }
}
