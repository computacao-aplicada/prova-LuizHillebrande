public class Validador {

    // Método principal que valida um CPF
    public static boolean validarCPF(String cpf) {
        // Se a entrada for nula ou só espaços, é inválido
        if (cpf == null || cpf.trim().isEmpty()) return false;

        // Limpa a string: remove espaços, pontos e traços
        String limpo = sanitizar(cpf);

        // Verifica se restam exatamente 11 dígitos
        if (!temFormatoValido(limpo)) return false;

        // Sequências repetidas (todos os dígitos iguais) são inválidas
        if (todosDigitosIguais(limpo)) return false;

        // Verifica se os dígitos verificadores batem
        return checarDigitos(limpo);
    }

    // Remove espaços, pontos e traços do CPF
    private static String sanitizar(String cpf) {
        return cpf.trim().replaceAll("[.-]", "");
    }

    // Confere se a string tem exatamente 11 números
    private static boolean temFormatoValido(String cpf) {
        return cpf.matches("\\d{11}");
    }

    // Confere se todos os dígitos são iguais (ex.: 00000000000)
    private static boolean todosDigitosIguais(String cpf) {
        return cpf.chars().distinct().count() == 1;
    }

    // Calcula os dígitos verificadores DV1 e DV2 e compara com os dígitos do CPF
    private static boolean checarDigitos(String cpf) {
        // Converte cada caractere em número inteiro
        int[] d = cpf.chars().map(c -> c - '0').toArray();

        // Calcula DV1 usando os 9 primeiros dígitos
        int s1 = 0;
        for (int i = 0; i < 9; i++) s1 += d[i] * (10 - i);
        int dv1 = calcularDV(s1);

        // Se DV1 não bater, CPF é inválido
        if (d[9] != dv1) return false;

        // Calcula DV2 usando os 9 primeiros dígitos + DV1
        int s2 = 0;
        for (int i = 0; i < 10; i++) s2 += d[i] * (11 - i);
        int dv2 = calcularDV(s2);

        // Retorna true apenas se DV2 bater com o dígito final
        return d[10] == dv2;
    }

    // Calcula o dígito verificador a partir da soma ponderada
    private static int calcularDV(int soma) {
        int resto = soma % 11;

        // Se o resto for menor que 2, o dígito é 0; caso contrário, é 11 menos o resto
        return (resto < 2) ? 0 : 11 - resto;
    }
}
