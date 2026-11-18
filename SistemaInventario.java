// ==========================================
// INTERFAZ: Define un comportamiento común
// ==========================================
interface Promocion {
    // Método que obliga a implementar una lógica de descuento en los productos
    double aplicarDescuento();
}

// ==========================================
// CLASE ABSTRACTA: Producto
// Sirve como plantilla base para todos los artículos
// ==========================================
abstract class Producto implements Promocion {
    // Atributos comunes heredados por todas las subclases
    protected String nombre;
    protected double precio;
    protected int stock;

    // Constructor de la clase abstracta
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Método abstracto: obliga a cada subclase a definir su propia forma de mostrar información
    public abstract void mostrarInformacion();
}

// ==========================================
// SUBCLASE 1: Electronico (Herencia de Producto)
// ==========================================
class Electronico extends Producto {
    private int mesesGarantia;

    public Electronico(String nombre, double precio, int stock, int mesesGarantia) {
        super(nombre, precio, stock); // Llama al constructor de la clase padre
        this.mesesGarantia = mesesGarantia;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("[Electrónico] " + nombre + " | Precio: $" + precio + " | Stock: " + stock + " | Garantía: " + mesesGarantia + " meses");
    }

    @Override
    public double aplicarDescuento() {
        return precio * 0.90; // 10% de descuento para electrónicos
    }
}

// ==========================================
// SUBCLASE 2: Alimento (Herencia de Producto)
// ==========================================
class Alimento extends Producto {
    private String fechaCaducidad;

    public Alimento(String nombre, double precio, int stock, String fechaCaducidad) {
        super(nombre, precio, stock);
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("[Alimento] " + nombre + " | Precio: $" + precio + " | Stock: " + stock + " | Caduca: " + fechaCaducidad);
    }

    @Override
    public double aplicarDescuento() {
        return precio * 0.80; // 20% de descuento por alimentos próximos a vencer
    }
}

// ==========================================
// SUBCLASE 3: Ropa (Herencia de Producto)
// ==========================================
class Ropa extends Producto {
    private String talla;

    public Ropa(String nombre, double precio, int stock, String talla) {
        super(nombre, precio, stock);
        this.talla = talla;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("[Ropa] " + nombre + " | Precio: $" + precio + " | Stock: " + stock + " | Talla: " + talla);
    }

    @Override
    public double aplicarDescuento() {
        return precio * 0.85; // 15% de descuento en ropa
    }
}

// ==========================================
// PROGRAMA PRINCIPAL (Demostración de Polimorfismo)
// ==========================================
public class SistemaInventario {
    public static void main(String[] args) {
        // POLIMORFISMO: Creamos un arreglo de tipo Producto (clase abstracta)
        // pero almacenamos en él objetos de distintas subclases.
        Producto[] inventario = new Producto[3];

        inventario[0] = new Electronico("Smartphone", 400.0, 10, 12);
        inventario[1] = new Alimento("Café en grano", 8.5, 25, "2027-06-15");
        inventario[2] = new Ropa("Chamarra", 45.0, 15, "L");

        System.out.println("=== DEMOSTRACIÓN DE INVENTARIO Y POLIMORFISMO ===");
        System.out.println();

        // Recorremos el arreglo de forma uniforme. 
        // El programa sabe automáticamente qué método ejecutar dependiendo del tipo real del objeto.
        for (Producto p : inventario) {
            p.mostrarInformacion(); // Se ejecuta el método sobrescrito de cada subclase
            System.out.println("Precio con descuento aplicado: $" + p.aplicarDescuento());
            System.out.println("--------------------------------------------------");
        }
    }
}