import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BINGOnovo {
    public static Random random=new Random();

    public static Scanner scanner = new Scanner(System.in);
    static int [] numUtilizadoSorteio =  new int[60];
    static int p = 0;

    public static void main(String[] args) {


    int [] CartelaAuto = gerarCartela();
    Arrays.sort(CartelaAuto);
    int CartelaEntregada [] = {0,0,0,0,0};
    int CartelaCorreta [] = {1,1,1,1,1};
    int rodada = 1;
    int Qtdejogadores;
    int [] pontos = new int[19];
    int [][] Cartelas = new int[19][5];
    int [][] ConfereCartelas = Arrays.copyOf(Cartelas,Cartelas.length);
    String NickNames [] = {"JOGADOR 01","JOGADOR 02","JOGADOR 03","JOGADOR 04","JOGADOR 05",
            "JOGADOR 06","JOGADOR 07","JOGADOR 08","JOGADOR 09","JOGADOR 10","JOGADOR 11",
            "JOGADOR 12","JOGADOR 13","JOGADOR 14","JOGADOR 15","JOGADOR 16","JOGADOR 17",
            "JOGADOR 18","JOGADOR 19"};

    Cartelas[0] = Arrays.copyOf(CartelaAuto,CartelaAuto.length);

    System.out.println("Insira a quantidade de jogadores" +
            ": ");
    Qtdejogadores = scanner.nextInt();
        for (int n = 0; n <Qtdejogadores; n++) {
            System.out.println("Preencha o nome do Jogador "+ (n+1));
            String novoNome=scanner.next();
            NickNames[n]=novoNome;
        }


        int [] CartelaManualInt = new int[5];

        System.out.println("\n\n\tOla, bem vindo ao bingo," + NickNames[0]+ "!!!\n" +
                "\tSua cartela foi gerada automaticamente e, em seguida devera gerar as cartelas" +
                " de seus oponentes caso nao esteja jogando sozinho(a)\n" +
                "Sua cartela : "+Arrays.toString(CartelaAuto) +"\n"+
                 "\n \t\tGerar Cartela para outros jogadores AUTOMATICAMENTE[1]" +
                "\n \t\tGerar Cartela para outros jogadores MANUALMENTE[2]");

        int resposta = (Qtdejogadores-1);

        int respostaBV = scanner.nextInt();
    switch (respostaBV){
        case 2:
            System.out.println("\nInsira os numeros separados por virgulas \n" +
                    "\texemplo: 1,2,3,4,5");


            System.out.println("\n");
            for (int a = 1; a <= resposta; a++) {
                System.out.println("\nCartela do "+NickNames[a]+":");
                String inserir = scanner.next();
                String []CartelaManualInsere=inserir.split(",");

                for (int i = 0; i < CartelaManualInsere.length; i++) {
                    CartelaManualInt[i]=Integer.parseInt(CartelaManualInsere[i]);

                }
                Cartelas[a]=Arrays.copyOf(CartelaManualInt,CartelaManualInt.length);
            }
            break;

        case 1:
            for (int z = 1; z <= resposta; z++) {
                Cartelas[z]=gerarCartela();
                Arrays.sort(Cartelas[z]);
            }
            break;
    }







        for (int i = 0; i < 19; i++) {
            System.out.println("CARTELA DE "+NickNames[i] +":"
                    + Arrays.toString(Cartelas[i]));
        }

//escolhe tipo de sorteio
    Arrays.sort(CartelaAuto);
    System.out.println("\n\n\nC A R T E L A :" + Arrays.toString(Cartelas[0]));
        System.out.println("O jogo vai começar\n" +
                " [1] Para sorteio AUTOMATICO\n" +
                " [2] Para sorteio MANUAL\n");
         int respostaComecaJogo = scanner.nextInt();

//sorteio AUTOMATICO
         switch (respostaComecaJogo){
             case 1:
                 while (!Arrays.equals(CartelaEntregada,CartelaCorreta)){
                     int[] sort = gerarSorteio();
                     Arrays.sort(sort);
                     System.out.println("\nAperte [QUALQUER TECLA] para proxima rodada:");
                     String respostaProxRod = scanner.next();

                     System.out.println("\nRodada" + rodada + ": \n"
                             + "Sua Cartela: " +Arrays.toString(Cartelas[0])
                             + "\nNum. Sorteados" + Arrays.toString(sort));
                     for (int o = 0; o < 19; o++) {
                         for (int i = 0; i < 5; i++) {
                             for (int k = 0; k < sort.length; k++) {
                                 if (Cartelas[o][i]==sort[k]){
                                     ConfereCartelas[o][i]=1;
                                     pontos[o]++;
                                 }
                             }
                         }
                     }
                     for (int v = 0; v < 19; v++) {
                         if (Arrays.equals(ConfereCartelas[v],CartelaCorreta)){
                             CartelaEntregada=new int[]{1,1,1,1,1};
                             System.out.println("\t\n\n O JOGADOR VENCEDOR FOI : "+NickNames[v]+
                                     " COM A CARTELA --->"
                                     +Arrays.toString(Cartelas[v]) +
                                     "\n\t O jogo teve " + rodada +" rodadas." +
                                     "\n\t Houveram " + (rodada*5) + " numeros sorteados.\n");
                             for (int i = 0; i < NickNames.length; i++) {
                                 System.out.println("\tPONTUACAO FINAL : "+ NickNames[i] +" --> "
                                         + pontos[i]);
                             }
                             break;
                         }
                     }
                     System.out.println("Sua situacao :"+Arrays.toString(ConfereCartelas[0]));
                     rodada++;
                 }

                 break;

//sorteio MENUAL
             case 2:
                 while (!Arrays.equals(CartelaEntregada,CartelaCorreta)){
                     System.out.println("\nDigite os numeros sorteados : ");
                     String numeros = scanner.next();
                     String arrayNum[]=numeros.split(",");
                     int[] sort= new int[5];
                     for (int i = 0; i < arrayNum.length; i++) {
                         sort[i]=Integer.parseInt(arrayNum[i]);
                     }

                     System.out.println("\nAperte [QUALQUER TECLA] para proxima rodada:");
                     String respostaProxRod = scanner.next();

                     System.out.println("\nRodada" + rodada + ": \n"
                             + "Sua Cartela: " +Arrays.toString(Cartelas[0])
                             + "\nNum. Sorteados" + Arrays.toString(sort));
                     for (int o = 0; o < 19; o++) {
                         for (int i = 0; i < 5; i++) {
                             for (int k = 0; k < sort.length; k++) {
                                 if (Cartelas[o][i]==sort[k]){
                                     ConfereCartelas[o][i]=1;
                                     pontos[o]++;
                                 }
                             }
                         }
                     }
                     for (int v = 0; v < 19; v++) {
                         if (Arrays.equals(ConfereCartelas[v],CartelaCorreta)){
                             CartelaEntregada=new int[]{1,1,1,1,1};
                             System.out.println("\t\n\n O JOGADOR VENCEDOR FOI : "+NickNames[v]+
                                     " COM A CARTELA --->"
                                     +Arrays.toString(Cartelas[v]) +
                                     "\n\t O jogo teve " + rodada +" rodadas."+
                                     "\n\t Houveram " + (rodada*5) + " numeros sorteados.\n");
                             for (int i = 0; i < NickNames.length; i++) {
                                 System.out.println("\tPONTUACAO FINAL : "+ NickNames[i] +" --> "
                                         + pontos[i]);
                             }
                             break;
                         }
                     }
                     System.out.println("\n\tSua situacao :"+Arrays.toString(ConfereCartelas[0]));
                     rodada++;

                 }
                 break;
         }

        System.out.println("Numeros Sorteados = " + Arrays.toString(numUtilizadoSorteio));

    }

    public static int[] gerarSorteio(){
        int [] sorteio = new int[5];

        for (int i = 0; i < sorteio.length; i++, p++) {
            sorteio[i] = gerarNumRandom();
            for (int j = 0; j < numUtilizadoSorteio.length; j++){
                if (sorteio[i]==numUtilizadoSorteio[j]){
                    sorteio[i]=gerarNumRandom();
                    j=-1;
                } ;

            }
            numUtilizadoSorteio[p]=sorteio[i];

        }

        return sorteio;
    }
    public static int[] gerarCartela(){
        int [] cartela= new int[5];
        int u = 5;
        int p = 0;
        int [] numUtilizados = new int[u];
        for (int i = 0; i < cartela.length; i++, p++) {
        cartela[i] = gerarNumRandom();
            for (int j = 0; j < numUtilizados.length; j++){
                if (cartela[i]==numUtilizados[j]){

                    cartela[i]=gerarNumRandom();
                    j=-1;
                } ;
            }
            numUtilizados[p]=cartela[i];
        }
        return cartela;
    }

    public static int gerarNumRandom(){
        int numRando;
        numRando = random.nextInt(60)+1;
        return numRando;
    }

}
