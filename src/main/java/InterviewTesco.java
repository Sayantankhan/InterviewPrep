import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterviewTesco {
    /*
     *  Tesco gets millions of orders every day with an average basket size of 100 items. Tesco Business has got some regulations around selling products online and in stores. These regulations are mandatory from legal and business perspective to enforce for all order transactions.
        You are given an order with a list of products in the shopping cart/basket with productid, product Category and quantity. And also, Restriction Rules on Qty and Qty/Category.
        Example:
        Ordered items in the shopping cart/basket
        Item-1 -> productid=1, category=Paracetamol, quantity=3
        Item-2 -> productid=2, category=analgesic, quantity=3
        Item-3 -> productid=3, category=chocolate, quantity=8
        Item-4 -> productid=4, category= Paracetamol, quantity=2
        Business Restriction rules:
        Cannot buy more than 10 Quantity of any products - BulkBuyLimit
        Cannot buy more than 5 Quantity of paracetamol products – BulkBuyLimitCategory

        Write a restriction rule engine to run the restriction check against the shopping cart/basket and return the status as to MET/BREACHED indicating restriction status for the given restriction rules.
        For the above given example, the restriction status returned would be MET.
     */

    /*
     * Rule configuration Server
       Product_Type = * , Quantity = 10;
       Product_Type = Paracetamol, Quantity = 5;
     */

    // DB -> Cache server --> Near Cache - Application

    static class ItemUnits {
        String itemId;

        String productType;
        double quantity;

        ItemUnits(String itemId, Double quantity, String productType) {
            this.itemId = itemId;
            this.quantity = quantity;
            this.productType = productType;
        }

        public String getItemId(){
            return this.itemId;
        }

        public Double getQuantity() {
            return this.quantity;
        }
    }

    static class Basket {
        List<ItemUnits> itemUnitsList;

        Basket() {
            this.itemUnitsList = new ArrayList<>();
        }

        void addItemToBasket(ItemUnits itemUnits) {
            itemUnitsList.add(itemUnits);
        }

        List<ItemUnits> getItemUnitsList() {
            return this.itemUnitsList;
        }


    }

    static class Rules {
        String productType;
        Double quantity;

        Rules(String productType, Double quantity) {
            this.productType = productType;
            this.quantity = quantity;
        }
    }

    static class RuleEngine {
        List<Rules> rulesList;
        Map<String, Double> ruleMap;

        RuleEngine(List<Rules> rulesList) {
            this.rulesList = rulesList;
            this.ruleMap = new HashMap<>();
            aggregateRules(ruleMap, rulesList);
        }

        private void aggregateRules(Map<String, Double> map, List<Rules> rulesList) {
            rulesList.stream().forEach(rules -> map.put(rules.productType, rules.quantity));
        }

        // paracetamol -> 0
        List<String> validate(Basket basket) {
            List<String> validation = new ArrayList<>();
            Map<String, Double> tempMap = new HashMap<>(ruleMap);
            Double resProByQuantity = tempMap.get("ALL");

            List<ItemUnits> list = basket.getItemUnitsList();

            for(ItemUnits itemUnit : list) {
                if(resProByQuantity != null) {
                    if(itemUnit.quantity > resProByQuantity) {
                        validation.add(itemUnit.productType + " Product Item has crossed the limits");
                    }
                }

                Double d = tempMap.getOrDefault(itemUnit.productType, null);
                if(d != null) {
                    if(itemUnit.quantity > d) {
                        validation.add(itemUnit.productType + " has crossed the limits");
                    }
                    tempMap.put(itemUnit.productType , d - itemUnit.getQuantity());
                }
            }

            Double d = tempMap.get("ALL");
            if(d != null) {
                if(list.size() > d) validation.add("Total Item has crossed the limits");
            }
            return validation;
        }
    }

    public static void main(String[] args) {
//        List<ItemUnits> list = new ArrayList<>() {{
//            add(new ItemUnits("Paracetamol", 3.0, "Paracetamol"));
//            add(new ItemUnits("Analgesic", 3.0, "Analgesic"));
//            add(new ItemUnits("Cocolate", 8.0, "Cocolate"));
//            add(new ItemUnits("Paracetamol", 2.0, "Paracetamol"));
//        }};

        Basket basket = new Basket();
        basket.addItemToBasket(new ItemUnits("Paracetamol_1", 3.0, "Paracetamol"));
        basket.addItemToBasket(new ItemUnits("Analgesic", 3.0, "Analgesic"));
        basket.addItemToBasket(new ItemUnits("Cocolate", 8.0, "Cocolate"));
        basket.addItemToBasket(new ItemUnits("Paracetamol_2", 3.0, "Paracetamol"));
        // basket.addItemToBasket(new ItemUnits("Paracetamol_1", 3.0, "Paracetamol"));
        //

        List<Rules> rulesList = new ArrayList<>() {{
            add(new Rules("Paracetamol", 5.0));
            add(new Rules("ALL", 7.0));
        }};

        List<String> l = new RuleEngine(rulesList).validate(basket);
        l.stream().forEach(validationMsg -> System.out.println(validationMsg));

        // unit : ruleEngine
        // Integration : System perfoms
        // Load test
    }

}
