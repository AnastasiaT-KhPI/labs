package entity;

import java.util.Objects;

public class Ticket {

    //Id добавляем обязательно
    private Integer id;
    private int row;
    private int place;
    private boolean isAvailable = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    //Equals и hasCode переопределены из-за того, что дальше мы используем Set объектов Ticket
    //Для переопределения используйте Code->Generate...->equals & hasCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return row == ticket.row &&
                place == ticket.place &&
                isAvailable == ticket.isAvailable &&
                Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, place, isAvailable);
    }

    //ToString переопределим для удобного вывода информации в консоли
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", row=" + row +
                ", place=" + place +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
