package models;

import com.github.rickmvi.jtoolbox.console.utils.ScannerUtils;
import com.github.rickmvi.jtoolbox.console.Out;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import controlers.SituacaoEnnum;
import controlers.Tarefa;

import java.util.ArrayList;

import static com.github.rickmvi.jtoolbox.control.Iteration.forEachIndex;

//TODO: Criar metodos

@lombok.Setter(value = lombok.AccessLevel.PUBLIC)
@lombok.Getter(value = lombok.AccessLevel.PUBLIC)
public class GerenciadorDeTarefas {

    public String titulo;
    public String descricao;
    private final ArrayList<Tarefa> listaDeTarefas;

    public GerenciadorDeTarefas(String titulo, String descricao) {
        this.titulo = (titulo != null) ? titulo : "Sem Titulo";
        this.descricao = (descricao != null) ? descricao : "Sem descricao";
        this.listaDeTarefas = new ArrayList<>();
    }

    public void listarTarefas(){
        Out.printLine(list());

        if (listaDeTarefas.isEmpty()) {
            Out.printLine("Nenhuma tarefa cadastrada.");
            return;
        }

        for (Tarefa tarefa : listaDeTarefas) {
            Out.printLine("ID: "  + tarefa.getId() +
                    " | TÍTULO: "    + tarefa.getTitulo() +
                    " | DESCRIÇÃO: " + tarefa.getDescricao() +
                    " | SITUAÇÃO: "  + tarefa.getConcluida()
            );
        }
    }

    public void adiconarTarefa(){
        ScannerUtils.nextLine();
        Out.printLine("Digite o título da terefa: ");
        setTitulo(ScannerUtils.nextLine());

        Out.printLine("Descreva sua tarefa: ");
        setDescricao(ScannerUtils.nextLine());

        Tarefa NovaTarefa = new Tarefa(
                listaDeTarefas.size(),
                getTitulo(),
                getDescricao()
        );
        listaDeTarefas.add(NovaTarefa);

        Out.printLine("Tarefa adiconada com sucesso");
    }

    public void marcarComoConcluido(){
        listarTarefas();
        ScannerUtils.nextLine();

        Out.printLine("Digite o ID do item que deseja concluir: ");
        int id = ScannerUtils.nextInt();

        if (id < 0 || id >= listaDeTarefas.size()) {
            Out.printLine("ID inválido!");
            return;
        }
        listaDeTarefas.get(id).setConcluida(SituacaoEnnum.CONCLUIDO);

        Out.printLine("Tarefa concluida com sucesso.");
        Out.newline();
    }

    public void removerTarefa(){
        listarTarefas();
        ScannerUtils.nextLine();
        Out.printLine("Digite o ID do item que deseja exluir: ");
        int id = ScannerUtils.nextInt();

        if (id < 0 || id >= listaDeTarefas.size()) {
            Out.printLine("ID inválido!");
            return;
        }

        listaDeTarefas.remove(id);
        forEachIndex(listaDeTarefas.size(), (i) -> listaDeTarefas.get(i).setId(i));

        Out.printLine("Tarefa removida com sucesso.");
        Out.newline();
    }

    @ApiStatus.Internal
    @Contract(pure = true)
    private @NotNull String list() {
        return """
                =========================================
                            LISTA DE TAREFAS
                =========================================
                """.stripIndent();
    }

}
