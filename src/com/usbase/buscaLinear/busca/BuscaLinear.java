package com.usbase.buscaLinear.busca;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Udinei Silva
* Essa classe implementa o algoritmo de Busca Linear utilizando Java
* com objetivo de observer a evolução de alguns recursos da Linguagem Java na
* execução desse algoritmo, bem como a velocidade de execução(pode variar em função do hardware
* que esta sendo executado) de cada uma das abordagens comentadas no artigo:
* O algoritmo de busca linear e o Java
*/
public class BuscaLinear {

    // a classe main fornecerá de forma estatica as entradas de dados para os metodos de busca
    public static void main(String[] args) {
        int vetor[] = {1, 5, 9, 4, 8, 50, 30, 40, 78, 63, 47};
        int numeroProcurado = 63;
        String string = "Udinei da Silva";

       long tempoInicial = System.nanoTime();

        // Descomente para executar os metodos de busca
        // Para uma melhor observação do comportamento, recomendo executar um metodo de cada vez
        // System.out.println(buscaLinear(vetor, numeroProcurado));                  //   1176300 - time execution
        // System.out.println(buscaLinearComHashMap(vetor, numeroProcurado));        //    357500 - time execution
        // System.out.println(buscaLinearComHashMapOnePass(vetor, numeroProcurado)); //    244300 - time execution
        // System.out.println(buscaLinearStream(vetor, numeroProcurado));            //   7114800 - time execution

        // Execucao dos metodos de conversao de String utilizando Streams API
        // System.out.println(split(string));
        // System.out.println(splitToListOfChar(string));
        // System.out.println(charRepetidoString(string));

        long tempoFinal = System.nanoTime();

        System.out.println("Tempo de execução: "+ (tempoFinal - tempoInicial));

    }


    // Converte um String em uma Lista de strings
    // entrada: Udinei da Silva
    // saida: [Udinei, da, silva]
    public static List<String> split(String str){
        return Stream.of(str.split(" "))
                .map(elem -> new String(elem))
                .collect(Collectors.toList());
    }

    // Converte um String em uma Lista de caracter
    // entrada:  Udinei da Silva
    // saida: [ , a, d, i]
    public static Set<Character> charRepetidoString(String str) {
        Set<String> allItems = new HashSet<>();

        Set<Character> duplicates =  str.chars()
                                      .mapToObj(item -> (char) item)
                                      .filter(n -> !allItems.add(String.valueOf(n))) // remove os repetidos
                                      .collect(Collectors.toSet());

         return duplicates;
    }


    // Converte um String em uma Lista de caracter
    // entrada:  Udinei da Silva
    // saida: [U, d, i, n, e, i,  , d, a,  , s, i, l, v, a]
    public static List<Character> splitToListOfChar(String str) {
        return str.chars()
                .mapToObj(item -> (char) item)
                .collect(Collectors.toList());
    }


    private static int buscaLinearStream(int[] vetor, int numeroProcurado) {
        return IntStream.range(0, vetor.length)
                .filter(i -> vetor[i] == numeroProcurado)
                .mapToObj(index -> index)
                .findFirst()
                .orElse(-1); // se nao encontrou retorna -1
    }



    public static int buscaLinearComHashMapOne(int[] vetor, int numeroProcurado) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < vetor.length; i++) {

            // verifica se o numeroProcurado ja existe no HashMap
            if (map.containsKey(numeroProcurado))
                return map.get(numeroProcurado);

            map.put(vetor[i], i); // passsando os dados do vetor pra um HasMap
        }

        // se nao encontrado
        return -1;
    }


    public static int buscaLinearComHashMapTwo(int[] vetor, int numeroProcurado) {

        Map<Integer, Integer> map = new HashMap<>();

        // passsando os dados do vetor pra um HasMap
        for (int i = 0; i < vetor.length; i++) {
            map.put(vetor[i], i);
        }

        // verificando se o numero procurado existe no hashMap
        if (map.containsKey(numeroProcurado))
            return map.get(numeroProcurado);

        // se nao encontrado
        return -1;
    }


    public static int buscaLinear(int[] vetor, int numeroProcurado) {

        for (int i = 0; i < vetor.length; i++)
            if (vetor[i] == numeroProcurado)
                return i;

        return -1; // Não achou numero procurado, retorna -1
    }

}

