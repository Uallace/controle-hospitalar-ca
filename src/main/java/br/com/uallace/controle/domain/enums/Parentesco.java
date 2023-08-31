package br.com.uallace.controle.domain.enums;

public enum Parentesco {
    MAE(0),
    PAI(1),
    IRMAO(2),
    AVÓ(3),
    AVÔ(4),
    OUTROS(5);

    private Integer codigo;

    Parentesco(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }


    public static Parentesco valueOf(Integer codigo){
        if(codigo == null){
            return null;
        }
        for(Parentesco p : Parentesco.values()){
            if(codigo.equals(p.getCodigo())){
                return p;
            }
        }
        throw new IllegalArgumentException("Parentesco inválido!!");
    }
}
