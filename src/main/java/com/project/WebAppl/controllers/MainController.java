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

    /*@GetMapping("/Logon")
    public String log(Model model) {
        return "login";
    }*/

    @GetMapping("/User")
    public String user(Model model) {
        return "user";
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

    @PostMapping("/Admin/add1/")
    public String adminclientadd(@RequestParam String FIO, @RequestParam String Email, @RequestParam String Password, @RequestParam int ClientsCode, @RequestParam int Admin, Model model) {
        Client client = new Client(FIO, Email, Password, ClientsCode, Admin);
        clientRepository.save(client);
        return "redirect:/Admin/add/";
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
    public String admindel(@PathVariable(value = "id") long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return "redirect:/Admin/add/";
    }

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/User/search")
    public String user1(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "booksearch";
    }

    @PostMapping("/User/search")
    public String search(@RequestParam String Book, @RequestParam String Author, Model model) {
        Book book = new Book(Book, Author);
        bookRepository.save(book);
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "booksearch";
    }

    /*@GetMapping("/User/search")
    public String filter(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Book> books = bookRepository.findAll();
        if (filter != null && !filter.isEmpty()){
            books = bookRepository.findByBook(filter);
        } else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        return "booksearch";
    }*/

}
