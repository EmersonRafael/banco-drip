package com.drip.banco.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RealizarTransferenciaRequest implements Serializable {

   @NotNull
   private Double valor;

   @NotNull
   private String tipoTransferencia;

}
