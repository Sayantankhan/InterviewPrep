package custom;

import java.util.List;

public class CustomPromise {
    static class Resolver {
        String ans;
        void resolve(String message) {
            ans = message;
        }
    }

    static class Error {
        String error;
        void throwError(String message) {
            error = message;
            throw new RuntimeException(message);
        }
    }

    @FunctionalInterface
    interface PromiseBuilder {
        void promiseBuilder(Resolver resolver, Error error) throws InterruptedException;
    }

    @FunctionalInterface
    interface PromiseAction {
        Object action(Object o);
    }

    static class Promise {

        PromiseBuilder builder;
        PromiseAction action;
        Thread t;
        private Resolver resolver = new Resolver();
        private Error error = new Error();

        Promise(PromiseBuilder builder) {
            t = new Thread(() -> {
                try {
                    builder.promiseBuilder(resolver, error);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }

        /*
         * To check a promise alive or not
         */
        public boolean isAlive() { return t.isAlive();}

        public Promise then(PromiseAction action) throws InterruptedException {
            if( isAlive() )
                t.join();
            if(resolver.ans != null) action.action(resolver.ans);
            return this;
        }

        public Promise catchError(PromiseAction action) {
            if(error.error != null) action.action(error.error);
            return this;
        }
    }

    static class Promises {
        static boolean allSettled(List<Promise> promiseList) {

            int notAliveCount = (int)promiseList
                    .stream()
                    .filter(promise -> !promise.isAlive())
                    .count();

            if(promiseList.size() == notAliveCount) return true;
            else return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Promise promise = new Promise((resolver, error) -> {
            int count = 1001;
            Thread.sleep(5000);
            if ( count <= 1100) resolver.resolve("OK");
            else error.throwError("Error");
        });

        System.out.println(Promises.allSettled(List.of(promise)));
        Object obj = promise.then(data -> data)
                .catchError(error -> error);
        System.out.println(Promises.allSettled(List.of(promise)));
        System.out.println(obj);
    }

}
