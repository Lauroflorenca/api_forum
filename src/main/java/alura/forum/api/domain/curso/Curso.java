package alura.forum.api.domain.curso;

import alura.forum.api.domain.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Curso")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;


    public Curso(CursoDTO cursoDTO) {
        this.id = cursoDTO.id();
        this.nome = cursoDTO.nome();
        this.categoria = cursoDTO.categoria();
    }
}
