# Other Projects

## MineField Game

This is a terminal-based Minefield game with two modes: **Testing** and **Actual**. The game offers three difficulty levels for you to choose from.

### How to Run:

1. Open your terminal.
2. Run the game by executing the command: `python minefield_game.py` (or the appropriate command for your environment).
3. Select the desired mode (Testing or Actual).
4. Choose one of the three available difficulty levels.

### Modes:
- **Testing Mode**: This mode is for practice or debugging. It allows you to test different features of the game without any stakes.
- **Actual Mode**: This is the main game mode where you play with the aim to win.

### Difficulty Levels:
1. Easy
2. Medium
3. Hard


## Fractal Drawer  

### Description  
This program creates fractals based on user-selected shapes using recursion. It generates vibrant, mathematically intriguing patterns while incorporating creative design principles.  

### Features  
- **Shape Selection**:  
  - Users can select from three base shapes:  
    - `Circle`  
    - `Triangle`  
    - `Rectangle`  
  - The chosen shape serves as the foundation for the fractal.  

- **Pattern Design**:  
  - The fractal pattern repeats recursively at least 7 times.  
  - Each repetition uses a unique color, ensuring distinct overlapping shapes.  
  - Colors can cycle or be chosen randomly, adding visual appeal.  

- **Recursive Mechanics**:  
  - Recursive calls adjust the size or position of shapes to create complex fractals.  
  - Minimum shapes per layer:  
    - **Rectangles and Circles**: At least 4 shapes.  
    - **Triangles**: At least 3 shapes (e.g., at the triangle's points or along its sides).  

- **Area Calculation**:  
  - The program computes the total area of all shapes forming the fractal.  
  - Overlapping shapes are included in the total sum.  
  - The result is displayed once the fractal is drawn.  

### How It Works  
1. The program prompts the user to select a base shape.  
2. Using recursion, the fractal is drawn layer by layer, with changes in size, position, and color for each iteration.  
3. After completing the fractal, the program calculates and displays the total area of all shapes.  

### Notes  
- Overlaps between shapes are not excluded in the area calculation.  
