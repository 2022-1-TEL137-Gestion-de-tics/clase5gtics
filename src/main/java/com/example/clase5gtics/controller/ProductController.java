package com.example.clase5gtics.controller;

import com.example.clase5gtics.entity.Product;
import com.example.clase5gtics.repository.CategoryRepository;
import com.example.clase5gtics.repository.ProductRepository;
import com.example.clase5gtics.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping(value = {"", "/"})
    public String listaProductos(Model model) {
        model.addAttribute("listaProductos", productRepository.findAll());
        return "product/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("listaCategorias", categoryRepository.findAll());
        model.addAttribute("listaProveedores", supplierRepository.findAll());
        return "product/editFrm";
    }

    @PostMapping("/save")
    public String guardarProducto(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                                  Model model, RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaCategorias", categoryRepository.findAll());
            model.addAttribute("listaProveedores", supplierRepository.findAll());
            return "product/editFrm";
        } else {
            String msg = "Producto " + (product.getId() == 0 ? "creado" : "actualizado") + " exitosamente";
            attr.addFlashAttribute("msg", msg);
            productRepository.save(product);
            return "redirect:/product";
        }
    }

    @GetMapping("/edit")
    public String editarTransportista(Model model, @RequestParam("id") int id) {

        Optional<Product> optProduct = productRepository.findById(id);

        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            model.addAttribute("product", product);
            model.addAttribute("listaCategorias", categoryRepository.findAll());
            model.addAttribute("listaProveedores", supplierRepository.findAll());
            return "product/editFrm";
        } else {
            return "redirect:/product";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Product> optProduct = productRepository.findById(id);

        if (optProduct.isPresent()) {
            productRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
        }
        return "redirect:/product";

    }

}
