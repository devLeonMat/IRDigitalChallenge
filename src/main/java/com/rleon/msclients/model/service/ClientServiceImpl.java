package com.rleon.msclients.model.service;

import com.rleon.msclients.model.dto.KpiDto;
import com.rleon.msclients.model.entity.Client;
import com.rleon.msclients.model.repository.ClientRepository;
import com.rleon.msclients.utils.Validate;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createNewClient(Client user) throws Exception {
        try {
            user.setDateOfDeath(calculateTimeLife(user));

            /** esperanza de vida (podriamos hacerlo configurable, pero desconozco como calculan en su compania la esperanza de vida, pero esto puede agregarse a esta logica)
             pondremos como esperanza de vida en peru 80 años
             **/
            return clientRepository.save(user);

        } catch (Exception e) {
            log.error("Error-->{}", e.getMessage());
            throw new Exception(e);
        }
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public KpiDto getKpi() throws Exception {
        KpiDto kpi = new KpiDto();
        try {
            List<Client> listClient = clientRepository.findAll();
            /** cantidad de registros **/
            int cantRegist = listClient.size();

            /** Suma de edades **/
            int sumaEdades = listClient.stream().mapToInt(Client::getAge).sum();

            /** Media o Promedio de edades **/
            double mediaAritmetica = sumaEdades / cantRegist;

            double sumatoria = 0;

            for (Client client : listClient) {
                sumatoria += Math.pow(client.getAge() - mediaAritmetica, 2);
            }

            double desvSt = Math.sqrt(sumatoria / (cantRegist - 1));
            kpi.setPromEdad(mediaAritmetica);
            kpi.setDesvStandar(Validate.redondearDecimales(desvSt, 2));

        } catch (Exception e) {
            log.error("Error->{}", e.getMessage());
            throw new Exception(e);
        }
        return kpi;
    }

    @Deprecated
    private Date calculateTimeLife(Client client) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(client.getDateOfBirth());
        calendar.add(Calendar.YEAR, 80);
        return calendar.getTime();
    }

}
