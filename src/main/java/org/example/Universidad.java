package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;

// Principio de Responsabilidad Única (SRP)
class Estudiante {
    private String nombre;
    private int id;

    public Estudiante(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}

// Principio de Responsabilidad Única (SRP)
interface Materia {
    String getNombre();
}

// Implementación de Materia
class MateriaImpl implements Materia {
    private String nombre;
    private String codigo;
    private int creditos;
    private String horario;
    private String grupo;

    public MateriaImpl(String nombre, String codigo, int creditos, String horario, String grupo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.horario = horario;
        this.grupo = grupo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public String getHorario() {
        return horario;
    }

    public String getGrupo() {
        return grupo;
    }
}

// Implementación extendida de Materia
class MateriaEspecial implements Materia {
    private String nombre;
    private String detalle;

    public MateriaEspecial(String nombre, String detalle) {
        this.nombre = nombre;
        this.detalle = detalle;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public String getDetalle() {
        return detalle;
    }
}

// Principio de Responsabilidad Única (SRP)
class Profesor {
    private String nombre;
    private MateriasGestionadas materiasGestionadas;

    public Profesor(String nombre) {
        this.nombre = nombre;
        this.materiasGestionadas = new MateriasGestionadas();
    }

    public void agregarMateria(Materia materia) {
        materiasGestionadas.agregarMateria(materia);
    }

    public List<Materia> getMaterias() {
        return materiasGestionadas.getMaterias();
    }

    public String getNombre() {
        return nombre;
    }
}

class MateriasGestionadas {
    private List<Materia> materias;

    public MateriasGestionadas() {
        this.materias = new ArrayList<>();
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    public List<Materia> getMaterias() {
        return materias;
    }
}

// Principio de Responsabilidad Única (SRP) y Principio de Inversión de Dependencias (DIP)
interface ServicioPago {
    void realizarPago();
    boolean estaPagado();
}

// Implementación del servicio de pago
class EstadoPago implements ServicioPago {
    private Matricula matricula;
    private boolean pagado;

    public EstadoPago(Matricula matricula) {
        this.matricula = matricula;
        this.pagado = false;
    }

    @Override
    public void realizarPago() {
        this.pagado = true;
    }

    @Override
    public boolean estaPagado() {
        return pagado;
    }
}

// Clase para manejar matrícula con responsabilidad única
class Matricula {
    private Estudiante estudiante;
    private Materia materia;
    private ServicioPago servicioPago;

    public Matricula(Estudiante estudiante, Materia materia, ServicioPago servicioPago) {
        this.estudiante = estudiante;
        this.materia = materia;
        this.servicioPago = servicioPago;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Materia getMateria() {
        return materia;
    }

    public ServicioPago getServicioPago() {
        return servicioPago;
    }
}

// Clase para manejar la inscripción con responsabilidad única
class Inscripcion {
    private Estudiante estudiante;
    private boolean inscrito;

    public Inscripcion(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.inscrito = false;
    }

    public void inscribir() {
        this.inscrito = true;
    }

    public boolean estaInscrito() {
        return inscrito;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
}

// Principio de Responsabilidad Única (SRP)
class Aula {
    private String nombre;
    private List<Materia> materias;

    public Aula(String nombre) {
        this.nombre = nombre;
        this.materias = new ArrayList<>();
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public String getNombre() {
        return nombre;
    }
}

// Ejemplo de uso
public class Universidad {
    public static void main(String[] args) {
        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante("Juan Pérez", 1);
        Estudiante estudiante2 = new Estudiante("María López", 2);

        // Crear materias
        Materia materia1 = new MateriaImpl("Matemáticas", "MATH101", 3, "08:00 - 10:00", "Grupo A");
        Materia materia2 = new MateriaImpl("Historia", "HIST202", 3, "10:00 - 12:00", "Grupo B");

        // Crear profesores y asignar materias
        Profesor profesor1 = new Profesor("Dr. Smith");
        profesor1.agregarMateria(materia1);

        Profesor profesor2 = new Profesor("Dr. Johnson");
        profesor2.agregarMateria(materia2);

        // Crear inscripciones
        Inscripcion inscripcion1 = new Inscripcion(estudiante1);
        inscripcion1.inscribir();

        Inscripcion inscripcion2 = new Inscripcion(estudiante2);

        // Crear matrículas
        Matricula matricula1 = new Matricula(estudiante1, materia1, new EstadoPago(null));
        matricula1.getServicioPago().realizarPago();

        Matricula matricula2 = new Matricula(estudiante2, materia2, new EstadoPago(null));

        // Crear aula y agregar materias
        Aula aula101 = new Aula("Aula 101");
        aula101.agregarMateria(materia1);
        aula101.agregarMateria(materia2);

        // Mostrar información
        System.out.println("Estudiante: " + matricula1.getEstudiante().getNombre() +
                ", Materia: " + matricula1.getMateria().getNombre() +
                ", Pagado: " + matricula1.getServicioPago().estaPagado());

        System.out.println("Estudiante: " + matricula2.getEstudiante().getNombre() +
                ", Materia: " + matricula2.getMateria().getNombre() +
                ", Pagado: " + matricula2.getServicioPago().estaPagado());

        System.out.println("Aula: " + aula101.getNombre() + ", Materias:");
        for (Materia materia : aula101.getMaterias()) {
            System.out.println(" - " + materia.getNombre());
        }
    }
}