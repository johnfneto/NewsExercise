package com.johnfneto.newsexercise.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by johnneto on 23/10/16.
 */

public class Items implements Serializable {

    private String title;
    private List<Item> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Item> getRows() {
        return rows;
    }

    public void setRows(List<Item> rows) {
        this.rows = rows;
    }

    public class Item implements Serializable {

        public final String title;
        public final String description;
        public final String imageHref;

        public Item(String title, String description, String imageHref) {
            this.title = title;
            this.description = description;
            this.imageHref = imageHref;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImage() {
            return imageHref;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", imageHref='" + imageHref + '\'' +
                    '}';
        }
    }
}
