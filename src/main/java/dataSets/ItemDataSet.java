package dataSets;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "Items")
public class ItemDataSet implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "price", updatable = false)
    private double price;

    @Column(name = "node_id", updatable = false)
    private long node_id;


    @SuppressWarnings("UnusedDeclaration")
    public ItemDataSet(){
    }

    @SuppressWarnings("UnusedDeclaration")
    public ItemDataSet(String name, double price, long node_id){
        this.id = -1;
        this.name = name;
        this.price = price;
        this.node_id = node_id;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getNode_id() {
        return node_id;
    }
}
