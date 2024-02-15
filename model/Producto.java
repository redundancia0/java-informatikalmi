import javax.swing.ImageIcon;

public class Producto {
		private int id_producto;
        private String nombre;
        private String imagen;
        private double precio;
        private int stock;

        public Producto(int id_producto, String nombre, double precio, String imagen, int stock) {
        	this.id_producto = id_producto;
            this.nombre = nombre;
            this.precio = precio;
            this.imagen = imagen;
            this.stock = stock;
        }

        public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public void setImagen(String imagen) {
			this.imagen = imagen;
		}

		public String getNombre() {
            return nombre;
        }

        public int getId_producto() {
			return id_producto;
		}

		public void setId_producto(int id_producto) {
			this.id_producto = id_producto;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public String getImagen() {
            return imagen;
        }
    }