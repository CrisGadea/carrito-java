package com.edu.unlz.tienda.controladores;

import com.edu.unlz.tienda.daos.ProductoDAO;
import com.edu.unlz.tienda.modelos.Producto;
import com.edu.unlz.tienda.modelos.ProductoCarrito;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductoDAO pdao;
    @Override
    public void init() throws ServletException {
        super.init();
        this.pdao = new ProductoDAO(); // Inicializa pdao aquí
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method")!=null?request.getParameter("method"):"";
        System.out.println("method: "+method);
        if (method.equals("delete")){
            eliminarProductoCarrito(request, response);

        }else if(method.equals("updateCantidad")){
            try {
                updateCantidad(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            mostrarCarrito(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/carrito/carrito.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperar los productos del carrito desde la sesión
        var session = request.getSession();

        List<ProductoCarrito> productosCarrito = session.getAttribute("productosCarrito") == null ?
                new ArrayList<ProductoCarrito>() :
                (ArrayList<ProductoCarrito>) session.getAttribute("productosCarrito");


        var productId=request.getParameter("productId");
        Producto producto= null;
        int totalCantidad=0;
        try {
            producto = pdao.getById(Integer.parseInt(productId));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (producto != null) {
            if (productosCarrito.size() == 0) {
                System.out.println("primer producto");
                ProductoCarrito productoCarrito=new ProductoCarrito();
                productoCarrito.setCantidad(1);
                productoCarrito.setIdProducto(producto.getId());
                productoCarrito.setNombre(producto.getNombre());
                productoCarrito.setPrecio(producto.getPrecio());
                productosCarrito.add(productoCarrito);
                session.setAttribute("totalCantidad",1);
                System.out.println("total cantidad primer producto: "+session.getAttribute("totalCantidad"));
            }else {
                ProductoCarrito productoCarrito=new ProductoCarrito();
                productoCarrito.setCantidad(1);
                productoCarrito.setIdProducto(producto.getId());
                productoCarrito.setNombre(producto.getNombre());
                productoCarrito.setPrecio(producto.getPrecio());
                if (!productosCarrito.contains(productoCarrito)) {
                    System.out.println("producto no existente se va a agregar p.getIdProducto(): "+producto.getId());
                    productosCarrito.add(productoCarrito);
                    System.out.println("productoCarrito: "+productoCarrito.toString());
                }else{
                    System.out.println("producto existente p.getIdProducto(): "+producto.getId());
                        System.out.println("size: "+productosCarrito.size());
                    Producto finalProducto = producto;
                    ProductoCarrito p = productosCarrito.stream() .filter(productoCarrito1 -> productoCarrito1.getIdProducto()==finalProducto.getId()).findFirst().get();

                    p.setCantidad(p.getCantidad() + 1);
                    p.setPrecio(p.getPrecio() + producto.getPrecio());

                }
                session.setAttribute("totalCantidad",(int)session.getAttribute("totalCantidad")+1);
                System.out.println("totalCantidad: "+totalCantidad);

        }}

        // Enviar la lista de productos del carrito a la vista
        session.setAttribute("productosCarrito", productosCarrito);

        request.setAttribute("productosCarrito", productosCarrito);
        // Redirigir a la vista del carrito
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    private void mostrarCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar los productos del carrito desde la sesión
        var session = request.getSession();
        AtomicInteger totalCantidad= new AtomicInteger();
        List<ProductoCarrito> productosCarrito = (List<ProductoCarrito>) session.getAttribute("productosCarrito")!=null? (List<ProductoCarrito>) session.getAttribute("productosCarrito"):new ArrayList<>();
        productosCarrito.forEach(productoCarrito -> totalCantidad.addAndGet(productoCarrito.getCantidad()));

        session.setAttribute("productosCarrito", productosCarrito);
        // Pasar la lista de productos del carrito a la vista usando request
        request.setAttribute("productosCarrito", productosCarrito);


    }
    private void eliminarProductoCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar el ID del producto a eliminar desde los parámetros de la solicitud
        String productoIdStr = request.getParameter("productoId");
        int totalCantidad=request.getSession().getAttribute("totalCantidad")==null?0:(int)request.getSession().getAttribute("totalCantidad");
        System.out.println("pasa por el delete: ");
        // Validar que se recibió el ID del producto
        if (productoIdStr == null || productoIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del producto no proporcionado.");
            return;
        }

        int productoId;
        try {
            productoId = Integer.parseInt(productoIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del producto no válido.");
            return;
        }

        // Recuperar los productos del carrito desde la sesión
        var session = request.getSession();
        List<ProductoCarrito> productosCarrito = (List<ProductoCarrito>) session.getAttribute("productosCarrito");

        // Si no hay productos en el carrito, inicializar una lista vacía
        if (productosCarrito == null) {
            productosCarrito = new ArrayList<>();
        } else {
            Optional<ProductoCarrito> productosCarritoFound= productosCarrito.stream().filter(productoCarrito -> productoCarrito.getIdProducto()==productoId).findFirst();
            System.out.println("productoId: "+productoId);
            System.out.println("productosCarritoFound: "+productosCarritoFound);
            if (productosCarritoFound.isPresent()){
                System.out.println("totalCantidad: "+totalCantidad);
                session.setAttribute("totalCantidad",totalCantidad-productosCarritoFound.get().getCantidad());
                System.out.println(totalCantidad-productosCarritoFound.get().getCantidad());
            }

            // Eliminar el producto correspondiente
            productosCarrito.removeIf(producto -> producto.getIdProducto() == productoId);

        }
        // Actualizar la lista de productos del carrito en la sesión
        session.setAttribute("productosCarrito", productosCarrito);

        // Pasar la lista de productos del carrito a la vista usando request
        request.setAttribute("productosCarrito", productosCarrito);
    }
    private void updateCantidad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Obtener la sesión y la lista de productos del carrito
        var session = request.getSession();
        List<ProductoCarrito> productosCarrito = (List<ProductoCarrito>) session.getAttribute("productosCarrito");
        int totalCantidad=session.getAttribute("totalCantidad")==null?0:(int)session.getAttribute("totalCantidad");
        if (productosCarrito == null) {
            response.sendRedirect(request.getContextPath() + "/carrito");
            return;
        }

        // Obtener parámetros del producto y la acción
        String productoId = request.getParameter("productoId");
        String accion = request.getParameter("accion");

        if (productoId != null && accion != null) {
            System.out.println("paso por el updateCantidad");
            Producto producto=pdao.getById(Integer.parseInt(productoId));
            // Buscar el producto en el carrito
            for (ProductoCarrito productoCarrito : productosCarrito) {
                if (productoCarrito.getIdProducto() == producto.getId()) {
                    if ("sumar".equals(accion)) {
                        productoCarrito.setCantidad(productoCarrito.getCantidad() + 1);
                        productoCarrito.setPrecio(productoCarrito.getPrecio() + producto.getPrecio());
                        totalCantidad=totalCantidad+1;
                    } else if ("restar".equals(accion) && productoCarrito.getCantidad() > 1) {
                        System.out.println("precio"+producto.getPrecio());
                        productoCarrito.setCantidad(productoCarrito.getCantidad() - 1);
                        productoCarrito.setPrecio(productoCarrito.getPrecio() - producto.getPrecio());
                        totalCantidad=totalCantidad-1;
                    }
                    break;
                }

            }
        }
        session.setAttribute("totalCantidad",totalCantidad);
        // Actualizar la sesión y redirigir al carrito
        session.setAttribute("productosCarrito", productosCarrito);
        request.setAttribute("productosCarrito", productosCarrito);
    }

}