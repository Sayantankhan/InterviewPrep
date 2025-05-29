public class CanPlaceFlowers {
    // Can Place Flowers
    //
    // theater/movie hall : Given a row of seats represented as an array, where 1 indicates an occupied seat and 0 indicates an empty seat,
    // determine whether you can seat a given number of people under the condition that no one wants to sit next to another person.
    //Implement a function that takes the seat arrangement and the number of people to be seated and returns whether it's possible to accommodate them.
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for(int i = 0; i < flowerbed.length; i++) {
            if(flowerbed[i] == 0) {
                boolean left = (i == 0) || (flowerbed[i-1] == 0);
                boolean right = (i == flowerbed.length-1) || (flowerbed[i+1] == 0);

                if(left & right) {
                    flowerbed[i] = 1;
                    count++;
                    if(count >= n) return true;
                }
            }
        }

        return (count >= n) ? true : false;
    }
}
