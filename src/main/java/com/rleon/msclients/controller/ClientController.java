package com.rleon.msclients.controller;

import com.rleon.msclients.model.dto.ResponseApi;
import com.rleon.msclients.model.entity.Client;
import com.rleon.msclients.model.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/client")
@Api(value = "Endpoint de usuario", consumes = "endPoint de creacion y reporte de clientes")
public class ClientController {


    private ClientService clientService;

    public ClientController(ClientService clientServ) {
        this.clientService = clientServ;
    }

    @PostMapping(value = "/creacliente")
    @ApiOperation(
            value = "Creacion de nuevo Cliente",
            httpMethod = "POST",
            tags = {"IRDIGITAL"},
            notes = "classpath:swagger/notes/post-create-client.md"
    )
    public ResponseEntity<?> createQualification(@Valid @RequestBody Client client) {
        try {
            log.trace("Inicio servicio NEW CLIENTE");
            return ResponseEntity.ok(new ResponseApi(200, "Success", clientService.createNewClient(client)));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi(500, "Error: " + e.getMessage()));
        }
    }

    @GetMapping(value = "/listclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listado de Clientes",
            httpMethod = "GET",
            tags = {"IRDIGITAL"},
            notes = "classpath:swagger/notes/get-list-clients.md")
    public ResponseEntity<?> getAll() {
        try {
            log.trace("Inicio servicio Listado de Clientes");
            return ResponseEntity.ok(new ResponseApi(200, "Success", clientService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi(500, "Error: " + e.getMessage()));
        }
    }

    @GetMapping(value = "/kpideclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "KPI de Clientes",
            httpMethod = "GET",
            tags = {"IRDIGITAL"},
            notes = "classpath:swagger/notes/get-kpi-client.md")
    public ResponseEntity<?> getKpi() {
        try {
            log.trace("Inicio servicio KPI de Clientes");
            return ResponseEntity.ok(new ResponseApi(200, "Success", clientService.getKpi()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi(500, "Error: " + e.getMessage()));
        }
    }

}
