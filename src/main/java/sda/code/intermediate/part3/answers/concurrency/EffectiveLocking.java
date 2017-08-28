package sda.code.intermediate.part3.answers.concurrency;

public class EffectiveLocking {

    private boolean najedzony;
    private final Object lock = new Object();

    public EffectiveLocking() {
        najedzony = false;
    }

    public void nakarm() {
        // dubluję warunek z sekcji krytycznej,
        // bo jeśli dany wątek już wie, że kot jest najedzony, to nie musi
        // synchronizować się z niczym
        if (!najedzony) {
            // chronimy sekcję krytyczną danej instancji (lock jest polem
            // nie-statycznym)
            synchronized (lock) {
                // jeśli kilka wątków zauwazyło, że kot żebrze o jedzenie,
                // to kilka może próbować wejść do sekcji krytycznej,
                // ale tylko pierwszemu może udać się wykonać akcję
                if (!najedzony) {
                    najedzony = true;
                    // przy karmieniu kot miałczy na rzecz danego wątku
                    System.out.println(Thread.currentThread().getName() + ": Miau!");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final EffectiveLocking kot = new EffectiveLocking();

        for (int i = 0; i < 1000; ++i) {
            new Thread(kot::nakarm).start();
        }

        Thread.sleep(1000);

        for (int i = 0; i < 1000; ++i) {
            new Thread(kot::nakarm).start();
        }

        Thread.sleep(1000);

        System.out.println("Koniec.");
    }
}
