/*
 * Projeto ciado por: Guilherme Simião Grangeiro
 * Jogo 21/BlackJack
 * treino de lógica de programaçao
 *
 * Jogo simples de 21 (Blackjack) criado em Java.
 *
 * O jogo se baseia em sorte: o programa solicita o número de jogadores e sorteia cartas para cada um.
 * Durante o jogo, os jogadores podem escolher entre "continuar" ou "parar". Se a soma das cartas de um jogador ultrapassar 21, ele perde automaticamente.
 *
 * Foram utilizados vetores para representar os valores e os naipes das cartas. Cada carta sorteada é uma combinação única entre valor e naipe, armazenada em um vetor separado.
 * Para garantir que nenhuma carta seja repetida, foi usado um vetor booleano que marca quais cartas já foram utilizadas.
 *
 * Ao final, o programa imprime a pontuação de cada jogador.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Criação do scanner
        Scanner scan = new Scanner(System.in);

        // Vetores e variaveis
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] naipes = {"♠", "♥", "♦", "♣"};
        String[] baralho = new String[52];
        int posicaoVetor = 0;

        // for para montar o vetor baralho
        for (int i = 0; i < valores.length; i++) {
            for (int j = 0; j < naipes.length; j++) {
                baralho[posicaoVetor] = valores[i] + naipes[j];
                posicaoVetor++;
            }
        }

        // print
        System.out.print("Quantas pessoas vão jogar? ");
        int quantidadeJogadores = scan.nextInt();

        // condicoes para o jogo rodar
        if (quantidadeJogadores <= 0 || quantidadeJogadores * 2 > 16) {
            System.out.println("O valor excede a quantidade de jogadores (MAX 8) ou não é válido!");
            return;
        } else {
            boolean[] cartasUsadas = new boolean[52];
            int[] pontos = new int[quantidadeJogadores];
            boolean[] ativos = new boolean[quantidadeJogadores]; // true = jogador ainda está jogando
            for (int i = 0; i < quantidadeJogadores; i++) {
                ativos[i] = true;
            }

            int jogadoresAtivos = quantidadeJogadores;

            // loop do jogo
            while (jogadoresAtivos > 0) {
                for (int k = 0; k < quantidadeJogadores; k++) {
                    if (!ativos[k]) continue; // Pula quem já perdeu ou parou

                    System.out.println("\n--- Jogador " + (k + 1) + " ---");

                    int posicao;
                    do {
                        posicao = (int) (Math.random() * 52);
                    } while (cartasUsadas[posicao]);
                    cartasUsadas[posicao] = true;
                    String carta = baralho[posicao];

                    System.out.println("Carta recebida: " + carta);

                    String valor = carta.substring(0, carta.length() - 1);

                    if (valor.equals("A")) {
                        pontos[k] += 11;
                    } else if (valor.equals("J") || valor.equals("Q") || valor.equals("K")) {
                        pontos[k] += 10;
                    } else {
                        pontos[k] += Integer.parseInt(valor);
                    }

                    System.out.println("Pontuação atual do jogador " + (k + 1) + ": " + pontos[k]);

                    if (pontos[k] > 21) {
                        System.out.println("Jogador " + (k + 1) + " estourou! Está fora do jogo.");
                        ativos[k] = false;
                        jogadoresAtivos--;
                    } else {
                        System.out.print("Deseja continuar? (s/n): ");
                        String resposta = scan.next();
                        if (!resposta.equalsIgnoreCase("s")) {
                            System.out.println("Jogador " + (k + 1) + " parou com " + pontos[k] + " pontos.");
                            ativos[k] = false;
                            jogadoresAtivos--;
                        }
                    }
                }
            }
            // print dos pontos
            System.out.println("\nFim do jogo!");
            for (int i = 0; i < quantidadeJogadores; i++) {
                System.out.println("Jogador " + (i + 1) + " terminou com " + pontos[i] + " pontos.");
            }
        }
    }
}