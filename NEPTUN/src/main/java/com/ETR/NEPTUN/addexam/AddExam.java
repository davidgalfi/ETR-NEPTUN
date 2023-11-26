package com.ETR.NEPTUN.addexam;
import com.ETR.NEPTUN.exam.Exam;
import com.ETR.NEPTUN.user.User;
import jakarta.persistence.*;


@Entity
@Table(name = "vizsga_felvetel")
public class AddExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Felhasznalo_Felhasznalonev")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Vizsga_kod")
    private Exam exam;

    public AddExam() {
    }

    public AddExam(User user, Exam exam) {
        this.user = user;
        this.exam = exam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
