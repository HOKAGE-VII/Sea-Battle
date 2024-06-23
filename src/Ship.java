package src;

public class Ship
{
    private int x, y;       // начало координат корабля
    private int rotation;   // направление корабля
    private int size;       // размер корабля
    private int hits;       // количество попаданий

    public Ship(int x, int y, int rotation, int size)
    {
        this.x = x - 1;     //массивы начинаются с 0, чтобы пользователю было проще вводить значения
        this.y = y - 1;
        this.rotation = rotation;
        this.size = size;
        this.hits = 0;
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

    public void hit() {
        hits++;
    }

    public int getHits() {
        return hits;
    }

    public boolean isSunk() {
        return hits >= size;
    }
}
