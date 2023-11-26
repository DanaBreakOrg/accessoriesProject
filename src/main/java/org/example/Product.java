package org.example;

import java.util.ArrayList;
import java.util.List;

public class Product {



private String category;
        private String name;
        private String description;
        private double price;
        private String id;

        protected static final List<Product> P = new ArrayList<>() ;
        public Product(String id, String name, String description,String category, double price) {
            super();
            this.name = name;
            this.description = description;
            this.category=category;
            this.price = price;
            this.id = id;
        }

        public static List<Product> getP() {
            return P;
        }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
