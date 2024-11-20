package co.edu.uptc.model;

public class Dog {

    //Atributos
    private String id;
    private String name;
    private int age;

    //Constructor
    public Dog(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //toString
    @Override
    public String toString() {
        return "ID: " + id + " Nombre: " + name + " Edad: " + age;
    }
}
