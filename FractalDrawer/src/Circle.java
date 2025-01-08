
import java.awt.Color;
public class Circle {
    double x;
    double y;
    double radius;

    Color color;
    public Circle(double xPostion,double yPosition, double circleRadius){
        x = xPostion;
        y = yPosition;
        radius = circleRadius;
    }

    //purpose: follows the circle perimeter formula of 2 * PI * radius
    //@param no parameters
    //@return return the perimeter of a circle
    public double calculatePerimeter(){

        return 2 * Math.PI * radius;
    }

    //purpose: calculate the area of a circle,follows the formula of PI * (radius ** 2), uses the Math.pow() function in this case for radius ** 2
    //@param no parameters
    //@return return area

    public double calculateArea(){

        return Math.PI * Math.pow(radius,2);
    }

    public void setColor(Color newColor){

        color = newColor;
    }

    public void setPOS(double newX, double newY){
        x = newX;
        y = newY;
    }

    public void setRadius(double newRadius){
        radius = newRadius;
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

    public double getRadius(){
        return radius;
    }





    




}
