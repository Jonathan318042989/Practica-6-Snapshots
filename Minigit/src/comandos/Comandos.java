package comandos;

import comandos.constants.Constantes;
import java.util.HashMap;
import java.util.Scanner;

public class Comandos {
    private HashMap<String, String> comandoHelp;

    public Comandos() {
        comandoHelp = new HashMap<>();
        comandoHelp.put("push", Constantes.PUSH);
        comandoHelp.put("add", Constantes.ADD);
        comandoHelp.put("help", Constantes.HELP);
        comandoHelp.put("checkout", Constantes.CHECKOUT);
        comandoHelp.put("diff", Constantes.DIFF);
    }

    public void ejecutarComando(String comando) {
        switch (comando) {
            case "push":
                System.out.println("Ejecutando 'git push'...");
                break;
            case "add":
                System.out.println("Ejecutando 'git add'...");
                break;
            case "checkout":
                System.out.println("Ejecutando 'git checkout'...");
                break;
            case "diff":
                System.out.println("Ejecutando 'git diff' ...");
                break;
            case "help":
                showHelp();
                break;
            default:
                System.out.println("Comando no reconocido. Use 'git help' para obtener ayuda.");
                break;
        }
    }

    public void showHelp() {
        System.out.println("Comandos disponibles:");
        comandoHelp.forEach((comando, description) -> System.out.println("'" + comando + "': " + description));
    }

    public static void main(String[] args) {
        Comandos gitTerminal = new Comandos();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese sus comandos git (type 'exit' para salir):");

        while (true) {
            System.out.print("git ");
            String input = scanner.nextLine().trim();
            if ("exit".equals(input)) {
                break;
            }
            if (input.startsWith("help") && input.split("\\s+").length > 1) {
                gitTerminal.getComandoHelp(input.split("\\s+")[1]);
            } else {
                gitTerminal.ejecutarComando(input);
            }
        }

        scanner.close();
    }

    public void getComandoHelp(String comando) {
        if (comandoHelp.containsKey(comando)) {
            System.out.println("Ayuda para 'git " + comando + "': " + comandoHelp.get(comando));
        } else {
            System.out.println("No se encontró ayuda para el comando 'git " + comando + "'.");
        }
    }
}
