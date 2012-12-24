import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
 
  public static void main(String[] args) 
  {
     String fileName = FileChooser.pickAFile();
     Picture pictObj = new Picture(fileName);
     pictObj.explore();
  }
  
  public void makeGray()
  {
    // get array of pixels
    // and declare variables for color values
    Pixel[] pixelArray = this.getPixels();
    int red, blue, green, gray;
    
    // loop through each pixel and change color
    for( Pixel pixelObject : pixelArray )
    {
      red = pixelObject.getRed();
      green = pixelObject.getGreen();
      blue = pixelObject.getBlue();
      gray = (red + green + blue) / 3;
      
      pixelObject.setRed( gray );
      pixelObject.setBlue( gray );
      pixelObject.setGreen( gray );
    }

  } // makeGray

  public void makeGray3()
  {
    // get array of pixels
    // and declare variables for color values
    // Pixel[] pixelArray = this.getPixels();
    int red, blue, green, gray;
    int h = this.getHeight();
    int w = this.getWidth();
    Pixel pixelObject;
    
    // loop through each pixel and change color
    for( int i = 0; i < w; ++i )
    {
      for( int j = 0; j < - h * (i - w) / w; ++j ) 
      {
        pixelObject = this.getPixel(i,j);
        red = pixelObject.getRed();
        green = pixelObject.getGreen();
        blue = pixelObject.getBlue();
        gray = (red + green + blue) / 3;
        pixelObject.setRed( gray );
        pixelObject.setBlue( gray );
        pixelObject.setGreen( gray );
      }
    }

  } // makeGray3

  public void makeGrayLevels()
  {
    // get array of pixels
    // and declare variables for color values
    Pixel[] pixelArray = this.getPixels();
    int red, blue, green, gray;
    
    // loop through each pixel and change color
    for( Pixel pixelObject : pixelArray )
    {
      red = pixelObject.getRed();
      green = pixelObject.getGreen();
      blue = pixelObject.getBlue();
      gray = (red + green + blue) / 3;
      
      if( gray > 180 )
        gray = 255;
      else if( gray > 90 )
        gray = 150;
      else
        gray = 0;
      
      pixelObject.setRed( gray );
      pixelObject.setBlue( gray );
      pixelObject.setGreen( gray );
    }

  } // makeGrayLevels

  public void makeObama()
  {
    // get array of pixels
    // and declare variables for color values
    Pixel[] pixelArray = this.getPixels();
    int red, blue, green, gray;
    
    // loop through each pixel and change color
    for( Pixel pixelObject : pixelArray )
    {
      red = pixelObject.getRed();
      green = pixelObject.getGreen();
      blue = pixelObject.getBlue();
      gray = (red + green + blue) / 3;
      
      if( gray > 180 )
        pixelObject.setColor( Color.white );
      else if( gray > 90 )
        pixelObject.setColor( Color.red );
      else
        pixelObject.setColor( Color.blue );
    }

  } // makeObama

  public void makePoster( Color[] colors )
  {
    // get array of pixels
    // and declare variables for color values
    Pixel[] pixelArray = this.getPixels();
    int red, blue, green, gray;
    int n = colors.length;
    
    // loop through each pixel and change color
    for( Pixel pixelObject : pixelArray )
    {
      red = pixelObject.getRed();
      green = pixelObject.getGreen();
      blue = pixelObject.getBlue();
      gray = (red + green + blue) / 3;
      
      pixelObject.setColor( colors[ gray * n / 256] );
    }

  } // makePoster

  public void makePoster( int n )
  {
    // get array of pixels
    // and declare variables for color values
    Pixel[] pixelArray = this.getPixels();
    int red, blue, green, gray;
    int inc = (int) (256.0/ n);
    
    // loop through each pixel and change color
    for( Pixel pixelObject : pixelArray )
    {
      red = pixelObject.getRed();
      green = pixelObject.getGreen();
      blue = pixelObject.getBlue();

      pixelObject.setRed( red * n / 255 * inc );
      pixelObject.setGreen( green * n / 255 * inc );
      pixelObject.setBlue( blue * n / 255 * inc );
    }

  } // makePoster

  public void mirrorLeftToRight()
  {
    int width = this.getWidth();
    int height = this.getHeight();
    
    for( int j = 0; j < height; ++j )
    {
      for( int i = width/2; i < width; ++i )
      {
        this.getPixel(i,j).setColor( this.getPixel( width-i-1, j ).getColor() );
      }
    }
  } // mirrorLeftToRight
  
  public Picture copyMe()
  {
    Picture target=new Picture("N:/Math/CSC111/classImages/blank400x400.jpg");
    target.show();
    Pixel sourcePixel, targetPixel;
    //for( int sourceX=0, targetX=0; sourceX<this.getWidth(); ++sourceX, ++targetX )
    for( int sourceX=140, targetX=0; sourceX<260; ++sourceX, ++targetX )
    {
      //for( int sourceY=0, targetY=0; sourceY<this.getHeight(); ++sourceY, ++targetY )
      for( int sourceY=140, targetY=0; sourceY<300; ++sourceY, ++targetY )
      {
        sourcePixel=this.getPixel(sourceX,sourceY);
        targetPixel=target.getPixel(targetX,targetY);
        targetPixel.setColor(sourcePixel.getColor());

        // Make another copy of pixel 200 to right
        targetPixel=target.getPixel(targetX+200,targetY);
        targetPixel.setColor(sourcePixel.getColor());

      }
    }
    target.repaint();
    return target;
  } // copyMe();

  public Picture halfSize()
  {
    // Target is half the size
    Picture target=new Picture((this.getWidth()+1)/2, (this.getHeight()+1)/2);
    target.show();
    Pixel sourcePixel, targetPixel;
    for( int sourceX=0, targetX=0; sourceX<this.getWidth(); sourceX+=2, ++targetX )
    {
      for( int sourceY=0, targetY=0; sourceY<this.getHeight(); sourceY+=2, ++targetY )
      {
        sourcePixel=this.getPixel(sourceX,sourceY);
        targetPixel=target.getPixel(targetX,targetY);
        targetPixel.setColor(sourcePixel.getColor());
      }
    }
    target.repaint();
    return target;
  } // copyMe();

  public Picture doubleSize()
  {
    // Target is twice the size
    Picture target=new Picture(this.getWidth()*2, this.getHeight()*2);
    target.show();
    Pixel sourcePixel, targetPixel;
    for( double sourceX=0, targetX=0; sourceX<this.getWidth(); sourceX+=0.5, ++targetX )
    {
      for( double sourceY=0, targetY=0; sourceY<this.getHeight(); sourceY+=0.5, ++targetY )
      {
        sourcePixel=this.getPixel((int) sourceX,(int) sourceY);
        targetPixel=target.getPixel((int) targetX, (int) targetY);
        targetPixel.setColor(sourcePixel.getColor());
      }
    }
    target.repaint();
    return target;
  } // copyMe();

  public Picture rotate()
  {
    // Save dimensions for later use 
    int w = this.getWidth();
    int h = this.getHeight();
    
    // Target has dimensions swapped
    Picture target=new Picture(h, w);
    target.show();
    Pixel sourcePixel, targetPixel;
    /*
    for( int sourceX=0; sourceX<w; ++sourceX )
    {
      for( int sourceY=0; sourceY<h; ++sourceY )
      {
        sourcePixel=this.getPixel(sourceX,sourceY);
        targetPixel=target.getPixel(h-sourceY-1, sourceX);
        targetPixel.setColor(sourcePixel.getColor());
      }
    }
    */
    for( int sourceX=0, targetY=0; sourceX<w; ++sourceX, ++targetY )
    {
      for( int sourceY=0, targetX=h-1; sourceY<h; ++sourceY, --targetX )
      {
        sourcePixel=this.getPixel(sourceX,sourceY);
        targetPixel=target.getPixel(targetX,targetY);
        targetPixel.setColor(sourcePixel.getColor());
      }
    }
    target.repaint();
    return target;
  } // rotate();

  public Picture blend( Picture pic )
  {
    // Save dimensions for later use.  Assume both pictures are the same size
    int w = this.getWidth();
    int h = this.getHeight();
    
    Picture target=new Picture(w, h);
    // target.show();
    Pixel thisPixel, picPixel, targetPixel;
    int red, green, blue;
    for( int thisX=0, picX=0, targetX=0; thisX<w; ++thisX, ++picX, ++targetX )
    {
      for( int thisY=0, picY=0, targetY=0; thisY<h; ++thisY, ++picY, ++targetY )
      {
        thisPixel=this.getPixel(picX,picY);
        picPixel=pic.getPixel(picX,picY);
        targetPixel=target.getPixel(targetX,targetY);
        red = ( thisPixel.getRed() + picPixel.getRed() ) / 2;
        green = ( thisPixel.getGreen() + picPixel.getGreen() ) / 2;
        blue = ( thisPixel.getBlue() + picPixel.getBlue() ) / 2;
        targetPixel.setRed( red );
        targetPixel.setGreen( green );
        targetPixel.setBlue( blue );
      }
    }
    // target.repaint();
    return target;
  } // blend();

  public Picture blend( Picture pic, int percent )
  {
    // Save dimensions for later use.  Assume both pictures are the same size
    int w = this.getWidth();
    int h = this.getHeight();
    
    //Picture target=new Picture(w, h);
    Picture target=this;
    // target.show();
    Pixel thisPixel, picPixel, targetPixel;
    int red, green, blue;
    for( int thisX=0, picX=0, targetX=0; thisX<w; ++thisX, ++picX, ++targetX )
    {
      for( int thisY=0, picY=0, targetY=0; thisY<h; ++thisY, ++picY, ++targetY )
      {
        thisPixel=this.getPixel(picX,picY);
        picPixel=pic.getPixel(picX,picY);
        targetPixel=target.getPixel(targetX,targetY);
        red = ( thisPixel.getRed()*(100-percent) + picPixel.getRed()*percent ) / 100;
        green = ( thisPixel.getGreen()*(100-percent) + picPixel.getGreen()*percent ) / 100;
        blue = ( thisPixel.getBlue()*(100-percent) + picPixel.getBlue()*percent ) / 100;
        targetPixel.setRed( red );
        targetPixel.setGreen( green );
        targetPixel.setBlue( blue );
      }
    }
    // target.repaint();
    return target;
  } // blend();
  
  public void greenScreen( Picture bgPic )
  {
    // Initialize objects used in this method
    // Color bgcolor = new Color( 0, 255, 0 );
    Color bgcolor = this.getPixel( 5, 5 ).getColor();
    
    double tolerance = 30.0;
    Pixel pixel;
    
    // Loop through all pixels
    for( int x = 0; x < this.getWidth(); ++x )
    {
      for( int y = 0; y < this.getHeight(); ++y )
      {
        pixel = this.getPixel( x, y );
        if( pixel.colorDistance( bgcolor ) < tolerance )
        {
          pixel.setColor( bgPic.getPixel( x, y ).getColor() );
        }
      }
    }
    
    this.repaint();
  }
  
  /**
   * Method to add a solid green circle to the current picture.
   */
  public void addCircle()
  {
    // get the graphics context from the picture
    Graphics g = this.getGraphics();
    
    // set color to green
    g.setColor( Color.green );
    
    // draw the circle as a filled oval within a square
    g.fillOval(50,20,50,50);
  }

  /**
   * Method to add a solid green circle in the center of the current picture.
   */
  public void addCircle2()
  {
    // get the graphics context from the picture
    Graphics g = this.getGraphics();
    
    // set color to green
    g.setColor( Color.green );
    
    // Find coordinates of circle
    int r = 150;
    int h = this.getHeight();
    int w = this.getWidth();
    
    int x = w/2 - r;
    int y = h/2 - r;
    
    // draw the circle as a filled oval within a square
    g.fillOval(x,y,2*r,2*r);
  }

  /**
   * Method to add a smiley face
   */
  public void smiley()
  {
    // get the graphics context from the picture
    Graphics g = this.getGraphics();
    
    // draw the background of the face
    int x = 120;
    int y = 90;
    int rFace = 64;
    g.setColor( Color.green );
    g.fillOval(x,y,2*rFace,2*rFace);
    
    // draw the eyes
    g.setColor( Color.black );
    int rEye = 5;
    g.fillOval( x+rFace/2-rEye, y+rFace/2+rEye, 2*rEye, 2*rEye );
    g.fillOval( x+3*rFace/2-rEye, y+rFace/2+rEye, 2*rEye, 2*rEye );
    
    // draw the mouth
    g.setColor( Color.black );
    g.drawArc( x+rFace/2, y+rFace/2, rFace, rFace, -45, -90 );
    
  }
  
  /**
   * add a block of a determined size and a random color to a random position 
   * in the picture
   */
  public void addBlock( int blockSize )
  {
    //
    int w = getWidth() - blockSize;
    int h = getHeight() - blockSize;
    
    //
    int x = (int) Math.floor( w * Math.random() );
    int y = (int) Math.floor( h * Math.random() );
    
    //
    int red = (int) Math.floor( 255 * Math.random() );
    int green = (int) Math.floor( 255 * Math.random() );
    int blue = (int) Math.floor( 255 * Math.random() );
    
    //
    Graphics g = this.getGraphics();
    g.setColor( new Color( red, green, blue ) );
    g.fillRect( x, y, blockSize, blockSize );
  }
  
  public void addBlock()
  {
    this.addBlock( (int) Math.floor( 40 * Math.random() ) );
  }

  public void addCircle( int blockSize )
  {
    //
    int w = getWidth() - blockSize;
    int h = getHeight() - blockSize;
    
    //
    int x = (int) Math.floor( w * Math.random() );
    int y = (int) Math.floor( h * Math.random() );
    
    //
    int red = (int) Math.floor( 255 * Math.random() );
    int green = (int) Math.floor( 255 * Math.random() );
    int blue = (int) Math.floor( 255 * Math.random() );
    
    //
    Graphics g = this.getGraphics();
    g.setColor( new Color( red, green, blue ) );
    g.fillOval( x, y, blockSize, blockSize );
  }
  
  public void addCirc()
  {
    this.addCircle( (int) Math.floor( 40 * Math.random() ) );
  }

  // write a text on the picture
  public void addText(String text)
  {
    //
    int w = getWidth() - (int) Math.random()*10;
    int h = getHeight() - (int) Math.random()*10;
    
    //
    int a = (int) Math.floor( w * Math.random()/2 );
    int b = (int) Math.floor( h * Math.random()/2 );
    
    //
    int red = (int) Math.floor( 255 * Math.random() );
    int green = (int) Math.floor( 255 * Math.random() );
    int blue = (int) Math.floor( 255 * Math.random() );
    
    //
    Graphics g = this.getGraphics();
    g.setColor( new Color( red, green, blue ) );
    g.setFont(new Font("Arial", Font.BOLD, 24));
    g.drawString( text, a, b );
  }
  
  //modify the front picture
  public void BCfront()
  {
    int m = (int) Math.random();
    if (m < 0.2)
    { this.makeGray(); }
    if (m >= 0.2 && m < 0.4)
    { this.makeObama(); }
    if (m >= 0.4 && m < 0.6)
    { this.makePoster(2); }
    if (m >= 0.6 && m < 0.8)
    { this.makeGrayLevels(); }
    else
    { this.makeGray3(); }
  }
  
  // generate the back picture
  public void BCback()
  {
    for( int i = 1; i < 10000; ++i )
    {
      this.addCirc();
    }
    this.repaint();
  }
    
  // generate the top picture
  public void BCtop()
  {
    int w = this.getWidth();
    int h = this.getHeight();
    
    Graphics g = this.getGraphics();
    
    int red = (int) Math.floor( 255 * Math.random() );
    int green = (int) Math.floor( 255 * Math.random() );
    int blue = (int) Math.floor( 255 * Math.random() );
    Color color = new Color(red, green, blue);
    
    g.setColor(color);
    g.fillRect(0, 0, w, h);
  }
  
  // generate the bottom picture
  public void BCbottom(String text)
  {
    this.addText(text);
    this.repaint();
  }
  
  // combine the front and back pictures to create the outside of the card 
  public void BCoutside(Picture p1, Picture p2, int m)
  {
     p1.BCfront();
     p2.BCback();
     
     int n = p1.getWidth() - m;
     
     // get the Graphics2D object
     Graphics g = this.getGraphics();
     Graphics2D g2 = (Graphics2D) g;
     
     // drawing p1 up to the combining point
     g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) 1.0f));
     g2.drawImage(p1.getImage(), 0, 0, m, p1.getHeight(), 0, 0, m, p1.getHeight(), null);
     g2.drawImage(p1.getImage(), m, 0, p1.getWidth(), p2.getHeight(), m, 0, p1.getWidth(), p1.getHeight(), null);
     
     // set the composite to blend the pixels
     // 50%
     g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
     g2.drawImage(p2.getImage(), m, 0, p1.getWidth(), p2.getHeight(), 0, 0, n, p2.getHeight(), null);
     
     // draw p2
     g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0f));
     g2.drawImage(p2.getImage(), p1.getWidth(), 0, p2.getWidth() + m, p2.getHeight(), n, 0, p2.getWidth(), p2.getHeight(), null);
     
  }
  
  // // combine the top and bottom pictures to create the inside of the card
  public void BCinside(Picture p3, Picture p4, int x, String text)
  {
    p3.BCtop();
    p4.BCbottom(text);

    int y = p3.getWidth() - x;
    
    Graphics gg = this.getGraphics();
    Graphics2D gg2 = (Graphics2D) gg;
     
    gg2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) 1.0f));
    gg2.drawImage(p3.getImage(), 0, 0, x, p3.getHeight(), 0, 0, x, p3.getHeight(), null);
    gg2.drawImage(p3.getImage(), x, 0, p3.getWidth(), p4.getHeight(), x, 0, p3.getWidth(), p3.getHeight(), null);
     
    gg2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    gg2.drawImage(p4.getImage(), x, 0, p3.getWidth(), p4.getHeight(), 0, 0, y, p4.getHeight(), null);
    
    gg2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0f));
    gg2.drawImage(p4.getImage(), p3.getWidth(), 0, p4.getWidth() + x, p4.getHeight(), y, 0, p4.getWidth(), p4.getHeight(), null);
    
  }
 
} // this } is the end of class Picture, put all new methods before this
