public class calculoBrasilCPF {

    public static int primeiroDigito = 0;
    public static int segundoDigito = 0;
    public static int multiplicadorCrescente = 2;
    public static String cpf = "00000000000";
    public static long contadorCPFvalido = 0L;
    
    //metodo que gera cpf
    public static void geradorCPF(long initialValue, long finalValue) {
        //Percorrendo para gerar todos os CPF's
        for (long i = initialValue; i <= finalValue; i++) {
            //connvertendo o indice do for em string
            String indexChar = Long.toString(i);
            //classe tring builder para usar seus métodos especificos
            StringBuilder cpfStringBuilder = new StringBuilder(cpf);
            //deletando os valores referente a posição inicial e final, tem situações de ter que
            //remover mais de um valor da String
            cpfStringBuilder = cpfStringBuilder.delete((cpf.length() - 1) - indexChar.length(), 
                    (cpf.length() - 1));
            //adicionando o novos valores para o CPF
            cpfStringBuilder = cpfStringBuilder.append(indexChar);
            System.out.println("\033[4;33m"+cpfStringBuilder);
            //priemeiro digito e segundo digito true
            if(calculaPrimeiroDigito(new String(cpfStringBuilder)) && 
            calculaSegundoDigito(new String(cpfStringBuilder))){
                    //os dois digitos verificadores são verdadeiros, logo cpf válido
                    contadorCPFvalido++;
            }
            primeiroDigito = 0;
            segundoDigito = 0;
        }

    }

    public static boolean calculaPrimeiroDigito(String cpf) {
        int vetCPF[] = new int[9];
        String posicaoNumero[] = cpf.split("");

        for (int i = 0; i < vetCPF.length; i++) {
            vetCPF[i] = Integer.parseInt(posicaoNumero[i]);
        }

        for (int i = (vetCPF.length - 1); i >= 0; i--) {
            primeiroDigito += vetCPF[i] * multiplicadorCrescente;
            multiplicadorCrescente++;
        }

        // atualizando para 2 novamente
        multiplicadorCrescente = 2;

        if (primeiroDigito % 11 < 2) {
            // digito igual a zero
            if (posicaoNumero[9].equals("0")) {
                // priemiro digito igual a zero
                return true;
            } else {
                // primeiro digito não é igual a zero
                return false;
            }
        } else {
            if (primeiroDigito % 11 >= 2) {
                // digito igual a 11 - resto da divisão
                int resultadoPrimeiroDigito = 11 - (primeiroDigito % 11);

                if (posicaoNumero[9].equals(Integer.toString(resultadoPrimeiroDigito))) {
                    // primeiro digito verdadeiro
                    return true;
                } else {
                    // CPF INVÁLIDO
                    return false;
                }
            }
            // caso não atende nenhuma das verificações
            return false;
        }
    }

    public static boolean calculaSegundoDigito(String cpf) {
        int vetCPF[] = new int[10];
        String posicaoNumero[] = cpf.split("");

        for (int i = 0; i < vetCPF.length; i++) {
            vetCPF[i] = Integer.parseInt(posicaoNumero[i]);
        }

        for (int i = (vetCPF.length - 1); i >= 0; i--) {
            segundoDigito += vetCPF[i] * multiplicadorCrescente;
            multiplicadorCrescente++;
        }

        // atualizando para 2 novamente
        multiplicadorCrescente = 2;

        if (segundoDigito % 11 < 2) {
            // digito igual a zero
            if (posicaoNumero[9].equals("0")) {
                return true;
            }else{
                return false;
            }
        } else {
            if (segundoDigito % 11 >= 2) {
                // digito igual a 11 - resto da divisão
                int resto = 11 - (segundoDigito % 11);

                if (posicaoNumero[10].equals(Integer.toString(resto))) {
                    //segundo digito válido
                    return true;
                } else {
                    // CPF INVÁLIDO
                    return false;
                }
            }
            // caso não atende nenhuma das verificações
            return false;
        }
    }
    
    public static long getContadorCPFvalido() {
        return contadorCPFvalido;
    }
}
