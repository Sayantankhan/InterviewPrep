package design.patterns;

import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * Proxy Pattern - a structural design pattern that provides a surrogate or placeholder for another object to control access to it.
 * This pattern is useful when you want to add an extra layer of control over access to an object.
 *
 * Use Cases:
 * Control access || Ex: Read Account Details but not modify.
 * To do Lazy instantiation of objects whose construction requires costly resources.
 */
public class Proxy {

    public static void main(String[] args) {
        Image image = new ProxyImage("a.jpg");
        // loaded once
        image.display();
        // cached
        image.display();
    }
}

interface Image {
    void display();
}

class RealImage implements Image {
    private InputStream stream;
    private String filename;

    RealImage(String filename) {
        System.out.println("Generating RealImage Instance!!");
        this.filename = filename;
        stream = loadFromDisk(filename);
    }

    private InputStream loadFromDisk(String filename) {
        // some operation
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
    }

    @Override
    public void display() {
        System.out.println("Displaying Image " + filename + " || Stream: " + stream);
    }
}

class ProxyImage implements Image {

    private RealImage image;
    String filename;

    ProxyImage(String fileName) {
        this.filename = fileName;
    }

    @Override
    public void display() {
        System.out.println("Inside Proxy Class");
        if(image == null) {
            image = new RealImage(filename);
        }
        image.display();
    }
}
