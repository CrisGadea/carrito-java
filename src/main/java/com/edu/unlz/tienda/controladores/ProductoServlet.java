package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.ProductoDAO;
import com.edu.unlz.tienda.modelos.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
@WebServlet({"/productos", ""})
public class ProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductoDAO pdao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.pdao = new ProductoDAO(); // Inicializa pdao aquí
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var session = request.getSession();

        System.out.println("pasa por get productos");
        // Solo cargar productos si no están en la sesión

            try {
                var productos = pdao.getAll();
                session.setAttribute("productos", productos);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        // Redirigir o reenviar al index.jsp
        var rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var session = request.getSession();


        try {
            if (request.getParameter("method")!=null && request.getParameter("method").equals("update")){
                updateProducto(request, response);
            } else {
                if (request.getParameter("method")!=null && request.getParameter("method").equals("delete")){
                    deleteProducto(request, response);
                } else {
                System.out.println("pasa por insert de producto");
                    Producto producto = null;
                    try {
                        producto = pdao.getByNombre(request.getParameter("nombre"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    if (producto != null) {
                    throw new RuntimeException("ya existe el producto con id: " + request.getParameter("id"));
                }
                // Obtener la parte del archivo
                Part filePart = request.getPart("imagen");
                String uploadPath =  "/Users/cristiangadea/Downloads/carrito-java-master/src/main/webapp/img";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir(); // Crear la carpeta si no existe
                // ✅ Guardar el archivo en la carpeta
                // Obtener el nombre del archivo y su extensión
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String filePath = uploadPath + File.separator + fileName; // Ruta completa
                filePart.write(filePath);
                producto = new Producto(request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("precio")), Integer.parseInt(request.getParameter("stock")),"img/"+fileName);
                pdao.insert(producto);
                var productos = pdao.getAll();
                session.setAttribute("productos", productos);
            }}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // Redirigir o reenviar al index.jsp
        var rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    private void updateProducto(HttpServletRequest request, HttpServletResponse response) {

        try {
            var producto = pdao.getById(Integer.parseInt(request.getParameter("id")));
            if (producto == null){
                throw new RuntimeException("No se encontró el producto con id: " + request.getParameter("id"));
            };
            if (request.getParameter("nombre")!=""){
                producto.setNombre(request.getParameter("nombre"));
            };
            if (request.getParameter("descripcion")!=""){
                producto.setDescripcion(request.getParameter("descripcion"));
            };
            if (request.getParameter("precio")!=""){
                producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
            };
            if (request.getParameter("stock")!=""){
                producto.setStock(Integer.parseInt(request.getParameter("stock")));
            };
            if (request.getParameter("urlImg")!=""){
                producto.setUrlImg(request.getParameter("urlImg"));
            };
            pdao.update(producto);
            var productos = pdao.getAll();

            request.getSession().setAttribute("productos", productos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            var producto = pdao.getById(Integer.parseInt(request.getParameter("id")));
            if (producto == null){
                throw new RuntimeException("No se encontró el producto con id: " + request.getParameter("id"));
            };
            pdao.logicDelete(producto.getId());
            var productos = pdao.getAll();

            request.getSession().setAttribute("productos", productos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
