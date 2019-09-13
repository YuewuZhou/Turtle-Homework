//This work was done by Yuewu Zhou for CS 170 section 1 Homework 1
public class hw1 {

    //requires the use of Turtle.java
    public static void main(String[] args){
        Turtle t=new Turtle();

        // 2) Medival town
        paintTown(t);

        // 3) polyspiral test cases
        polyspiral(t, 3, 20, 5);
        polyspiral(t, 5, 10, 4);
        polyspiral(t, 8, 5, 3);

        // 4) polywheel test cases
        polywheel(t,3,70);
        polywheel(t,4,50);
        polywheel(t,5,40);
        polywheel(t,6,30);
        polywheel(t,12,15);

        // 5) multistar test cases
        multistar(t, 7, 100);
        multistar(t, 10, 70);
        multistar(t, 25, 120);

        // 6) pyramid test cases
        pyramid(t, 200, 5);
        pyramid(t, 200, 10);
        pyramid(t, 300, 10);

        // 7) houseline testcase
        houseline(t, 20);
    }



    //Medival town///////////////////////////////////////////////////////////////////////////////////////////////////////
    //610 x 370

    //centers the image and compiles the individual aspects of the Medival town
    public static void paintTown(Turtle t){
        back(t,250,0);
        paintStars(t);
        paintMountains(t);
        paintCastle(t);
        paintWalls(t);
        paintArches(t);
        paintTrees(t);
        go(t,250,0);
    }

    //takes coordinates of individual stars and loops through the position of each
    public static void paintStars(Turtle t){
        int[] x={50,90,130,210,250,380,440,520};
        int[] y={330,290,330,360,290,340,280,330};

        for(int i=0;i<x.length;i++){
            go(t,x[i],y[i]);
            star(t);
            back(t,x[i],y[i]);
        }
    }

    //places mountains at their coordinates
    public static void paintMountains(Turtle t){
        int[] x={0,180,390};
        int[] y={170,210,190};

        for(int i=0;i<x.length;i++){
            go(t,x[i],y[i]);
            mountain(t);
            back(t,x[i],y[i]);
        }
    }

    //places arches at set coordinates
    public static void paintArches(Turtle t){
        int[] x={210,400,450,500};
        int[] y={20,50,70,90};

        for(int i=0;i<x.length;i++){
            go(t,x[i],y[i]);
            arch(t);
            back(t,x[i],y[i]);
        }
    }

    //places trees at set coordinates
    public static void paintTrees(Turtle t){
        int[] x={470,500,510,530,540,560,570,600};
        int[] y={0,0,40,0,40,0,40,40};

        for(int i=0;i<x.length;i++){
            go(t,x[i],y[i]);
            tree(t);
            back(t,x[i],y[i]);
        }
    }

    //places towers at coordinates
    public static void paintCastle(Turtle t){
        int[] x={80,330};
        int[] y={20,20};

        for(int i=0;i<x.length;i++){
            go(t,x[i],y[i]);
            tower(t);
            back(t,x[i],y[i]);
        }
    }

    //places walls at coordinates
    public static void paintWalls(Turtle t){
        int[] x={130,250};
        int[] y={20,20};

        for(int i=0;i<x.length;i++){
            go(t,x[i],y[i]);
            wall(t);
            back(t,x[i],y[i]);
        }
    }


    //constructs arch using 2 loops, the rest drawing the legs of the arch
    public static void arch(Turtle t){
        t.forward(10);
        t.left(90);
        t.forward(30);

        //the first loop generates the inner half circle by iterating through the degrees of the circle
        for(int i=0;i<180;i++){
            t.right(1);
            t.forward(Math.PI/180*10);
        }

        t.forward(30);
        t.left(90);
        t.forward(10);
        t.left(90);
        t.forward(30);

        //the second loop generates a larger half circle
        for(int i=0;i<180;i++){
            t.left(1);
            t.forward(2*Math.PI/180*10);
        }

        t.forward(30);
        t.left(90);
    }

    //constructs a tree from the top down
    public static void tree(Turtle t){
        go(t,0,40);

        //at each layer, the pointer draws a level of leaves, then drops down to the next layer
        for(int i=0;i<5;i++){
            leaves(t);
            t.right(90);
            t.forward(5);
            t.left(90);
        }

        //returns to the starting position at the bottom of the trunk
        t.right(90);
        t.forward(15);
        t.left(90);
    }

    //draws the leaves by pivoting left and right
    public static void leaves(Turtle t){
        double leaf=Math.sqrt(2)*10;
        t.left(45);
        t.backward(leaf);
        t.forward(leaf);
        t.right(90);
        t.forward(leaf);
        t.backward(leaf);
        t.left(45);
    }

    //constucts the wall in two layers, one with just rectangles and the other staggered
    public static void wall(Turtle t){

        //iterates between the two layers, moving up when a layer is done
        for(int i=0;i<3;i++){
            layer1(t);
            go(t,0,5);
            layer2(t);
            go(t,0,5);
        }

        //finishes with a solid layer
        layer1(t);

        //returns to original starting position
        back(t,0,30);
    }

    //constructs the primary layer with only whole rectangles
    public static void layer1(Turtle t){
        for(int i=0;i<8;i++){
            rectangle(t);
            t.forward(10);
        }
        t.backward(80);
    }

    //constructs the secondary layer with the split rectangles at the end
    public static void layer2(Turtle t){
        square(t,5);
        t.forward(5);
        for(int i=0;i<7;i++){
            rectangle(t);
            t.forward(10);
        }
        square(t,5);
        t.backward(75);
    }

    //formula for the bricks of the wall, non modular as the project does not require scaling
    public static void rectangle(Turtle t){
        for(int i=0;i<4;i++){
            t.forward(10);
            t.left(90);
            t.forward(5);
            t.left(90);
            t.forward(10);
            t.left(90);
            t.forward(5);
            t.left(90);
        }
    }

    //creates a tower through a lot of hard coding: there isn't a lot to optimize
    public static void tower(Turtle t){
        //the angle that the top of the tower uses
        double angle=Math.toDegrees(Math.asin(2/Math.sqrt(5)));

        t.forward(50);
        t.left(90);
        t.forward(100);
        t.right(angle);
        t.forward(Math.sqrt(5)*10);
        t.left(angle);
        t.forward(20);
        t.left(90);

        //draws most of the battlements with a loop
        for(int i=0;i<4;i++){
            t.forward(10);
            t.left(90);
            t.forward(10);
            t.right(90);
            t.forward(10);
            t.right(90);
            t.forward(10);
            t.left(90);
        }
        t.forward(10);
        t.left(90);
        t.forward(20);
        t.left(angle);
        t.forward(Math.sqrt(5)*10);
        t.right(angle);
        t.forward(100);
        t.left(90);

        //draws the windows
        windows(t);
    }

    //grid of squares
    public static void windows(Turtle t){

        //square grid going up
        go(t,10,40);
        for(int i=0;i<3;i++){
            square(t,10);
            go(t,0,20);
        }

        //square grid coming down
        go(t,20,0);
        for(int i=0;i<3;i++){
            back(t,0,20);
            square(t,10);
        }
        back(t,30,40);
    }

    //draws a mountain
    public static void mountain(Turtle t) {
        //degrees of turns
        double angleA = Math.toDegrees(Math.asin(Math.sqrt(80) / 20));
        double angleB = Math.toDegrees(180 - (180 - Math.asin(Math.sqrt(80) / 20) - Math.asin(Math.sqrt(320) / 20)));

        //length of edges
        double lengthA = Math.sqrt(320) * 10;
        double lengthB = Math.sqrt(180) * 10;

        //draws the first line of mountain
        t.left(angleA);
        t.forward(lengthA);

        //draws second line of mountain
        t.right(angleB);
        t.forward(lengthB);

        //goes back to starting point
        t.backward(lengthB);
        t.left(angleB);
        t.backward(lengthA);
        t.right(angleA);
    }

    //each star iterates through the angles of the arms and draws a line out and then back in
    public static void star(Turtle t){
        for(int i=0;i<8;i++){
            t.forward(10);
            t.backward(10);
            t.left(360/8);
        }
    }

    //Polyspiral///////////////////////////////////////////////////////////////////////////////////////////////////////
    //creates looped spiral that increase by 5 units for every line drawn
    public static void polyspiral(Turtle t, int n, double base, int rounds){
        double temp=base;
        for(int i=0;i<n*rounds;i++){
            t.forward(temp+=10);
            t.right(360.0/n);
        }

        //follows the spiral back to the starting position
        temp+=10;
        for(int i=0;i<n*rounds;i++){
            t.left(360.0/n);
            t.backward(temp-=10);
        }
    }

    //moves the turtle to the right and up without drawing, then reorients the pointer to its original orientation
    public static void go(Turtle t, int right, int up){
        t.penup();
        t.forward(right);
        t.left(90);
        t.forward(up);
        t.right(90);
        t.pendown();
    }

    //moves the turtle left and down without drawing, then reorients pointer
    public static void back(Turtle t, int right, int up){
        t.penup();
        t.left(90);
        t.backward(up);
        t.right(90);
        t.backward(right);
        t.pendown();
    }

    //Polywheel///////////////////////////////////////////////////////////////////////////////////////////////////////
    //rotates in the opposite direction of satellite polygons, thereby creating a flower pattern
    public static void polywheel(Turtle t, int numSides, double length){
        for(int i=0;i<numSides;i++){
            polygon(t,numSides,length);
            t.forward(length);
            t.left(360.0/numSides);

        }
    }

    //creates a shape with "numSides" number of sides that is "length" long
    public static void polygon(Turtle t, int numSides, double length){
        for(int i=0;i<numSides;i++){
            t.forward(length);
            t.right(360.0/numSides);
        }
    }
    //Multistar///////////////////////////////////////////////////////////////////////////////////////////////////////
    //draws a star at every point of a larger star
    public static void multistar(Turtle t,int n, double length){
        for (int i=0;i<n;i++) {
            double degree=360.0/n;
            t.right(degree);
            t.forward(length);
            drawstar(t,n,length/4);
            t.backward(length);
        }
    }

    //draws a star "length" long with n points
    static void drawstar(Turtle t, int n, double length){
        for (int i=0;i<n;i++) {
            double degree=360.0/n;
            t.right(degree);

            //360 degrees is split into the number of points the star needs, where a line is drawn
            t.forward(length);
            t.backward(length);
        }
    }

    //Pyramid///////////////////////////////////////////////////////////////////////////////////////////////////////
    //draws a pyramid that expands by 2 squares every level
    //until the base is "base" long and has "levels' number of levels
    public static void pyramid(Turtle t,double base, int levels){
        double squareBase=base/(levels*2-1);
        int i=1;
        int count=1;
        while(count<=levels){

            //draws a level of the pyramid
            pyramidLevel(t,squareBase,i);
            count++;

            //adds 2 squares for every level down
            i+=2;

            //moves down a square length
            t.penup();
            t.right(90);
            t.forward(squareBase);
            t.left(90);
            t.pendown();
        }

        //returns to original position and direction
        t.penup();
        t.left(90);
        t.forward(squareBase*levels);
        t.right(90);
        t.pendown();
    }

    //builds a pyramid base with "squares" number of squares, then returns to original position
    public static void pyramidLevel(Turtle t,double base, int squares){
        t.backward(base*(squares/2));
        for (int i=0;i<squares;i++){
            square(t,base);
            t.forward(base);
        }
        t.backward(base*(squares/2+1));
    }

    //Houseline///////////////////////////////////////////////////////////////////////////////////////////////////////
    //draws a line of houses, each with base length a consecutive fraction of the house before
    public static void houseline(Turtle t, int number){
        int base=80;
        int i=1;

        //sums distance travelled, which returns turtle to starting point
        double undo=0;

        while(i<=number){

            //keeps track of base length
            double temp=base/i;

            //draws house with base temp
            house(t,temp);

            t.penup();
            t.forward(temp*1.25);
            t.pendown();

            i++;
            undo+=temp*1.25;
        }

        //returns Turtle length "undo" to the start
        t.penup();
        t.backward(undo);
        t.pendown();
    }

    //code for the House provided by the Emory MathCS Department and modified for the purposes of the assignment
    //House Code Begins////////////////////////////////////////////////////////////////////////////////////////////
    //draws a house with Turtle t that has a base equal to "base"
    public static void house(Turtle t,double base) {
        front(t,base);
        t.left(90);
        t.forward(base);
        t.right(90);
        top(t,base);
        t.left(90);
        t.backward(base);
        t.right(90);
    }

    //draws the base of the house, including the door and windows
    public static void front(Turtle t, double base) {
        walls(t,base);
        t.forward(base*3/8);
        door(t,base);
        t.penup();
        t.left(90);
        t.forward(base*5/8);
        t.right(90);
        t.backward(base/4);
        t.pendown();
        windows(t,base);
        t.penup();
        t.backward(base/8);
        t.left(90);
        t.backward(base*5/8);
        t.right(90);
        t.pendown();
    }

    //method for the top of the house, draws roof and chimney
    public static void top(Turtle t,double base) {
        roof(t,base);
        t.penup();
        t.forward(base/8);
        t.left(90);
        t.forward(base/8);
        t.right(90);
        t.pendown();
        chimney(t,base);
        t.penup();
        t.backward(base/8);
        t.right(90);
        t.forward(base/8);
        t.left(90);
        t.pendown();
    }

    //walls are constructed as square with base length "base"
    public static void walls(Turtle t,double base) {
        square(t, base);
    }

    //windows are 1/4 the size of the walls,spaced equidistance from the central line on the top half of the base
    public static void windows(Turtle t,double base) {
        square(t, base/4);
        t.penup();
        t.forward(base/2);
        t.pendown();
        square(t, base/4);
        t.penup();
        t.backward(base/2);
        t.pendown();
    }

    //rectangle on the bottom half of house, centered and 1/4 base length wide, 3/8th base length tall
    public static void door(Turtle t,double base) {
        for (int i = 0; i < 2; i++) {
            t.forward(base/4);
            t.left(90);
            t.forward(base*3/8);
            t.left(90);
        }
    }

    //equilateral right triangle with hypotenuse equal to the base of the house
    public static void roof(Turtle t,double base) {
        t.forward(base);
        t.left(135);
        t.forward(base/2 * Math.sqrt(2));
        t.left(90);
        t.forward(base/2 * Math.sqrt(2));
        t.left(135);
    }

    //chimney 1/8 base length wide and 1/4 base length tall on left side of roof
    public static void chimney(Turtle t,double base) {
        t.left(90);
        t.forward(base/4);
        t.right(90);
        t.forward(base/8);
        t.right(90);
        t.forward(base/8);
        t.backward(base/8);
        t.left(90);
        t.backward(base/8);
        t.left(90);
        t.backward(base/4);
        t.right(90);
    }

    //builds a with that has a width of x
    public static void square(Turtle t, double x) {
        for (int i = 0; i < 4; i++) {
            t.forward(x);
            t.left(90);
        }
    }
    //House Code ends ///////////////////////////////////////////////////////////////////////////////////////////
}
