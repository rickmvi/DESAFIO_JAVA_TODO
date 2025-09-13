package controlers;

import com.github.rickmvi.jtoolbox.console.Out;
import com.github.rickmvi.jtoolbox.console.utils.ScannerUtils;
import models.GerenciadorDeTarefas;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.InputMismatchException;

@lombok.Setter(value = lombok.AccessLevel.PUBLIC)
@lombok.Getter(value = lombok.AccessLevel.PUBLIC)
public class Menu {

    private int requisicaoMenu;
    private final GerenciadorDeTarefas gerenciador;

    public Menu() {
        ScannerUtils.init();
        this.gerenciador = new GerenciadorDeTarefas("Estudar", "Estudar Java");
    }

    public void exibirMenu(){
        while (true){
            try {
                Out.print(menu());
                setRequisicaoMenu(ScannerUtils.nextInt());

                switch (getRequisicaoMenu()) {
                    case 1 -> gerenciador.listarTarefas();
                    case 2 -> gerenciador.adiconarTarefa();
                    case 3 -> gerenciador.marcarComoConcluido();
                    case 4 -> gerenciador.removerTarefa();
                    case 5 -> {
                            Out.printFormatted("Até a próxima.%n{}%n", "Fechando..");
                            ScannerUtils.close();
                            return;
                    }
                    default -> System.out.println("Digite somente um número do menu.");
                }

            } catch (InputMismatchException e){
                System.err.println("Digite apenas números, por favor!.");
                ScannerUtils.next();
            }
        }
    }

    @ApiStatus.Internal
    @Contract(pure = true)
    private @NotNull String menu() {
        return """
                ====================================
                             TODO LIST
                ====================================
                [1] Exibir lista de tarefas.
                [2] Adiconar tarefas.
                [3] Marcar como concluido.
                [4] Remover tarefa.
                [5] Sair.
                
                Escolha uma opção: 
                """;
    }
}
