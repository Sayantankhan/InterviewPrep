package design.patterns;

import java.io.*;

public class Factory {

    public static void main(String[] args) throws IOException {
        System.out.println("1. Card Payment \n2. UPI \n3. NetBanking");
        System.out.println("\nEnter your choice : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer choice = Integer.valueOf(br.readLine());
        Payments payments = null;
        switch (choice) {
            case 1: payments = new Card();
                break;
            case 2: payments = new UPI();
                break;
            case 3: payments = new NetBanking();
                break;
            default:
                break;
        }

        System.out.println(payments);
    }
}

interface Payments {
    void paymentMode();
}

class Card implements Payments {
    final String MODE = "Card Payemnt";

    @Override
    public void paymentMode() {
        System.out.println(MODE);
    }
}

class UPI implements Payments {
    final String MODE = "UPI Payemnt";

    @Override
    public void paymentMode() {
        System.out.println(MODE);
    }
}

class NetBanking implements Payments {
    final String MODE = "NetBanking";

    @Override
    public void paymentMode() {
        System.out.println(MODE);
    }
}
