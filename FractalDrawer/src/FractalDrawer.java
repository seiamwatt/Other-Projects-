// FractalDrawer class draws a fractal of a shape indicated by user input
import java.awt.Color;
import java.util.Scanner;
import java.util.Random;

public class FractalDrawer {
    private double totalArea = 0;  // member variable for tracking the total area

    public FractalDrawer() {

    }  // contructor


    //TODO:
    // drawFractal creates a new Canvas object
    // and determines which shapes to draw a fractal by calling appropriate helper function
    // drawFractal returns the area of the fractal


    // purpose: to determine what fractal shape should be drawn and draws the fractal , each drawfractal starts with the color blue and canvas size is made to 10
    // @param uses  user input from the scanner from main
    //@returns the method then returns the total area

    public double drawFractal(String type) {
        Canvas canvas = new Canvas(800,800);

        if (type.equals("Circle")){
            drawCircleFractal(400,300,450,Color.blue,canvas,7);
            return totalArea;
        } else if (type.equals("Triangle")) {
            drawTriangleFractal(200,200,300,450,Color.blue,canvas,7);
            return totalArea;
        } else if (type.equals("Rectangle")) {
            drawRectangleFractal(300,300,200,200,Color.blue,canvas,7);
            return totalArea;
        }
        return totalArea;
    }


    // purpose: draws triangle fractal which calls 3 recursive calls, color is randomized in the range of 256 using the random built in method
    // @ param width: triangle width, height: triangle height, x: triangle x position,y: triangle y position ,c: color, can: Canvas object ,level: levels of shape on the canvas
    //@returns no return value
    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level){


        if(level == 0){
            return ;
        }else{
            Random random = new Random();

            Color newColor = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));


            Triangle triangle = new Triangle(x,y,width,height);
            totalArea += triangle.calculateArea();
            triangle.setColor(c);
            can.drawShape(triangle);
            drawTriangleFractal(width / 2, height / 2,x + width,y / 2,newColor,can,level - 1);
            drawTriangleFractal(width / 2, height / 2,x - width,y * 2,newColor,can,level - 1);
            drawTriangleFractal(width / 2, height / 2,x + 0.5 * width ,y + width ,newColor,can,level - 1);
        }
    }




    // purpose: draw circle fractal using 4 recursive calls, color is randomized using the random built in method
    // @param radius: circle radius,x: circle x position,y: circle y position,c: color, can: canvas object,level: levels of shape on the canvas
    //@return no return value
    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        if(level == 0){
            return ;
        }else{
            Random random = new Random();


            Color newColor = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));


            Circle circle = new Circle(x,y,radius);
            can.drawShape(circle);
            totalArea += circle.calculateArea();

            circle.setColor(c);
            drawCircleFractal(radius / 2,x + radius,y + radius,newColor,can,level - 1);
            drawCircleFractal(radius / 2,x + radius,y - radius,newColor,can,level - 1);
            drawCircleFractal(radius / 2,x - radius,y + radius,newColor,can,level - 1);
            drawCircleFractal(radius / 2,x - radius,y - radius,newColor,can,level - 1);
        }
    }


    // purpose: draw rectangle fractal using 4 recursive calls, color is randomized using the random built in method
    //@param width: rectangle width, height: rectangle height, x: rectangle x position, y: rectangle y position, c: color,can: Canvas object,level: levels of shape on the canvas
    //@return no return value
    public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        if(level == 0){
            return;
        }else{
            Random random = new Random();

            Color newColor = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));


            Rectangle rectangle = new Rectangle(x,y,width,height);
            rectangle.setColor(c);
            can.drawShape(rectangle);
            totalArea += rectangle.calculateArea();


            drawRectangleFractal(width / 2, height / 2,x - width / 2 ,y + height ,newColor,can,level - 1);
            drawRectangleFractal(width / 2, height / 2,x + width,y - height / 2,newColor,can,level - 1);
            drawRectangleFractal(width / 2, height / 2,x - width /2 ,y - height/2,newColor,can,level - 1);
            drawRectangleFractal(width / 2, height / 2,x + width,y + height,newColor,can,level - 1);
        }
    }



    //TODO:
    // main should ask user for shape input, and then draw the corresponding fractal.
    // should print area of fractal
    public static void main(String[] args){
        //scanner takes in user input to put into the drawFractal parameter, this determines what shape will be drawn on the canvas
        Scanner scanner = new Scanner(System.in);

        System.out.println("choices: “Circle”, “Triangle”, or “Rectangle”) ");
        String shapeType = scanner.nextLine();



        FractalDrawer fractalDrawer = new FractalDrawer();
        System.out.println(fractalDrawer.drawFractal(shapeType));

        }





    }

