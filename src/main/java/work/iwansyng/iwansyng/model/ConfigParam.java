package work.iwansyng.iwansyng.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "config_params")
@NoArgsConstructor
@Getter
@Setter
public class ConfigParam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "config_id")
    private Integer id;
    private Boolean isEnabled;
    private String configParamName;
}