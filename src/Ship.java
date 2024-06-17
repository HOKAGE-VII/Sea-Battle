package src;

public class Ship
{
    private int x, y;       // начало координат корабля
    private int rotation;   // направление корабля
    private int size;       // размер корабля

    public Ship(int x, int y, int rotation, int size)
    {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.size = size;
    }

    public int getX()
    {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRotation() {
        return rotation;
    }

    public int getSize() {
        return size;
    }

}
