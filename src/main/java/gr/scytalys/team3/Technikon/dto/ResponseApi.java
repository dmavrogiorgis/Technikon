package gr.scytalys.team3.Technikon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
    public class ResponseApi <T>{
        private int code;
        private String message;
        private T data;

}
