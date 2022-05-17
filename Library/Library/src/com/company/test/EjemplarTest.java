package com.company.test;

import com.company.controller.EjemplarController;
import com.company.model.Ejemplar;
import com.company.view.IOView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EjemplarTest {

    public static void test() {
        System.out.println("***** EJEMPLARES TEST *****");

        ArrayList<Ejemplar> EjemplaresTest = new ArrayList<>();
        createFakeEjemplares(EjemplaresTest);
        printEjemplares(EjemplaresTest);
        //unitary tests
        testCreateEjemplarView();
        testCreateEjemplarController();
        System.out.println("Tests ending... \n");
    }


    private static void createFakeEjemplares(ArrayList<Ejemplar> ejemplaresTest) {
        Ejemplar ejemplar1 = new Ejemplar("Around Whe World in 80 Days", "Jules Verne");
        Ejemplar ejemplar2 = new Ejemplar("Treasure Island", "Robert Louis Stevenson");
        Ejemplar ejemplar3 = new Ejemplar("Robinson Crusoe", "Daniel Defoe");
        Ejemplar ejemplar4 = new Ejemplar("Little Women", "Louisa May Alcott");
        Ejemplar ejemplar5 = new Ejemplar("Frankenstein", "Mary Shelley");

        ejemplaresTest.add(ejemplar1);
        ejemplaresTest.add(ejemplar2);
        ejemplaresTest.add(ejemplar3);
        ejemplaresTest.add(ejemplar4);
        ejemplaresTest.add(ejemplar5);

        if (ejemplaresTest.size() == 5) System.out.println("Test #createFakeEjemplares OK");
        else System.out.println("Test #createFakeEjemplares FAIL");
    }

    private static void printEjemplares(ArrayList<Ejemplar> ejemplares) {
        System.out.println("Ejemplares:" + ejemplares + "\n");
    }

    private static void testCreateEjemplarView() {
        String fakeDataEjemplar = "Harry Potter and the Chamber of Secrets\n" + "J.K.Rowling\n";
        Scanner fakeReader = new Scanner(fakeDataEjemplar);

        String status = IOView.createItem(fakeReader);
        if (status.equals("created")) System.out.println("Test #testCreateEjemplarView OK");
        else System.out.println("Test #testCreateEjemplarView FAIL");
    }

    private static void testCreateEjemplarController() {
        HashMap<String, String> fakeDataEjemplar = new HashMap();
        fakeDataEjemplar.put("operation", "createEjemplar");
        fakeDataEjemplar.put("title", "Interview with the Vampire");
        fakeDataEjemplar.put("author", "Anne Rice");

        HashMap<String, String> responseHashMap = EjemplarController.createEjemplar(fakeDataEjemplar);

        if (responseHashMap.get("status").equals("created")) System.out.println("Test #testCreateEjemplarController OK");
        else System.out.println("Test #testCreateEjemplarController FAIL");
    }

}
