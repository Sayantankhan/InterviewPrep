package design.patterns;

import util.Utility;

/*
 * Builder Pattern -  creational pattern used in software design to construct a complex object step by step.
 * It allows the construction of a product in a step-by-step fashion,
 * where the construction process can vary based on the type of product being built.
 *
 * Use Cases:
 * to construct a complex object step by step || Ex: Lombok
 */
public class Build {
    private String a;
    private int b;
    static Builder builder() {
        return new Builder();
    }
    static class Builder {
        private Build build = null;
        Builder() {
            build = new Build();
        }
        Builder a(String a){
            build.a = a;
            return this;
        }
        Builder b(int b){
            build.b = b;
            return this;
        }

        public Build build() {
            return build;
        }
    }

    public static void main(String[] args) throws Exception {

        Build obj = Build.builder()
                .a("SK")
                .b(1)
                .build();

        Utility.assertTrue(obj.a, "SK");
        Utility.assertTrue(obj.b, 1);
    }

}
