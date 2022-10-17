class Robot (var x : Int, var y : Int, var direction : Direction) {
    fun stepForward() {
        when (direction) {
            Direction.RIGHT -> x++
            Direction.LEFT -> x--
            Direction.UP -> y++
            Direction.DOWN -> y--
        }
    }

    fun turnLeft() {
        direction = when (direction) {
            Direction.RIGHT -> Direction.DOWN
            Direction.LEFT -> Direction.UP
            Direction.UP -> Direction.RIGHT
            Direction.DOWN -> Direction.LEFT
        }
    }
    fun turnRight() {
        direction = when (direction) {
            Direction.RIGHT -> Direction.UP
            Direction.LEFT -> Direction.DOWN
            Direction.UP -> Direction.LEFT
            Direction.DOWN -> Direction.RIGHT
        }
    }

    override fun toString(): String {
        return "(${x}, ${y}), looks ${direction}"
    }
}
