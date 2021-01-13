package com.rleon.msclients.model.service;

import com.rleon.msclients.model.dto.KpiDto;
import com.rleon.msclients.model.entity.Client;

import java.util.List;


public interface ClientService {

    Client createNewClient(Client user) throws Exception;

    List<Client> findAll();

    KpiDto getKpi() throws Exception;

}
