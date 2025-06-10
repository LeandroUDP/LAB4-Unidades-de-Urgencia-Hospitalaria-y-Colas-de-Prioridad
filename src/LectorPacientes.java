
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Random;

public class LectorPacientes {
    public static List<Paciente> leerDesdeArchivo(String nombreArchivo, long timestampInicio) {
        List<Paciente> lista = new ArrayList<>();
        int id = 1000;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int i = 0;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 4) {
                    System.out.println("Línea inválida: " + linea); // debug temporal
                    continue;
                }

                if (partes.length < 4) continue;


                String nombre = partes[0].trim();
                String apellido = partes[1].trim();
                int categoria = Integer.parseInt(partes[2].trim().substring(1));
                long llegada = timestampInicio + i * 600;
                String area = obtenerAreaAleatoria();

                Paciente p = new Paciente(nombre, apellido, "P" + (id++), categoria, llegada, area);
                lista.add(p);
                i++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return lista;
    }

    private static String obtenerAreaAleatoria() {
        String[] areas = {"SAPU", "urgencia_adulto", "infantil"};
        return areas[new Random().nextInt(areas.length)];
    }
}