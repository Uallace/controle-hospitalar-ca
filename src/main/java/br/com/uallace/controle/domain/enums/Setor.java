package br.com.uallace.controle.domain.enums;



public enum Setor {
    EMERGENCIA(0),
    ENFERMARIA(1),
    UTI(2);

    private Integer codigo;

    private Setor(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
    public static Setor valueOf (Integer codigo){

        if(codigo == null){
            return null;
        }

        for(Setor s : Setor.values()){
            if(codigo.equals(s.getCodigo())) {
                return s;
            }
        }

        throw new IllegalArgumentException("Setor Inválido!");
    }
}
