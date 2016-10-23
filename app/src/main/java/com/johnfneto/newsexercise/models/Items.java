package com.johnfneto.newsexercise.models;

import com.johnfneto.newsexercise.R;

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

    public static class Item implements Serializable {

        public final String title;
        public final String description;
        public final String imageHref = null;
        public final int imageId;

        public Item(String title, String description, int imageId) {
            this.title = title;
            this.description = description;
            this.imageId = imageId;
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

        public int getImageId() {
            return imageId;
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

    public static void prepareDummyContent(List<Item> dummyList) {
        int[] covers = new int[]{
                R.mipmap.american_beaver,
                R.mipmap.canada,
                R.mipmap.the_golden_compass_still,
                0,
                0,
                R.mipmap.igloo_icon,
                R.mipmap.avril_lavigne,
                0,
                0,
                R.mipmap.majestic_moose,
                0,
                R.mipmap.that_fish_was_this_big,
                R.mipmap.mounty,
                0
        };

        Item a = new Item("Beavers", "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony", covers[0]);
        dummyList.add(a);

        a = new Item("Flag", "", covers[1]);
        dummyList.add(a);

        a = new Item("Transportation", "It is a well known fact that polar bears are the main mode of transportation in Canada. They consume far less gas and have the added benefit of being difficult to steal.", covers[2]);
        dummyList.add(a);

        a = new Item("Hockey Night in Canada", "These Saturday night CBC broadcasts originally aired on radio in 1931. In 1952 they debuted on television and continue to unite (and divide) the nation each week.", covers[3]);
        dummyList.add(a);

        a = new Item("Eh", "A chiefly Canadian interrogative utterance, usually expressing surprise or doubt or seeking confirmation.", covers[4]);
        dummyList.add(a);

        a = new Item("Housing", "Warmer than you might think.", covers[5]);
        dummyList.add(a);

        a = new Item("Public Shame", "Sadly it's true.", covers[6]);
        dummyList.add(a);

        a = new Item("", "", covers[7]);
        dummyList.add(a);

        a = new Item("Space Program", "Canada hopes to soon launch a man to the moon.", covers[8]);
        dummyList.add(a);

        a = new Item("Meese", "A moose is a common sight in Canada. Tall and majestic, they represent many of the values which Canadians imagine that they possess. They grow up to 2.7 metres long and can weigh over 700 kg. They swim at 10 km/h. Moose antlers weigh roughly 20 kg. The plural of moose is actually 'meese', despite what most dictionaries, encyclopedias, and experts will tell you.", covers[9]);
        dummyList.add(a);

        a = new Item("Geography", "It's really big.", covers[10]);
        dummyList.add(a);

        a = new Item("Kittens...", "Éare illegal. Cats are fine.", covers[11]);
        dummyList.add(a);

        a = new Item("Mounties", "They are the law. They are also Canada's foreign espionage service. Subtle.", covers[12]);
        dummyList.add(a);

        a = new Item("Language", "Nous parlons tous les langues importants.", covers[13]);
        dummyList.add(a);
    }
}
