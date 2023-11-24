package com.ETR.NEPTUN.addexam;
import com.ETR.NEPTUN.exam.Exam;
import com.ETR.NEPTUN.user.User;
import jakarta.persistence.*;


@Entity
@Table(name = "VizsgaFelvetel")
public class AddExam {

    @Id
    @ManyToOne
    @JoinColumn(name = "Felhasznalo_Felhasznalonev")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Vizsga_kod")
    private Exam exam;
}
