
public class rectangleTest {

	public static void main(String[] args) {
		Rectangle box1 = new Rectangle (-10, 2); //Initialize box1 with length and width
		Rectangle box2 = new Rectangle (70, 200); //Initialize box2 with length and width
		Rectangle box3 = new Rectangle (15.37, 80.0001); //Initialize box3 with length and width
		
		System.out.println("Box1 Area: " + box1.getArea()); //Print the area of each box to the console
		System.out.println("Box2 Area: " + box2.getArea());
		System.out.println("Box3 Area: " + box3.getArea());
		
		box1.setLength(20); //Update the box1 length
		box1.setWidth(12); //Update the box1 width
		
		System.out.println("Box1 Area: " + box1.getArea()); //Print the new area of box1 to the console.
		

	}

}
