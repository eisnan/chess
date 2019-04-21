package app.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "chess_user")
public class ChessUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Enumerated
    @Column(name = "user_type", columnDefinition = "smallint")
    private UserType userType;

    @Column(name = "username", unique = true)
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String emailAddress;

}
