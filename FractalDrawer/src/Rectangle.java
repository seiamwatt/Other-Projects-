import java.awt.Color;
public class Rectangle {
    double x;
    double y;
    double width;
    double height;
    Color color;
    public Rectangle(double xPosition,double yPosition, double rectangleWidth, double rectangleHeight){
        x = xPosition;
        y = yPosition;
        width = rectangleWidth;
        height = rectangleHeight;
    }

    //purpose:add up all sides of a rectangle which is two side  width and two side of height
    //@param no parameters
    //@return return parameter
    public double calculatePerimeter(){
        return width + width + height + height;
    }

    //purpose: follows the area formula, this multiplies the rectangle width and height
    //@param no parameters
    //@return return area
    public double calculateArea(){
        return width * height;
    }

    public void setColor(Color newColor){
        color = newColor;
    }

    public void setPos(double xPos, double yPos){
        x = xPos;
        y = yPos;
    }

    public void setHeight(double newHeight){
        height = newHeight;
    }

    public void setWidth(double newWidth){
        width = newWidth;
    }

    public Color getColor(){
        return color;
    }

    public double getXPos(){
        return x;
    }

    public double getYPos(){
        return y;
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }




}
