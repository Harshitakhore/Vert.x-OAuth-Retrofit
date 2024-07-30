package org.example.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private String id;
    private String name;
    private Data data;

    // Getter and Setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for data
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    // Inner class Data
    public static class Data {
        private Double price;
        private String color;
        // other fields as necessary

        // Getter and Setter for price
        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        // Getter and Setter for color
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }


    }
}
