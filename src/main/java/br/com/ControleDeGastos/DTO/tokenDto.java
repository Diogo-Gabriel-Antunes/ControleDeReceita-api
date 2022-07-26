package br.com.ControleDeGastos.DTO;

public class tokenDto {
    private String token;
    private String tipo;

    public tokenDto(String token, String bearer) {
        this.token = token;
        this.tipo = bearer;

    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
