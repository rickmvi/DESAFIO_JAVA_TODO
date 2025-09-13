package controlers;

@lombok.Setter(value = lombok.AccessLevel.PUBLIC)
@lombok.Getter(value = lombok.AccessLevel.PUBLIC)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PUBLIC)
public class Tarefa{
    private int id;
    private String titulo;
    private String descricao;
    private SituacaoEnnum concluida;

    public Tarefa(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = SituacaoEnnum.NAO_CONCLUIDO;
    }

}
