package dataSets;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "nodes")
public class NodeDataSet implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @SuppressWarnings("UnusedDeclaration")
    public NodeDataSet() {
    }


    public NodeDataSet(String name) {
        this.id = -1;
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public long getId() {
        return id;
    }
}

