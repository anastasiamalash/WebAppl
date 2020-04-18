package com.project.WebAppl.controllers;

import com.project.WebAppl.models.Book;
import com.project.WebAppl.models.Client;
import com.project.WebAppl.repository.BookRepository;
import com.project.WebAppl.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainpage(Model model) {
        model.addAttribute("title", "Reader.io");
        return "mainpage";
    }
    @GetMapping("/Registration")
    public String reg(Model model) {
        return "registration";
    }
    @GetMapping("/Logon")
    public String log(Model model) {
        return "logon";
    }

    @GetMapping("/User")
    public String user(Model model) {
        return "user";
    }

    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/User/search")
    public String user1(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "booksearch";
    }

    @GetMapping("/Admin")
    public String admin(Model model) {
        return "admin";
    }

    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/Admin/add/")
    public String admin1(Model model) {
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "addclient";
    }

    @GetMapping("/Admin/add1/")
    public String admin2(Model model) {
        return "addclient1";
    }

    @PostMapping("/Admin/add/")
    public String adminclientadd(@RequestParam Long id, @RequestParam String FIO, @RequestParam String Email, @RequestParam String Password, @RequestParam int ClientsCode, @RequestParam int Admin, Model model) {
        Client client = new Client(id, FIO, Email, Password, ClientsCode, Admin);
        clientRepository.save(client);
        return "redirect:/Admin/add";
    }

    @GetMapping("/Admin/write/")
    public String admin3(Model model) {
        return "writebook";
    }

    @GetMapping("/Admin/add/{id}")
    public String featuresDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Client> client = clientRepository.findById(id);
        ArrayList<Client> res = new ArrayList<>();
        client.ifPresent(res::add);
        model.addAttribute("client", res);
        return "edit-delete";
    }

    @GetMapping("/Admin/add/{id}/edit")
    public String edit(@PathVariable(value = "id") long id, Model model) {
        Optional<Client> client = clientRepository.findById(id);
        ArrayList<Client> res = new ArrayList<>();
        client.ifPresent(res::add);
        model.addAttribute("client", res);
        return "edit";
    }

    @PostMapping("/Admin/add/{id}/edit")
    public String adminupd(@PathVariable(value = "id") long id, @RequestParam String FIO, @RequestParam String Email, @RequestParam String Password, @RequestParam int ClientsCode, @RequestParam int Admin, Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        client.setFIO(FIO);
        client.setEmail(Email);
        client.setPassword(Password);
        client.setClientsCode(ClientsCode);
        client.setAdmin(Admin);
        clientRepository.save(client);
        return "redirect:/Admin/add/";
    }

    @PostMapping("/Admin/add/{id}/delete")
    public String admindel(@PathVariable(value = "id") long id,  Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return "redirect:/Admin/add/";
    }

}
