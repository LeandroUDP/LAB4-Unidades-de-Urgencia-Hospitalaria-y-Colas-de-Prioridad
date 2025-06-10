import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        String archivo = "Pacientes_24h.txt"; //Aqui en realidad es mejor poner la ruta del archivo, no el nombre
        long timestampInicio = 0;

        System.out.println(">> Cargando pacientes desde archivo...");
        List<Paciente> pacientes = LectorPacientes.leerDesdeArchivo(archivo, timestampInicio);

        System.out.println("Pacientes cargados: " + pacientes.size());
        if (pacientes.isEmpty()) {
            System.out.println("No se puede continuar sin pacientes. Revisa el archivo.");
            return;
        }

        // Iniciar simulación
        System.out.println("\n>> Iniciando simulación de 24 horas...");
        SimuladorUrgencia simulador = new SimuladorUrgencia(pacientes);
        simulador.simular();

        // Mostrar promedios por categoría
        Map<Integer, Double> promedios = simulador.obtenerPromediosPorCategoria();
        System.out.println("\n>> Promedios por categoría:");
        for (int cat = 1; cat <= 5; cat++) {
            double prom = promedios.getOrDefault(cat, 0.0);
            System.out.printf("C%d → %.2f minutos%n", cat, prom);
        }

        System.out.println(">> Simulación finalizada.");
    }
}