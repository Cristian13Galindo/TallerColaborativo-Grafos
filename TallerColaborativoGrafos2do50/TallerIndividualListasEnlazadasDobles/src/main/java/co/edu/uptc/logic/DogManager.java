package co.edu.uptc.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class DogManager {

    //Atributos
    private LinkedList<Dog> dogList;

    //Constructor
    public DogManager() {
        this.dogList = new LinkedList<>();
    }

    /**
     * Añadir un perro a la lista
     * @param id ID del perro
     * @param name Nombre del perro
     * @param age Edad del perro
     * @throws Exception Si el ID del perro ya existe
     */
    public void addDog(String id, String name, int age) throws Exception {
        Dog newDog = new Dog(id, name, age);
        if (findDog(id) == null) {
            dogList.addNodeLast(newDog);
        } else {
            throw new Exception("ID de perro ya existente.");
        }
    }

    /**
     * Buscar un perro en la lista por su ID
     * @param id ID del perro
     * @return Perro encontrado o null si no existe
     */
    public Dog findDog(String id) {
        Node<Dog> node = dogList.findNode(id);
        return node != null ? node.getInfo() : null;
    }

    /**
     * Eliminar un perro de la lista por su ID
     * @param id ID del perro
     * @throws Exception Si el perro no existe
     */
    public void deleteDog(String id) throws Exception {
        Node<Dog> node = dogList.findNode(id);
        if (node != null) {
            dogList.deleteNode(node);
        } else {
            throw new Exception("Perro no encontrado.");
        }
    }

    /**
     * Mostrar todos los perros en la lista
     * @param order Orden ascendente o descendente
     * @return Lista de perros ordenada
     */
    public List<Dog> showDogs(boolean order) {
        Comparator<Dog> orderById = Comparator.comparing(Dog::getId);
        if (order) {
            return dogList.getLinkedList(true).stream().sorted(orderById).collect(Collectors.toList());
        } else {
            return dogList.getLinkedList(true).stream().sorted(orderById.reversed()).collect(Collectors.toList());
        }
    }


    /**
     * Mostrar todos los perros en la lista
     * @param order Orden ascendente o descendente
     * @return Lista de perros ordenada
     */
    public Dog getFirstDog() {
        return dogList.getFirst();
    }

    /**
     * Mostrar todos los perros en la lista
     * @param order Orden ascendente o descendente
     * @return Lista de perros ordenada
     */
    public Dog getLastDog() {
        return dogList.getLast();
    }

    /**
     * Mostrar todos los perros en la lista
     * @param index Índice del perro
     * @return Perro en el índice especificado
     */
    public Dog getDogAt(int index) {
        return dogList.getObject(index);
    }
}
