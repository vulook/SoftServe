package com.softserve.controller;

import com.softserve.entity.Cart;
import com.softserve.entity.Form;
import com.softserve.service.CartService;
import com.softserve.service.FormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
public class CartController {

    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private FormService formService;

    @GetMapping("/cart/list")
    public String listCarts(Model theModel) {
        LOG.debug("Show Carts handler method");
        List<Cart> theCarts = cartService.findAllByID();
        theModel.addAttribute("carts", theCarts);
        return "list-carts";
    }
    @GetMapping("/carts")
    public String allCarts(Model theModel) {
        LOG.debug("Show Carts handler method");
        List<Cart> theCarts = cartService.getAll();
        theModel.addAttribute("carts", theCarts);
        return "all-carts";
    }
    @GetMapping("/cart/showForm")
    public String showFormForAdd(Model theModel) {
        LOG.debug("Inside show cart-form handler method");
        Cart theCart = new Cart();
        theModel.addAttribute("cart", theCart);
        return "cart-form";
    }

    @PostMapping("/cart/saveCart")
    public String saveCart(@ModelAttribute("cart") Cart theCart) {
        LOG.debug("Save Cart handler method");
        cartService.create(theCart);
        return "redirect:/cart/list";
    }

    @GetMapping("/cart/updateForm")
    public String showFormForUpdate(@RequestParam("cartID") long theId,
                                    Model theModel) {
        LOG.debug("Update Cart handler method");
        Cart theCart = cartService.findByID(theId);
        theModel.addAttribute("cart", theCart);
        return "cart-form";
    }
    @GetMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable long id)  {
        LOG.debug("Delete Cart handler method");
        cartService.delete(id);
        return "redirect:/cart/list";
    }
    @GetMapping("/carts/delete/{id}")
    public String deleteCarts(@PathVariable long id)  {
        LOG.debug("Delete Cart handler method");
        cartService.delete(id);
        return "redirect:/carts";
    }
    @GetMapping("/cart/add/{id}")
    public String addCart(@PathVariable long id)  {
        LOG.debug("Delete Cart handler method");
        cartService.request(id);
        return "redirect:/cart/list";
    }
//    @GetMapping("/carts/updateForm")
//    public String showForm(@RequestParam("cartID") long theId,
//                                    Model theModel) {
//        LOG.debug("Update Cart handler method");
//        Cart theCart = cartService.findByID(theId);
//        Form theForm = new Form();
////        Date date = new Date();
//        theForm.setFormUser(theCart.getCartUser());
//        theForm.setFormBook(theCart.getCartBook());
////        java.sql.Date date1= (java.sql.Date) date;
//
//        theModel.addAttribute("form", theForm);
//        return "form-form";
//    }

}