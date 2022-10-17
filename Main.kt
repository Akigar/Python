import java.util.Scanner

class main_class{

}

enum class Direction{
    UP, DOWN, LEFT, RIGHT
}

fun moveRobot(r: Robot, right: turnRight, left: turnLeft, toX: Int, toY: Int) {

// TODO: Двигаемся по координате Y
    if (toY >= r.y){
        while (toY != r.y){
            r.stepForward()
            println("${r.x}, ${r.y}, ${r.direction},");
        }
    }
    else {
        while(r.direction != Direction.DOWN) {
            r.turnRight()
        }
        while (toY != r.y){
            r.stepForward()
            println("${r.x}, ${r.y}, ${r.direction},");
        }
    }
// TODO: Двигаемся по координате X
    if (toX >= r.x){
        while(r.direction != Direction.RIGHT) {
            r.turnRight()
        }
        while (toX != r.x){
            r.stepForward()
            println("${r.x}, ${r.y}, ${r.direction},");
        }
    }
    else {
        while(r.direction != Direction.LEFT) {
            r.turnRight()
        }
        while (toX != r.x){
            r.stepForward()
            println("${r.x}, ${r.y}, ${r.direction},");
        }
    }

    return println("The robot has come to a point: ${r.x}, ${r.y}, ${r.direction},");


}

fun main() {
    val r = Robot(2,-3, Direction.UP)
    val right = turnRight(r.x, r.y, Direction.UP)
    val left = turnLeft(r.x, r.y, Direction.UP)
   
    println("Enter toX: ")
    val num_x = Scanner(System. `in`)
    var toX = num_x.nextInt()

    println("Enter toY: ")
    val num_y = Scanner(System. `in`)
    var toY = num_y.nextInt()

    println(moveRobot(r, right, left, toX, toY))
}
