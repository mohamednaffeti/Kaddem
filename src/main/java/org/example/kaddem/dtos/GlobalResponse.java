package org.example.kaddem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalResponse<T> {
    T data;
    @Builder.Default
    boolean succes = true;
    String message;

    public GlobalResponse(T data) {
        this.data = data;
    }

    public GlobalResponse(boolean succes) {
        this.succes = succes;
    }

    public GlobalResponse(T data, boolean succes) {
        this.data = data;
        this.succes = succes;

    }

    public GlobalResponse(boolean succes, String message) {
        this.succes = succes;
        this.message = message;
    }
}
