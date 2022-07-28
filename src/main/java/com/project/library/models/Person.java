package com.project.library.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя человека не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно находиться в диапозоне от 2 до 100 символов")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1900, message = "Год рождения человека должен быть больше 1900")
    @Column(name = "year_of_brith")
    private int yearOfBrith;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {

    }

    public Person(String fullName, int yearOfBrith) {
        this.fullName = fullName;
        this.yearOfBrith = yearOfBrith;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBrith() {
        return yearOfBrith;
    }

    public void setYearOfBrith(int yearOfBrith) {
        this.yearOfBrith = yearOfBrith;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", yearOfBrith=" + yearOfBrith +
                ", books=" + books +
                '}';
    }
}
