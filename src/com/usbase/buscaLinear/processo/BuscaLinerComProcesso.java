package com.usbase.buscaLinear.processo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problema:
 * Dado um array de números inteiros e um alvo (outro numero inteiro), retornar os índices de dois números contidos no array.
 * cuja soma seja igual ao numero alvo.
 * Presumir que cada entrada tem exatamente uma solução e não pode usar o mesmo elemento duas vezes.
 * A resposta pode ser retornada em qualquer ordem.
 * Entrada: nums = [2,7,11,15], alvo = 9 Resultado: [0,1]
 * Saída: Como nums [0] + nums [1] == 9, retornamos [0, 1]
 */
public class BuscaLinerComProcesso {

    public static void main(String[] args) {
        int vetor[] = {3, 2, 4};
        int target = 6;

        //System.out.println(Arrays.toString(twoSum(vetor, target)));
        // System.out.println(Arrays.toString(towSumWhithMap(vetor, target)));
        System.out.println(Arrays.toString(twoSumHashMapOnePass(vetor, target)));
    }

    // Abordadgem força bruta usando duas iterações e uma condicional, que caso verdadeiro para o loop e retorna a resposta
    // 1 - para obter o elemento que sera somado com o restantes dos elementos na lista (a cada iteração será um novo elemento)
    // 2 - para percorrer todos elementos da lista, somando com o elemento da primeira iteração
    //     e verificando se a soma é igual ao alvo
    public static int[] twoSum(int[] nums, int target) {

        // primeira iteração i = indice do elemento a ser comparado com o restante dos elementos da lista
        for (int i = 0; i < nums.length; i++) {

            // segunda interação i+1 = é o indice dos proximos elementos a serem comparados
            // com elemento i da primeira iteração
            for (int j = i + 1; j < nums.length; j++) {

                // verifica se satifaz a condição: soma de dois elementos do vetor ser igual ao alvo
                // lembrete: target = num1 + num2 ou num1 = target - num2
                if (target == nums[j] + nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        // caso nenhum elemento encontrado lanca exception
        throw new IllegalArgumentException("No two sum solution");
    }


    // Utilizando a interface Map e sua implementação HashMap com duas iterações:
    // 1 - Para preencher o Map, informando como chave os elementos do array
    // 2 - Para encontrar
    public static int[] towSumWhithMap(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            // lembrete: target = num1 + num2 ou
            // num1 = target - num2
            int complement = target - nums[i];

            //  verifica se o complemento contem no map e se seu indice
            //  não é igual ao do elemento que esta sendo processado
            //  (o mesmo numero nao pode ser processado duas vezes)
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }


    public static int[] twoSumHashMapOnePass(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // complemento é o resultado da soma.
            // target = complement + nums[i]
            int complement = target - nums[i];

            // se o numero existe no vetor
            if (map.containsKey(complement)) {
                // retorna um vetor contendo o numero que é o resultado da soma de outro numero
                // do vetor e igual ao numero alvo, e seu indice
                return new int[]{map.get(complement), i};

            }

            // passe do vetor para o Hasmap a chave(nums[i]) e o indice i gerado pelo for
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No tuo sum solution");
    }
}


