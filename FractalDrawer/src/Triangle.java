import  java.awt.Color;
public class Triangle {
    double x;
    double y;
    double width;
    double height;
    Color color;


    public Triangle(double xPos, double yPos, double triangleWidth, double triangleHeight){
        x = xPos;
        y = yPos;
        width = triangleWidth;
        height =triangleHeight;
    }

    //purpose: calculate the perimeter of a triangle,the perimeter follows the formula of  2 * squareroot of (width * width/ 4 + height * height) + width
    //@param no parameters
    //@return return perimeter

    public double calculatePerimeter(){
        return 2 * Math.sqrt(width*width/4+height*height) + width;
    }

    //purpose: calculate the area of the triangle,follows the formula base * height / 2
    //@param no parameters
    //@return return area
    public double calculateArea(){

        return (width * height) / 2;
    }

    public void setColor(Color newColor){
        color = newColor;
    }

    public void setPos(double newX, double newY){
        x = newX;
        y = newY;
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
