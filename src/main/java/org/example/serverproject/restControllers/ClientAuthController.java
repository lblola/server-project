package org.example.serverproject.restControllers;

import jakarta.validation.Valid;
import org.example.serverproject.DTO.ClientAuthDTO;
import org.example.serverproject.models.Client;
import org.example.serverproject.models.Product;
import org.example.serverproject.serviceImpl.ClientServiceImpl;
import org.example.serverproject.serviceImpl.ProductServiceImpl;
import org.example.serverproject.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/client")
public class ClientAuthController {
    private final ClientServiceImpl clientService;
    private final ProductServiceImpl productService;
    private final ClientMapper clientMapper;
    private final ClientValidator clientValidator;
    private final Logger logger = Logger.getLogger(ClientAuthController.class.getName());

    @Autowired
    public ClientAuthController(ClientServiceImpl clientService, ProductServiceImpl productService, ClientMapper clientMapper, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.productService = productService;
        this.clientMapper = clientMapper;
        this.clientValidator = clientValidator;
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.findAll();
    }

    @PostMapping
    public HttpEntity<HttpStatus> registerClient(@RequestBody @Valid Client client,
                                                 BindingResult bindingResult){
        clientValidator.validate(client, bindingResult);
        if(bindingResult.hasErrors()){
            logger.log(Level.WARNING, "obj client is not correct");
            ErrorConverterMessage.returnErrorsCreateClientToClient(bindingResult);
        }
        clientService.save(client);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/auth")
    public boolean authClient(@RequestBody @Valid ClientAuthDTO clientAuthDTO, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            logger.log(Level.WARNING, "obj client is not correct");
            ErrorConverterMessage.returnErrorsCreateClientToClient(bindingResult);
        }
        return clientService.checkAuthClient(clientMapper.toEntity(clientAuthDTO));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<HttpStatus> deleteEmployee(@PathVariable("id") int id){
        clientService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") int id){
        if(clientService.findOne(id) == null)
            logger.log(Level.INFO, "Find obj client by id is null");
        return clientService.findOne(id);
    }

    @GetMapping("/{id}/orders")
    public List<Product> getOrdersByClient(@PathVariable("id") int id){
        List<Product> products = clientService.getProductListByClientId(id);
        if(!products.isEmpty()) {
            for (Product p : products) {
                System.out.println(p.getName());
            }
        }else{
            logger.log(Level.INFO, "Client orders is empty");
        }
        return products;
    }

    @PostMapping("/{clientId}/{productId}")
    public HttpEntity<HttpStatus> addOrderToClient(@PathVariable("clientId")int clientId,
                                                   @PathVariable("productId")int productId){
        productService.saveProductForClient(clientId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}/{productId}")
    public HttpEntity<HttpStatus> deleteOrderFromClient(@PathVariable("clientId") int clientId,
                                                        @PathVariable("productId") int productId){
        productService.deleteById(clientId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public Client getClientByName(@PathVariable("name")String name){
        return clientService.findByName(name);
    }

    @ExceptionHandler
    public HttpEntity<NotCreatedError> handleExceptionCreatedClient(ClientNotCreatedException e){
        NotCreatedError errorResponse = new NotCreatedError(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
