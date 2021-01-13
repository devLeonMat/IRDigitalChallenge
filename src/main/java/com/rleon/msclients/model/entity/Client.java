package com.rleon.msclients.model.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@ApiModel("Model Client")
@Document(collection = "Clientes")
@Data
public class Client {


    @Id
    @ApiModelProperty(value = "Id de Cliente")
    private String id;

    @ApiModelProperty(value = "Nombre de Cliente", required = true)
    @NotNull(message="No puede ser Nulo")
    private String names;

    @ApiModelProperty(value = "Apellido Paterno", required = true)
    @NotNull(message="No puede ser Nulo")
    private String lastName;

    @ApiModelProperty(value = "Apellido Materno", required = true)
    @NotNull(message="No puede ser Nulo")
    private String secLastName;

    @ApiModelProperty(value = "Nombre de Usuario", required = true)
    @NotNull(message="No puede ser Nulo")
    private int age;

    @ApiModelProperty(value = "Fecha de Nacimiento", required = true)
    @NotNull(message="No puede ser Nulo")
    private Date dateOfBirth;

    @ApiModelProperty(value = "Fecha de Posible Fallecimiento")
    private Date dateOfDeath;


    public Client() {
    }
}
